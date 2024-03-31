package info.chandika.sql.logger.agent.instrumentation.transformer;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import org.ietf.jgss.GSSCredential;

import java.security.ProtectionDomain;
import java.util.Properties;

/**
 * @author : chandika
 * @since : 2024-03-27(Wed) 20:21
 **/
public abstract class AbstractDriverTransformer {
    public byte[] apply(Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws Exception {
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get(getClassName());

        String methodName = getConnectMethodName();

        CtMethod connectMethod = getConnectMethod(cp, cc);
        connectMethod.insertBefore("" +
                "if(((info.chandika.sql.logger.agent.instrumentation.ThreadLocalFlags.ToggleBool)(info.chandika.sql.logger.agent.instrumentation.ThreadLocalFlags#isFirstCall.get())).getAndToggle()){\n" +
                "    return info.chandika.sql.logger.agent.loggable.ConnectionWrapper.wrap(this." + methodName + "($$));\n" +
                "}" +
                "");

        classfileBuffer = cc.toBytecode();
        cc.detach();
        return classfileBuffer;
    }

    public abstract String getClassName();

    public String getConnectMethodName() {
        return "connect";
    }

    public CtMethod getConnectMethod(ClassPool cp, CtClass cc) throws NotFoundException {
        return cc.getDeclaredMethod(getConnectMethodName());
    }
}
