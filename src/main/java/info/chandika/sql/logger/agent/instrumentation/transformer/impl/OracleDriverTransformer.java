package info.chandika.sql.logger.agent.instrumentation.transformer.impl;

import info.chandika.sql.logger.agent.instrumentation.transformer.AbstractDriverTransformer;
import info.chandika.sql.logger.agent.instrumentation.transformer.DriverType;

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
}
