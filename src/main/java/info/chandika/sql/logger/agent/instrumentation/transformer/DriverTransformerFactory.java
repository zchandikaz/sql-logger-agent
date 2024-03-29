package info.chandika.sql.logger.agent.instrumentation.transformer;

import java.util.NoSuchElementException;

/**
 * @author : chandika
 * @since : 2024-03-27(Wed) 20:28
 **/
public class DriverTransformerFactory {
    public IDriverTransformer getTransformer(DriverType driverType) {
        if (driverType == DriverType.POSTGRES) {
            return new PostgresDriverTransformer();
        } else if (driverType == DriverType.ORACLE) {
            return new OracleDriverTransformer();
        }
        throw new RuntimeException("No implementation for " + driverType);
    }
}
