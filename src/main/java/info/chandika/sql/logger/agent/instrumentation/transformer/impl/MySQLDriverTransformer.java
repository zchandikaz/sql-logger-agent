package info.chandika.sql.logger.agent.instrumentation.transformer.impl;

import info.chandika.sql.logger.agent.instrumentation.transformer.AbstractDriverTransformer;
import info.chandika.sql.logger.agent.instrumentation.transformer.DriverType;

import java.util.logging.Logger;

/**
 * @author : chandika
 * @since : 2024-03-27(Wed) 20:24
 **/
public class MySQLDriverTransformer extends AbstractDriverTransformer {
    private static final Logger LOGGER = Logger.getLogger(MySQLDriverTransformer.class.getName());

    private MySQLDriverTransformer() {
    }

    private static class SingletonHelper {
        private static final MySQLDriverTransformer INSTANCE = new MySQLDriverTransformer();
    }

    public static MySQLDriverTransformer getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public String getClassName() {
        return DriverType.MYSQL.getClassName();
    }
}
