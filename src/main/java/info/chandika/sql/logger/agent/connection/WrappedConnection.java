package info.chandika.sql.logger.agent.connection;

import info.chandika.sql.logger.agent.logger.ConnectionLogger;
import info.chandika.sql.logger.agent.statement.WrappedCallableStatement;
import info.chandika.sql.logger.agent.statement.WrappedPreparedStatement;
import info.chandika.sql.logger.agent.statement.WrappedStatement;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @author : chandika
 * @since : 2022-07-06(Wed) 10:46
 **/


public class WrappedConnection implements Connection {
    private final Connection con;

    public WrappedConnection(Connection con) {
        this.con = con;
    }

    private Statement wrap(Statement st) {
        return new WrappedStatement(st);
    }

    private PreparedStatement wrap(PreparedStatement ps, String sql) {
        return new WrappedPreparedStatement(ps, sql);
    }

    private CallableStatement wrap(CallableStatement cs, String sql) {
        return new WrappedCallableStatement(cs, sql);
    }

    @Override
    public Statement createStatement() throws SQLException {
        return wrap(con.createStatement());
    }


    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return wrap(con.prepareStatement(sql), sql);
    }


    @Override
    public CallableStatement prepareCall(String sql) throws SQLException {
        return wrap(con.prepareCall(sql), sql);
    }


    @Override
    public String nativeSQL(String sql) throws SQLException {
        return con.nativeSQL(sql);
    }


    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        con.setAutoCommit(autoCommit);
    }


    @Override
    public boolean getAutoCommit() throws SQLException {
        return con.getAutoCommit();
    }


    @Override
    public void commit() throws SQLException {
        con.commit();
    }


    @Override
    public void rollback() throws SQLException {
        con.rollback();
    }


    @Override
    public void close() throws SQLException {
        ConnectionLogger.logEvent(ConnectionLogger.ConnectionEvent.CLOSE);
        con.close();
    }


    @Override
    public boolean isClosed() throws SQLException {
        return con.isClosed();
    }


    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return con.getMetaData();
    }


    @Override
    public void setReadOnly(boolean readOnly) throws SQLException {
        con.setReadOnly(readOnly);
    }


    @Override
    public boolean isReadOnly() throws SQLException {
        return con.isReadOnly();
    }


    @Override
    public void setCatalog(String catalog) throws SQLException {
        con.setCatalog(catalog);
    }


    @Override
    public String getCatalog() throws SQLException {
        return con.getCatalog();
    }


    @Override
    public void setTransactionIsolation(int level) throws SQLException {
        con.setTransactionIsolation(level);
    }


    @Override
    public int getTransactionIsolation() throws SQLException {
        return con.getTransactionIsolation();
    }


    @Override
    public SQLWarning getWarnings() throws SQLException {
        return con.getWarnings();
    }


    @Override
    public void clearWarnings() throws SQLException {
        con.clearWarnings();
    }


    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return wrap(con.createStatement(resultSetType, resultSetConcurrency));
    }


    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return wrap(con.prepareStatement(sql, resultSetType, resultSetConcurrency), sql);
    }


    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return wrap(con.prepareCall(sql, resultSetType, resultSetConcurrency), sql);
    }


    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return con.getTypeMap();
    }


    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        con.setTypeMap(map);
    }


    @Override
    public void setHoldability(int holdability) throws SQLException {
        con.setHoldability(holdability);
    }


    @Override
    public int getHoldability() throws SQLException {
        return con.getHoldability();
    }


    @Override
    public Savepoint setSavepoint() throws SQLException {
        return con.setSavepoint();
    }


    @Override
    public Savepoint setSavepoint(String name) throws SQLException {
        return con.setSavepoint(name);
    }


    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
        con.rollback(savepoint);
    }


    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        con.releaseSavepoint(savepoint);
    }


    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return wrap(con.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability));
    }


    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return wrap(con.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability), sql);
    }


    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return wrap(con.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability), sql);
    }


    @Override
    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return wrap(con.prepareStatement(sql, autoGeneratedKeys), sql);
    }


    @Override
    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        return wrap(con.prepareStatement(sql, columnIndexes), sql);
    }


    @Override
    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        return wrap(con.prepareStatement(sql, columnNames), sql);
    }


    @Override
    public Clob createClob() throws SQLException {
        return con.createClob();
    }


    @Override
    public Blob createBlob() throws SQLException {
        return con.createBlob();
    }


    @Override
    public NClob createNClob() throws SQLException {
        return con.createNClob();
    }


    @Override
    public SQLXML createSQLXML() throws SQLException {
        return con.createSQLXML();
    }


    @Override
    public boolean isValid(int timeout) throws SQLException {
        return con.isValid(timeout);
    }


    @Override
    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        con.setClientInfo(name, value);
    }


    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        con.setClientInfo(properties);
    }


    @Override
    public String getClientInfo(String name) throws SQLException {
        return con.getClientInfo(name);
    }


    @Override
    public Properties getClientInfo() throws SQLException {
        return con.getClientInfo();
    }


    @Override
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        return con.createArrayOf(typeName, elements);
    }


    @Override
    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        return con.createStruct(typeName, attributes);
    }


    @Override
    public void setSchema(String schema) throws SQLException {
        con.setSchema(schema);
    }


    @Override
    public String getSchema() throws SQLException {
        return con.getSchema();
    }


    @Override
    public void abort(Executor executor) throws SQLException {
        con.abort(executor);
    }


    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        con.setNetworkTimeout(executor, milliseconds);
    }


    @Override
    public int getNetworkTimeout() throws SQLException {
        return con.getNetworkTimeout();
    }


    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return con.unwrap(iface);
    }


    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return con.isWrapperFor(iface);
    }


    @Override
    public void beginRequest() throws SQLException {
        con.beginRequest();
    }


    @Override
    public void endRequest() throws SQLException {
        con.endRequest();
    }


    @Override
    public boolean setShardingKeyIfValid(ShardingKey shardingKey, ShardingKey superShardingKey, int timeout) throws SQLException {
        return con.setShardingKeyIfValid(shardingKey, superShardingKey, timeout);
    }


    @Override
    public boolean setShardingKeyIfValid(ShardingKey shardingKey, int timeout) throws SQLException {
        return con.setShardingKeyIfValid(shardingKey, timeout);
    }


    @Override
    public void setShardingKey(ShardingKey shardingKey, ShardingKey superShardingKey) throws SQLException {
        con.setShardingKey(shardingKey, superShardingKey);
    }


    @Override
    public void setShardingKey(ShardingKey shardingKey) throws SQLException {
        con.setShardingKey(shardingKey);
    }
}
