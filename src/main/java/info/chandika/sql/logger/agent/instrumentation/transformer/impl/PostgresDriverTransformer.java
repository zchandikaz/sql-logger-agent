package info.chandika.sql.logger.agent.instrumentation.transformer.impl;

import info.chandika.sql.logger.agent.instrumentation.transformer.AbstractDriverTransformer;
import info.chandika.sql.logger.agent.instrumentation.transformer.DriverType;

import java.util.logging.Logger;

/**
 * @author : chandika
 * @since : 2024-03-27(Wed) 20:24
 **/
public class PostgresDriverTransformer extends AbstractDriverTransformer {
    private static final Logger LOGGER = Logger.getLogger(PostgresDriverTransformer.class.getName());

    private PostgresDriverTransformer() {
    }

    private static class SingletonHelper {
        private static final PostgresDriverTransformer INSTANCE = new PostgresDriverTransformer();
    }

    public static PostgresDriverTransformer getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public String getClassName() {
        return DriverType.POSTGRES.getClassName();
    }
}
