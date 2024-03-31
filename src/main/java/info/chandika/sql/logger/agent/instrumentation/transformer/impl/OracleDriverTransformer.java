package info.chandika.sql.logger.agent.instrumentation.transformer.impl;

import info.chandika.sql.logger.agent.instrumentation.transformer.AbstractDriverTransformer;
import info.chandika.sql.logger.agent.instrumentation.transformer.DriverType;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import org.ietf.jgss.GSSCredential;

import java.security.ProtectionDomain;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * @author : chandika
 * @since : 2024-03-27(Wed) 20:24
 **/
public class OracleDriverTransformer extends AbstractDriverTransformer {
    private static final Logger LOGGER = Logger.getLogger(OracleDriverTransformer.class.getName());

    private OracleDriverTransformer() {
    }

    private static class SingletonHelper {
        private static final OracleDriverTransformer INSTANCE = new OracleDriverTransformer();
    }

    public static OracleDriverTransformer getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public String getClassName() {
        return DriverType.ORACLE.getClassName();
    }

    @Override
    public CtMethod getConnectMethod(ClassPool cp, CtClass cc) throws NotFoundException {
        return cc.getDeclaredMethod(
                getConnectMethodName(),
                cp.get(new String[]{
                        String.class.getName(),
                        Properties.class.getName(),
                        GSSCredential.class.getName()
                })
        );
    }
}
