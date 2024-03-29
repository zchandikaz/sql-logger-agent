package info.chandika.sql.logger.agent.instrumentation.transformer;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

import java.io.IOException;
import java.security.ProtectionDomain;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author : chandika
 * @since : 2024-03-27(Wed) 20:24
 **/
public class OracleDriverTransformer implements IDriverTransformer {
    private static final Logger LOGGER = Logger.getLogger(OracleDriverTransformer.class.getName());

    @Override
    public byte[] apply(Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        try {
            ClassPool cp = ClassPool.getDefault();
            CtClass cc = cp.get("org.postgresql.Driver");

            CtMethod connectMethod = cc.getDeclaredMethod("connect");
            connectMethod.insertBefore("" +
                    "if(((info.chandika.sql.logger.agent.instrumentation.ThreadLocalFlags.ToggleBool)(info.chandika.sql.logger.agent.instrumentation.ThreadLocalFlags#isFirstCall.get())).getAndToggle()){\n" +
                    "    return  new info.chandika.sql.logger.agent.connection.WrappedConnection(this.connect(url, info));\n" +
                    "}" +
                    "");

            classfileBuffer = cc.toBytecode();
            cc.detach();
        } catch (NotFoundException | CannotCompileException | IOException e) {
            LOGGER.log(Level.SEVERE, "", e);
        }
        return classfileBuffer;
    }
}
