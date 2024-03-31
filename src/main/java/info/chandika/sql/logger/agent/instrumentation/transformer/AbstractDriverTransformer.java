package info.chandika.sql.logger.agent.instrumentation.transformer;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.security.ProtectionDomain;

/**
 * @author : chandika
 * @since : 2024-03-27(Wed) 20:21
 **/
public abstract class AbstractDriverTransformer {
    public byte[] apply(Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws Exception {
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get(getClassName());

        String methodName = getConnectMethodName();

        CtMethod connectMethod = cc.getDeclaredMethod(methodName);
        connectMethod.insertBefore("" +
                "if(((info.chandika.sql.logger.agent.instrumentation.ThreadLocalFlags.ToggleBool)(info.chandika.sql.logger.agent.instrumentation.ThreadLocalFlags#isFirstCall.get())).getAndToggle()){\n" +
                "    return  new info.chandika.sql.logger.agent.connection.LoggableConnection(this." + methodName + "($$));\n" +
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
}
