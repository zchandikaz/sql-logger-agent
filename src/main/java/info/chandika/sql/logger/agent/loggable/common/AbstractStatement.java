package info.chandika.sql.logger.agent.loggable.common;

import info.chandika.sql.logger.agent.logger.ConnectionLogger;
import info.chandika.sql.logger.agent.util.Configs;
import info.chandika.sql.logger.agent.util.StatementData;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author : chandika
 * @since : 2022-07-19(Tue) 21:06
 **/


public abstract class AbstractStatement {
    private static final Logger LOGGER = Logger.getLogger(AbstractStatement.class.getName());
    public static String EMPTY_BATCH_PREFIX = "[-] ";
    public static String TEST_SQL = "select 1";
    private Map<String, String> richSqlMap = new HashMap<>();


    private List<Map<Integer, StatementData>> statementDataWithParamIndexList = Configs.IS_DATA_STORED_STATEMENT_ENABLED ? new ArrayList<>() : null;
    private List<Map<String, StatementData>> statementDataWithParamNameList = Configs.IS_DATA_STORED_STATEMENT_ENABLED ? new ArrayList<>() : null;
    int currentBatchIndex = -1;

    protected void addNewBatch() throws SQLException {
        if (Configs.IS_DATA_STORED_STATEMENT_ENABLED) {
            currentBatchIndex++;
            statementDataWithParamIndexList.add(new HashMap<>());
            statementDataWithParamNameList.add(new HashMap<>());
        }
    }

    void checkBatch() {
        try {
            if (currentBatchIndex == -1) {
                addNewBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void storeData(String parameterName, Object data) {
        checkBatch();
        if (Configs.IS_DATA_STORED_STATEMENT_ENABLED) {
            statementDataWithParamNameList.get(currentBatchIndex).put(parameterName, new StatementData(data == null ? null : data.getClass(), data));
        }
    }

    public void storeData(String parameterName, Class<?> dataType, Object data) {
        checkBatch();
        if (Configs.IS_DATA_STORED_STATEMENT_ENABLED) {
            statementDataWithParamNameList.get(currentBatchIndex).put(parameterName, new StatementData(dataType, data));
        }
    }

    public void storeData(int parameterIndex, Object data) {
        checkBatch();
        if (Configs.IS_DATA_STORED_STATEMENT_ENABLED) {
            statementDataWithParamIndexList.get(currentBatchIndex).put(parameterIndex, new StatementData(data == null ? null : data.getClass(), data));
        }
    }

    public void storeData(int parameterIndex, Class<?> dataType, Object data) {
        checkBatch();
        if (Configs.IS_DATA_STORED_STATEMENT_ENABLED) {
            statementDataWithParamIndexList.get(currentBatchIndex).put(parameterIndex, new StatementData(dataType, data));
        }
    }

    public void log(String sql) {
        if (!Configs.TEST_CALL_ENABLED && sql.equals(TEST_SQL)) {
            return;
        }
        if (sql != null) {
            if (statementDataWithParamIndexList == null) {
                ConnectionLogger.logSQL(sql);
            } else {
                if (statementDataWithParamIndexList.isEmpty()) {
                    ConnectionLogger.logSQL(EMPTY_BATCH_PREFIX + sql);
                }
                for (var batchIndex = 0; batchIndex < statementDataWithParamIndexList.size(); batchIndex++) {
                    if (Configs.IS_DATA_STORED_STATEMENT_ENABLED) {
                        sql = getSqlWithData(sql, batchIndex);
                    }
                    ConnectionLogger.logSQL(sql);
                }
            }
        }
    }

    public String getSqlWithData(String sql, int batchIndex) {
        return richSqlMap.computeIfAbsent(batchIndex + "_" + sql, key -> getSqlWithDataOriginal(sql, batchIndex));
    }

    private String getParameterDataSql(int batchIndex, int parameterIndex) {
        try {
            return statementDataWithParamIndexList.get(batchIndex).get(parameterIndex).getSqlValue();
        } catch (Exception e) {
            LOGGER.log(Level.FINE, "", e);
            return "!#";
        }
    }

    private String getParameterDataSql(int batchIndex, String paramName) {
        try {
            return statementDataWithParamNameList.get(batchIndex).get(paramName).getSqlValue();
        } catch (Exception e) {
            LOGGER.log(Level.FINE, "", e);
            return "!#";
        }
    }

    private String getSqlWithDataOriginal(String sql, int batchIndex) {
        try {
            String oroginalSql = sql;
            int i = 1;
            while (oroginalSql.indexOf("?") != -1) {
                int index = oroginalSql.indexOf("?");
                String partBefore = oroginalSql.substring(0, index);
                String partAfter = oroginalSql.substring(index + "?".length());
                oroginalSql = partBefore + " " + getParameterDataSql(batchIndex, i) + " " + partAfter;
                i = i + 1;
            }
            for (String paramName : statementDataWithParamNameList.get(batchIndex).keySet()) {
                oroginalSql = oroginalSql.replaceAll(":" + paramName, getParameterDataSql(batchIndex, paramName));
            }
            return oroginalSql;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ERROR IN GENERATING SQL";
    }

    public int getBatchCount() {
        return Configs.IS_DATA_STORED_STATEMENT_ENABLED ? statementDataWithParamNameList.size() : -1;
    }
}
