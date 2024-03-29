package info.chandika.sql.logger.agent.instrumentation;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author : chandika
 * @since : 2023-07-30(Sun) 21:03
 **/
public class GenericDriverTransformer implements ClassFileTransformer {
    private static final Logger LOGGER = Logger.getLogger(GenericDriverTransformer.class.getName());

    private static final String METHOD_NAME = "connect";

    /**
     * The internal form class name of the class to transform
     */
    private String targetClassName;
    /**
     * The class loader of the class we want to transform
     */
    private ClassLoader targetClassLoader;

    public GenericDriverTransformer(String targetClassName, ClassLoader targetClassLoader) {
        this.targetClassName = targetClassName;
        this.targetClassLoader = targetClassLoader;
    }


    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        byte[] byteCode = classfileBuffer;
        String finalTargetClassName = this.targetClassName
                .replaceAll("\\.", "/");
        if (!className.equals(finalTargetClassName)) {
            return byteCode;
        }

        if (className.equals(finalTargetClassName) && loader.equals(targetClassLoader)) {

            LOGGER.info("[Agent] Transforming class MyAtm");
            try {
                ClassPool cp = ClassPool.getDefault();
                CtClass cc = cp.get(targetClassName);

                CtMethod connectMethod = cc.getDeclaredMethod(METHOD_NAME);
                connectMethod.insertBefore("" +
                        "if(((info.chandika.sql.logger.agent.instrumentation.ThreadLocalFlags.ToggleBool)(info.chandika.sql.logger.agent.instrumentation.ThreadLocalFlags#isFirstCall.get())).getAndToggle()){\n" +
                        "    return  new info.chandika.sql.logger.agent.connection.WrappedConnection(this.connect(url, info));\n" +
                        "}" +
                        "");

                byteCode = cc.toBytecode();
                cc.detach();
            } catch (NotFoundException | CannotCompileException | IOException e) {
                LOGGER.log(Level.SEVERE, "", e);
            }
        }
        return byteCode;
    }
}
