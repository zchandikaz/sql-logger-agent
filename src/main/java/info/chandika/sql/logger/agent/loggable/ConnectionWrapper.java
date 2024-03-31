package info.chandika.sql.logger.agent.loggable;

import info.chandika.sql.logger.agent.loggable.common.LoggableConnection;
import info.chandika.sql.logger.agent.loggable.oracle.LoggableOracleConnection;
import oracle.jdbc.internal.OracleConnection;

import java.sql.Connection;

public class ConnectionWrapper {
    private ConnectionWrapper(){}
    public static Connection wrap(Connection connection){
        if(connection==null){
            return null;
        }else if(connection instanceof OracleConnection) {
            return new LoggableOracleConnection((OracleConnection)connection);
        }
        return new LoggableConnection(connection);
    }
}
