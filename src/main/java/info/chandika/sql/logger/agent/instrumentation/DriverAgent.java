package info.chandika.sql.logger.agent.instrumentation;

import info.chandika.sql.logger.agent.instrumentation.transformer.DriverType;

import java.lang.instrument.Instrumentation;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author : chandika
 * @since : 2023-07-31(Mon) 23:46
 * <p>
 * Ref: https://www.baeldung.com/java-instrumentation
 **/
public class DriverAgent {
    private static final Logger LOGGER = Logger.getLogger(DriverAgent.class.getName());

    // -javaagent:jar
    public static void premain(
            String agentArgs, Instrumentation inst) {

        LOGGER.info("[Agent] In premain method");
        transformClass(inst);
    }

   // attach while running
    public static void agentmain(
            String agentArgs, Instrumentation inst) {

        LOGGER.info("[Agent] In agentmain method");
        transformClass(inst);
    }

    private static void transformClass(Instrumentation instrumentation) {
        DriverType.init(instrumentation);

        LOGGER.log(Level.INFO, "Adding driver transformer");
        instrumentation.addTransformer(CompositeDriverTransformer.getInstance(), true);

        for (DriverType driverType : DriverType.values()) {
            if (driverType.isAvailable()) {
                try {
                    LOGGER.log(Level.INFO, "Re-transforming - {0}", new Object[]{driverType.getClassName()});
                    instrumentation.retransformClasses(driverType.getClazz());
                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, "Error while re-transform", e);
                }
            }
        }

    }
}
