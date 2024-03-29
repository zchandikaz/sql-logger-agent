package info.chandika.sql.logger.agent;

import info.chandika.sql.logger.agent.connection.WrappedConnection;
import info.chandika.sql.logger.agent.logger.ConnectionLogger;
import info.chandika.sql.logger.agent.util.Configs;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author : chandika
 * @since : 2022-07-06(Wed) 10:41
 **/


public class WrapPostgresDriver extends org.postgresql.Driver {
    @Override
    public @org.checkerframework.checker.nullness.qual.Nullable Connection connect(String url, @org.checkerframework.checker.nullness.qual.Nullable Properties info) throws SQLException {
        if (Configs.WRAP_DRIVER_ENABLED) {
            ConnectionLogger.logEvent(ConnectionLogger.ConnectionEvent.OPEN);
            return new WrappedConnection(super.connect(url, info));
        } else {
            return super.connect(url, info);
        }
    }
}
