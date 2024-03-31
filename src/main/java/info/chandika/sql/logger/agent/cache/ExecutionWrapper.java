package info.chandika.sql.logger.agent.cache;

import info.chandika.sql.logger.agent.lang.SqlSupplier;
import info.chandika.sql.logger.agent.loggable.common.AbstractStatement;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author : chandika
 * @since : 2022-09-24(Sat) 20:51
 **/
public class ExecutionWrapper {

    public static ResultSet executeRs(AbstractStatement statement, String sql, SqlSupplier<ResultSet> resultSetSqlSupplier) throws SQLException {
        var startTime = System.currentTimeMillis();
        try {
            return resultSetSqlSupplier.get();
        } finally {
            statement.log(sql + " ::: " + (System.currentTimeMillis() - startTime));
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T execute(AbstractStatement statement, String sql, SqlSupplier<T> resultSetSqlSupplier) throws SQLException {
        var startTime = System.currentTimeMillis();
        try {
            return resultSetSqlSupplier.get();
        } finally {
            statement.log(sql + " ::: " + (System.currentTimeMillis() - startTime));
        }
    }

    public static void execute(AbstractStatement statement, String sql, Runnable resultSetSqlSupplier) {
        var startTime = System.currentTimeMillis();
        try {
            resultSetSqlSupplier.run();
        } finally {
            statement.log(sql + " ::: " + (System.currentTimeMillis() - startTime));
        }
    }

}
