package info.chandika.sql.logger.agent.cache;

import com.github.vldrus.sql.rowset.CachedRowSetWrapper;
import info.chandika.sql.logger.agent.cache.redis.RedisMap;
import info.chandika.sql.logger.agent.lang.SqlSupplier;
import info.chandika.sql.logger.agent.lang.Throwing;
import info.chandika.sql.logger.agent.statement.AbstractStatement;
import info.chandika.sql.logger.agent.util.Configs;

import javax.sql.rowset.CachedRowSet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author : chandika
 * @since : 2022-09-24(Sat) 20:51
 **/
public class ExecutionWrapper {
    private static final Map<String, ResultSet> resultMap = new RedisMap();
    private static final String KEY_PREFIX = "result-set-cache:" + Configs.APP_NAME + ":";

    public static ResultSet executeRs(AbstractStatement statement, String sql, SqlSupplier<ResultSet> resultSetSqlSupplier) throws SQLException {
        var startTime = System.currentTimeMillis();
        try {
            if (Configs.RESULT_CACHING_AVAILABLE && (statement.getBatchCount() == 0 || statement.getBatchCount() == 1) && !isIgnoreSQL(sql)) {
                var sqlWithData = statement.getBatchCount() == 0 ? sql : statement.getSqlWithData(sql, 0);
                var rs = (CachedRowSet) resultMap.computeIfAbsent(KEY_PREFIX + sqlWithData, k -> {
                    try {
                        try (var actualResultSet = resultSetSqlSupplier.get()) {
                            var cachedRowSet = new CachedRowSetWrapper();
                            cachedRowSet.populate(actualResultSet);
                            return cachedRowSet;
                        }
                    } catch (Exception e) {
                        Throwing.sneakyThrow(e);
                        return null;
                    }
                });
                return rs.createCopy();
            } else {
                return resultSetSqlSupplier.get();
            }
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

    private static boolean isIgnoreSQL(String sql) {
        for (var is : Configs.RESULT_CACHING_IGNORE_SQL) {
            if (sql.startsWith(is))
                return true;
        }
        return false;
    }
}
