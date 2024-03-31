package info.chandika.sql.logger.agent.instrumentation.transformer.impl;

import info.chandika.sql.logger.agent.instrumentation.transformer.AbstractDriverTransformer;
import info.chandika.sql.logger.agent.instrumentation.transformer.DriverType;

import java.util.logging.Logger;

/**
 * @author : chandika
 * @since : 2024-03-27(Wed) 20:24
 **/
public class H2DriverTransformer extends AbstractDriverTransformer {
    private static final Logger LOGGER = Logger.getLogger(H2DriverTransformer.class.getName());

    private H2DriverTransformer() {
    }

    private static class SingletonHelper {
        private static final H2DriverTransformer INSTANCE = new H2DriverTransformer();
    }

    public static H2DriverTransformer getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public String getClassName() {
        return DriverType.H2.getClassName();
    }
}
