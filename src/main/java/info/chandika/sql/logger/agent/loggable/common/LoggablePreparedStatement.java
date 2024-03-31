package info.chandika.sql.logger.agent.loggable.common;

import info.chandika.sql.logger.agent.cache.ExecutionWrapper;
import info.chandika.sql.logger.agent.util.StatementData;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author : chandika
 * @since : 2022-07-06(Wed) 11:19
 **/


public class LoggablePreparedStatement extends AbstractStatement implements PreparedStatement {
    private final PreparedStatement ps;
    List<StatementData> dataList = new ArrayList<>();

    private final String sql;

    public LoggablePreparedStatement(PreparedStatement ps) {
        this(ps, "N/A");
    }

    public LoggablePreparedStatement(PreparedStatement ps, String sql) {
        this.ps = ps;
        this.sql = sql;
    }

    @Override
    public ResultSet executeQuery() throws SQLException {
        return ExecutionWrapper.executeRs(this, sql, () -> ps.executeQuery());
    }


    @Override
    public int executeUpdate() throws SQLException {
        return ExecutionWrapper.execute(this, sql, () -> ps.executeUpdate());
    }


    @Override
    public void setNull(int parameterIndex, int sqlType) throws SQLException {
        storeData(parameterIndex, null);
        ps.setNull(parameterIndex, sqlType);
    }


    @Override
    public void setBoolean(int parameterIndex, boolean x) throws SQLException {
        storeData(parameterIndex, x);
        ps.setBoolean(parameterIndex, x);
    }


    @Override
    public void setByte(int parameterIndex, byte x) throws SQLException {
        storeData(parameterIndex, x);
        ps.setByte(parameterIndex, x);
    }


    @Override
    public void setShort(int parameterIndex, short x) throws SQLException {
        storeData(parameterIndex, x);
        ps.setShort(parameterIndex, x);
    }


    @Override
    public void setInt(int parameterIndex, int x) throws SQLException {
        storeData(parameterIndex, x);
        ps.setInt(parameterIndex, x);
    }


    @Override
    public void setLong(int parameterIndex, long x) throws SQLException {
        storeData(parameterIndex, x);
        ps.setLong(parameterIndex, x);
    }


    @Override
    public void setFloat(int parameterIndex, float x) throws SQLException {
        storeData(parameterIndex, x);
        ps.setFloat(parameterIndex, x);
    }


    @Override
    public void setDouble(int parameterIndex, double x) throws SQLException {
        storeData(parameterIndex, x);
        ps.setDouble(parameterIndex, x);
    }


    @Override
    public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
        storeData(parameterIndex, x);
        ps.setBigDecimal(parameterIndex, x);
    }


    @Override
    public void setString(int parameterIndex, String x) throws SQLException {
        storeData(parameterIndex, x);
        ps.setString(parameterIndex, x);
    }


    @Override
    public void setBytes(int parameterIndex, byte[] x) throws SQLException {
        storeData(parameterIndex, x);
        ps.setBytes(parameterIndex, x);
    }


    @Override
    public void setDate(int parameterIndex, Date x) throws SQLException {
        storeData(parameterIndex, x);
        ps.setDate(parameterIndex, x);
    }


    @Override
    public void setTime(int parameterIndex, Time x) throws SQLException {
        storeData(parameterIndex, x);
        ps.setTime(parameterIndex, x);
    }


    @Override
    public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
        storeData(parameterIndex, x);
        ps.setTimestamp(parameterIndex, x);
    }


    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
        storeData(parameterIndex, x);
        ps.setAsciiStream(parameterIndex, x, length);
    }


    @Override
    public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
        storeData(parameterIndex, x);
        ps.setUnicodeStream(parameterIndex, x, length);
    }


    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
        storeData(parameterIndex, x);
        ps.setBinaryStream(parameterIndex, x, length);
    }


    @Override
    public void clearParameters() throws SQLException {
        ps.clearParameters();
    }


    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
        storeData(parameterIndex, x);
        ps.setObject(parameterIndex, x, targetSqlType);
    }


    @Override
    public void setObject(int parameterIndex, Object x) throws SQLException {
        storeData(parameterIndex, x);
        ps.setObject(parameterIndex, x);
    }


    @Override
    public boolean execute() throws SQLException {
        return ExecutionWrapper.execute(this, sql, () -> ps.execute());
    }


    @Override
    public void addBatch() throws SQLException {
        super.addNewBatch();
        ps.addBatch();
    }


    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
        storeData(parameterIndex, reader);
        ps.setCharacterStream(parameterIndex, reader, length);
    }


    @Override
    public void setRef(int parameterIndex, Ref x) throws SQLException {
        storeData(parameterIndex, x);
        ps.setRef(parameterIndex, x);
    }


    @Override
    public void setBlob(int parameterIndex, Blob x) throws SQLException {
        storeData(parameterIndex, x);
        ps.setBlob(parameterIndex, x);
    }


    @Override
    public void setClob(int parameterIndex, Clob x) throws SQLException {
        storeData(parameterIndex, x);
        ps.setClob(parameterIndex, x);
    }


    @Override
    public void setArray(int parameterIndex, Array x) throws SQLException {
        storeData(parameterIndex, x);
        ps.setArray(parameterIndex, x);
    }


    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return ps.getMetaData();
    }


    @Override
    public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
        storeData(parameterIndex, x);
        ps.setDate(parameterIndex, x, cal);
    }


    @Override
    public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
        storeData(parameterIndex, x);
        ps.setTime(parameterIndex, x, cal);
    }


    @Override
    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
        storeData(parameterIndex, x);
        ps.setTimestamp(parameterIndex, x, cal);
    }


    @Override
    public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
        storeData(parameterIndex, null);
        ps.setNull(parameterIndex, sqlType, typeName);
    }


    @Override
    public void setURL(int parameterIndex, URL x) throws SQLException {
        storeData(parameterIndex, x);
        ps.setURL(parameterIndex, x);
    }


    @Override
    public ParameterMetaData getParameterMetaData() throws SQLException {
        return ps.getParameterMetaData();
    }


    @Override
    public void setRowId(int parameterIndex, RowId x) throws SQLException {
        storeData(parameterIndex, x);
        ps.setRowId(parameterIndex, x);
    }


    @Override
    public void setNString(int parameterIndex, String value) throws SQLException {
        storeData(parameterIndex, value);
        ps.setNString(parameterIndex, value);
    }


    @Override
    public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
        storeData(parameterIndex, value);
        ps.setNCharacterStream(parameterIndex, value, length);
    }


    @Override
    public void setNClob(int parameterIndex, NClob value) throws SQLException {
        storeData(parameterIndex, value);
        ps.setNClob(parameterIndex, value);
    }


    @Override
    public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
        storeData(parameterIndex, reader);
        ps.setClob(parameterIndex, reader, length);
    }


    @Override
    public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
        storeData(parameterIndex, inputStream);
        ps.setBlob(parameterIndex, inputStream, length);
    }


    @Override
    public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
        storeData(parameterIndex, reader);
        ps.setNClob(parameterIndex, reader, length);
    }


    @Override
    public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
        storeData(parameterIndex, xmlObject);
        ps.setSQLXML(parameterIndex, xmlObject);
    }


    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
        storeData(parameterIndex, x);
        ps.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
    }


    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
        storeData(parameterIndex, x);
        ps.setAsciiStream(parameterIndex, x, length);
    }


    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
        storeData(parameterIndex, x);
        ps.setBinaryStream(parameterIndex, x, length);
    }


    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
        storeData(parameterIndex, reader);
        ps.setCharacterStream(parameterIndex, reader, length);
    }


    @Override
    public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
        storeData(parameterIndex, x);
        ps.setAsciiStream(parameterIndex, x);
    }


    @Override
    public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
        storeData(parameterIndex, x);
        ps.setBinaryStream(parameterIndex, x);
    }


    @Override
    public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
        storeData(parameterIndex, reader);
        ps.setCharacterStream(parameterIndex, reader);
    }


    @Override
    public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
        storeData(parameterIndex, value);
        ps.setNCharacterStream(parameterIndex, value);
    }


    @Override
    public void setClob(int parameterIndex, Reader reader) throws SQLException {
        storeData(parameterIndex, reader);
        ps.setClob(parameterIndex, reader);
    }


    @Override
    public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
        storeData(parameterIndex, inputStream);
        ps.setBlob(parameterIndex, inputStream);
    }


    @Override
    public void setNClob(int parameterIndex, Reader reader) throws SQLException {
        storeData(parameterIndex, reader);
        ps.setNClob(parameterIndex, reader);
    }


    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        return ExecutionWrapper.executeRs(this, sql, () -> ps.executeQuery(sql));
    }


    @Override
    public int executeUpdate(String sql) throws SQLException {
        return ExecutionWrapper.execute(this, sql, () -> ps.executeUpdate(sql));
    }


    @Override
    public void close() throws SQLException {
        ps.close();
    }


    @Override
    public int getMaxFieldSize() throws SQLException {
        return ps.getMaxFieldSize();
    }


    @Override
    public void setMaxFieldSize(int max) throws SQLException {
        ps.setMaxFieldSize(max);
    }


    @Override
    public int getMaxRows() throws SQLException {
        return ps.getMaxRows();
    }


    @Override
    public void setMaxRows(int max) throws SQLException {
        ps.setMaxRows(max);
    }


    @Override
    public void setEscapeProcessing(boolean enable) throws SQLException {
        ps.setEscapeProcessing(enable);
    }


    @Override
    public int getQueryTimeout() throws SQLException {
        return ps.getQueryTimeout();
    }


    @Override
    public void setQueryTimeout(int seconds) throws SQLException {
        ps.setQueryTimeout(seconds);
    }


    @Override
    public void cancel() throws SQLException {
        ps.cancel();
    }


    @Override
    public SQLWarning getWarnings() throws SQLException {
        return ps.getWarnings();
    }


    @Override
    public void clearWarnings() throws SQLException {
        ps.clearWarnings();
    }


    @Override
    public void setCursorName(String name) throws SQLException {
        ps.setCursorName(name);
    }


    @Override
    public boolean execute(String sql) throws SQLException {
        return ExecutionWrapper.execute(this, sql, () -> ps.execute(sql));
    }


    @Override
    public ResultSet getResultSet() throws SQLException {
        return ps.getResultSet();
    }


    @Override
    public int getUpdateCount() throws SQLException {
        return ps.getUpdateCount();
    }


    @Override
    public boolean getMoreResults() throws SQLException {
        return ps.getMoreResults();
    }


    @Override
    public void setFetchDirection(int direction) throws SQLException {
        ps.setFetchDirection(direction);
    }


    @Override
    public int getFetchDirection() throws SQLException {
        return ps.getFetchDirection();
    }


    @Override
    public void setFetchSize(int rows) throws SQLException {
        ps.setFetchSize(rows);
    }


    @Override
    public int getFetchSize() throws SQLException {
        return ps.getFetchSize();
    }


    @Override
    public int getResultSetConcurrency() throws SQLException {
        return ps.getResultSetConcurrency();
    }


    @Override
    public int getResultSetType() throws SQLException {
        return ps.getResultSetType();
    }


    @Override
    public void addBatch(String sql) throws SQLException {
        ps.addBatch(sql);
    }


    @Override
    public void clearBatch() throws SQLException {
        ps.clearBatch();
    }


    @Override
    public int[] executeBatch() throws SQLException {
        return ExecutionWrapper.execute(this, sql, () -> ps.executeBatch());
    }


    @Override
    public Connection getConnection() throws SQLException {
        return ps.getConnection();
    }


    @Override
    public boolean getMoreResults(int current) throws SQLException {
        return ps.getMoreResults(current);
    }


    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        return ps.getGeneratedKeys();
    }


    @Override
    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        return ExecutionWrapper.execute(this, sql, () -> ps.executeUpdate(sql, autoGeneratedKeys));
    }


    @Override
    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
        return ExecutionWrapper.execute(this, sql, () -> ps.executeUpdate(sql, columnIndexes));
    }


    @Override
    public int executeUpdate(String sql, String[] columnNames) throws SQLException {
        return ExecutionWrapper.execute(this, sql, () -> ps.executeUpdate(sql, columnNames));
    }


    @Override
    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        return ExecutionWrapper.execute(this, sql, () -> ps.execute(sql, autoGeneratedKeys));
    }


    @Override
    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        return ExecutionWrapper.execute(this, sql, () -> ps.execute(sql, columnIndexes));
    }


    @Override
    public boolean execute(String sql, String[] columnNames) throws SQLException {
        return ExecutionWrapper.execute(this, sql, () -> ps.execute(sql, columnNames));
    }


    @Override
    public int getResultSetHoldability() throws SQLException {
        return ps.getResultSetHoldability();
    }


    @Override
    public boolean isClosed() throws SQLException {
        return ps.isClosed();
    }


    @Override
    public void setPoolable(boolean poolable) throws SQLException {
        ps.setPoolable(poolable);
    }


    @Override
    public boolean isPoolable() throws SQLException {
        return ps.isPoolable();
    }


    @Override
    public void closeOnCompletion() throws SQLException {
        ps.closeOnCompletion();
    }


    @Override
    public boolean isCloseOnCompletion() throws SQLException {
        return ps.isCloseOnCompletion();
    }


    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return ps.unwrap(iface);
    }


    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return ps.isWrapperFor(iface);
    }


    @Override
    public void setObject(int parameterIndex, Object x, SQLType targetSqlType, int scaleOrLength) throws SQLException {
        storeData(parameterIndex, x);
        ps.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
    }


    @Override
    public void setObject(int parameterIndex, Object x, SQLType targetSqlType) throws SQLException {
        storeData(parameterIndex, x);
        ps.setObject(parameterIndex, x, targetSqlType);
    }


    @Override
    public long executeLargeUpdate() throws SQLException {
        return ExecutionWrapper.execute(this, sql, () -> ps.executeLargeUpdate());
    }


    @Override
    public long getLargeUpdateCount() throws SQLException {
        return ps.getLargeUpdateCount();
    }


    @Override
    public void setLargeMaxRows(long max) throws SQLException {
        ps.setLargeMaxRows(max);
    }


    @Override
    public long getLargeMaxRows() throws SQLException {
        return ps.getLargeMaxRows();
    }


    @Override
    public long[] executeLargeBatch() throws SQLException {
        return ExecutionWrapper.execute(this, sql, () -> ps.executeLargeBatch());
    }


    @Override
    public long executeLargeUpdate(String sql) throws SQLException {
        return ExecutionWrapper.execute(this, sql, () -> ps.executeLargeUpdate(sql));
    }


    @Override
    public long executeLargeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        return ExecutionWrapper.execute(this, sql, () -> ps.executeLargeUpdate(sql, autoGeneratedKeys));
    }


    @Override
    public long executeLargeUpdate(String sql, int[] columnIndexes) throws SQLException {
        return ExecutionWrapper.execute(this, sql, () -> ps.executeLargeUpdate(sql, columnIndexes));
    }


    @Override
    public long executeLargeUpdate(String sql, String[] columnNames) throws SQLException {
        return ExecutionWrapper.execute(this, sql, () -> ps.executeLargeUpdate(sql, columnNames));
    }


    @Override
    public String enquoteLiteral(String val) throws SQLException {
        return ps.enquoteLiteral(val);
    }


    @Override
    public String enquoteIdentifier(String identifier, boolean alwaysQuote) throws SQLException {
        return ps.enquoteIdentifier(identifier, alwaysQuote);
    }


    @Override
    public boolean isSimpleIdentifier(String identifier) throws SQLException {
        return ps.isSimpleIdentifier(identifier);
    }
}
