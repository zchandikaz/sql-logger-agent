package info.chandika.sql.logger.agent.instrumentation;

import info.chandika.sql.logger.agent.instrumentation.transformer.DriverTransformerFactory;
import info.chandika.sql.logger.agent.instrumentation.transformer.DriverType;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author : chandika
 * @since : 2023-07-30(Sun) 21:03
 **/
public class CompositeDriverTransformer implements ClassFileTransformer {
    private static final Logger LOGGER = Logger.getLogger(CompositeDriverTransformer.class.getName());

    private CompositeDriverTransformer() {
    }

    private static class SingletonHelper {
        private static final CompositeDriverTransformer INSTANCE = new CompositeDriverTransformer();
    }

    public static CompositeDriverTransformer getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        byte[] byteCode = classfileBuffer;

        for (DriverType driverType : DriverType.values()) {
            if (driverType.isAvailable()) {
                String targetClassName = driverType.getClassName();
                String finalTargetClassName = targetClassName.replaceAll("\\.", "/");
                if (className.equals(finalTargetClassName)) {
                    LOGGER.log(Level.INFO, "Transforming class {0}", new Object[]{targetClassName});
                    try {
                        byteCode = DriverTransformerFactory.getTransformer(driverType).apply(classBeingRedefined, protectionDomain, byteCode);
                    } catch (Exception e) {
                        LOGGER.log(Level.SEVERE, "Error while applying transformation", e);
                    }
                }
            }
        }
        return byteCode;
    }
}
