package info.chandika.sql.logger.agent.instrumentation.transformer;

import info.chandika.sql.logger.agent.instrumentation.transformer.impl.H2DriverTransformer;
import info.chandika.sql.logger.agent.instrumentation.transformer.impl.PostgresDriverTransformer;

/**
 * @author : chandika
 * @since : 2024-03-27(Wed) 20:28
 **/
public class DriverTransformerFactory {
    private DriverTransformerFactory(){}

    public static AbstractDriverTransformer getTransformer(DriverType driverType) {
        if (driverType == DriverType.POSTGRES) {
            return PostgresDriverTransformer.getInstance();
        } else if (driverType == DriverType.H2) {
            return H2DriverTransformer.getInstance();
        }
        throw new RuntimeException("No implementation for " + driverType);
    }
}
