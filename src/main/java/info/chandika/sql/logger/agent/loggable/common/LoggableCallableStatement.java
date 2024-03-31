package info.chandika.sql.logger.agent.loggable.common;

import info.chandika.sql.logger.agent.cache.ExecutionWrapper;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.Map;

/**
 * @author : chandika
 * @since : 2022-07-06(Wed) 11:21
 **/


public class LoggableCallableStatement extends AbstractStatement implements CallableStatement {

    private final CallableStatement cs;
    private final String sql;

    public LoggableCallableStatement(CallableStatement cs) {
        this(cs, "N/A");
    }

    public LoggableCallableStatement(CallableStatement cs, String sql) {
        this.cs = cs;
        this.sql = sql;
    }

    @Override
    public void registerOutParameter(int parameterIndex, int sqlType) throws SQLException {
        cs.registerOutParameter(parameterIndex, sqlType);
    }


    @Override
    public void registerOutParameter(int parameterIndex, int sqlType, int scale) throws SQLException {
        cs.registerOutParameter(parameterIndex, sqlType, scale);
    }


    @Override
    public boolean wasNull() throws SQLException {
        return cs.wasNull();
    }


    @Override
    public String getString(int parameterIndex) throws SQLException {
        return cs.getString(parameterIndex);
    }


    @Override
    public boolean getBoolean(int parameterIndex) throws SQLException {
        return cs.getBoolean(parameterIndex);
    }


    @Override
    public byte getByte(int parameterIndex) throws SQLException {
        return cs.getByte(parameterIndex);
    }


    @Override
    public short getShort(int parameterIndex) throws SQLException {
        return cs.getShort(parameterIndex);
    }


    @Override
    public int getInt(int parameterIndex) throws SQLException {
        return cs.getInt(parameterIndex);
    }


    @Override
    public long getLong(int parameterIndex) throws SQLException {
        return cs.getLong(parameterIndex);
    }


    @Override
    public float getFloat(int parameterIndex) throws SQLException {
        return cs.getFloat(parameterIndex);
    }


    @Override
    public double getDouble(int parameterIndex) throws SQLException {
        return cs.getDouble(parameterIndex);
    }


    @Override
    public BigDecimal getBigDecimal(int parameterIndex, int scale) throws SQLException {
        return cs.getBigDecimal(parameterIndex, scale);
    }


    @Override
    public byte[] getBytes(int parameterIndex) throws SQLException {
        return cs.getBytes(parameterIndex);
    }


    @Override
    public Date getDate(int parameterIndex) throws SQLException {
        return cs.getDate(parameterIndex);
    }


    @Override
    public Time getTime(int parameterIndex) throws SQLException {
        return cs.getTime(parameterIndex);
    }


    @Override
    public Timestamp getTimestamp(int parameterIndex) throws SQLException {
        return cs.getTimestamp(parameterIndex);
    }


    @Override
    public Object getObject(int parameterIndex) throws SQLException {
        return cs.getObject(parameterIndex);
    }


    @Override
    public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {
        return cs.getBigDecimal(parameterIndex);
    }


    @Override
    public Object getObject(int parameterIndex, Map<String, Class<?>> map) throws SQLException {
        return cs.getObject(parameterIndex, map);
    }


    @Override
    public Ref getRef(int parameterIndex) throws SQLException {
        return cs.getRef(parameterIndex);
    }


    @Override
    public Blob getBlob(int parameterIndex) throws SQLException {
        return cs.getBlob(parameterIndex);
    }


    @Override
    public Clob getClob(int parameterIndex) throws SQLException {
        return cs.getClob(parameterIndex);
    }


    @Override
    public Array getArray(int parameterIndex) throws SQLException {
        return cs.getArray(parameterIndex);
    }


    @Override
    public Date getDate(int parameterIndex, Calendar cal) throws SQLException {
        return cs.getDate(parameterIndex, cal);
    }


    @Override
    public Time getTime(int parameterIndex, Calendar cal) throws SQLException {
        return cs.getTime(parameterIndex, cal);
    }


    @Override
    public Timestamp getTimestamp(int parameterIndex, Calendar cal) throws SQLException {
        return cs.getTimestamp(parameterIndex, cal);
    }


    @Override
    public void registerOutParameter(int parameterIndex, int sqlType, String typeName) throws SQLException {
        cs.registerOutParameter(parameterIndex, sqlType, typeName);
    }


    @Override
    public void registerOutParameter(String parameterName, int sqlType) throws SQLException {
        cs.registerOutParameter(parameterName, sqlType);
    }


    @Override
    public void registerOutParameter(String parameterName, int sqlType, int scale) throws SQLException {
        cs.registerOutParameter(parameterName, sqlType, scale);
    }


    @Override
    public void registerOutParameter(String parameterName, int sqlType, String typeName) throws SQLException {
        cs.registerOutParameter(parameterName, sqlType, typeName);
    }


    @Override
    public URL getURL(int parameterIndex) throws SQLException {
        return cs.getURL(parameterIndex);
    }


    @Override
    public void setURL(String parameterName, URL val) throws SQLException {
        storeData(parameterName, val);
        cs.setURL(parameterName, val);
    }


    @Override
    public void setNull(String parameterName, int sqlType) throws SQLException {
        storeData(parameterName, null);
        cs.setNull(parameterName, sqlType);
    }


    @Override
    public void setBoolean(String parameterName, boolean x) throws SQLException {
        storeData(parameterName, x);
        cs.setBoolean(parameterName, x);
    }


    @Override
    public void setByte(String parameterName, byte x) throws SQLException {
        storeData(parameterName, x);
        cs.setByte(parameterName, x);
    }


    @Override
    public void setShort(String parameterName, short x) throws SQLException {
        storeData(parameterName, x);
        cs.setShort(parameterName, x);
    }


    @Override
    public void setInt(String parameterName, int x) throws SQLException {
        storeData(parameterName, x);
        cs.setInt(parameterName, x);
    }


    @Override
    public void setLong(String parameterName, long x) throws SQLException {
        storeData(parameterName, x);
        cs.setLong(parameterName, x);
    }


    @Override
    public void setFloat(String parameterName, float x) throws SQLException {
        storeData(parameterName, x);
        cs.setFloat(parameterName, x);
    }


    @Override
    public void setDouble(String parameterName, double x) throws SQLException {
        storeData(parameterName, x);
        cs.setDouble(parameterName, x);
    }


    @Override
    public void setBigDecimal(String parameterName, BigDecimal x) throws SQLException {
        storeData(parameterName, x);
        cs.setBigDecimal(parameterName, x);
    }


    @Override
    public void setString(String parameterName, String x) throws SQLException {
        storeData(parameterName, x);
        cs.setString(parameterName, x);
    }


    @Override
    public void setBytes(String parameterName, byte[] x) throws SQLException {
        storeData(parameterName, x);
        cs.setBytes(parameterName, x);
    }


    @Override
    public void setDate(String parameterName, Date x) throws SQLException {
        storeData(parameterName, x);
        cs.setDate(parameterName, x);
    }


    @Override
    public void setTime(String parameterName, Time x) throws SQLException {
        storeData(parameterName, x);
        cs.setTime(parameterName, x);
    }


    @Override
    public void setTimestamp(String parameterName, Timestamp x) throws SQLException {
        storeData(parameterName, x);
        cs.setTimestamp(parameterName, x);
    }


    @Override
    public void setAsciiStream(String parameterName, InputStream x, int length) throws SQLException {
        storeData(parameterName, x);
        cs.setAsciiStream(parameterName, x, length);
    }


    @Override
    public void setBinaryStream(String parameterName, InputStream x, int length) throws SQLException {
        storeData(parameterName, x);
        cs.setBinaryStream(parameterName, x, length);
    }


    @Override
    public void setObject(String parameterName, Object x, int targetSqlType, int scale) throws SQLException {
        storeData(parameterName, x);
        cs.setObject(parameterName, x, targetSqlType, scale);
    }


    @Override
    public void setObject(String parameterName, Object x, int targetSqlType) throws SQLException {
        storeData(parameterName, x);
        cs.setObject(parameterName, x, targetSqlType);
    }


    @Override
    public void setObject(String parameterName, Object x) throws SQLException {
        storeData(parameterName, x);
        cs.setObject(parameterName, x);
    }


    @Override
    public void setCharacterStream(String parameterName, Reader reader, int length) throws SQLException {
        storeData(parameterName, reader);
        cs.setCharacterStream(parameterName, reader, length);
    }


    @Override
    public void setDate(String parameterName, Date x, Calendar cal) throws SQLException {
        storeData(parameterName, x);
        cs.setDate(parameterName, x, cal);
    }


    @Override
    public void setTime(String parameterName, Time x, Calendar cal) throws SQLException {
        storeData(parameterName, x);
        cs.setTime(parameterName, x, cal);
    }


    @Override
    public void setTimestamp(String parameterName, Timestamp x, Calendar cal) throws SQLException {
        storeData(parameterName, x);
        cs.setTimestamp(parameterName, x, cal);
    }


    @Override
    public void setNull(String parameterName, int sqlType, String typeName) throws SQLException {
        storeData(parameterName, null);
        cs.setNull(parameterName, sqlType, typeName);
    }


    @Override
    public String getString(String parameterName) throws SQLException {
        return cs.getString(parameterName);
    }


    @Override
    public boolean getBoolean(String parameterName) throws SQLException {
        return cs.getBoolean(parameterName);
    }


    @Override
    public byte getByte(String parameterName) throws SQLException {
        return cs.getByte(parameterName);
    }


    @Override
    public short getShort(String parameterName) throws SQLException {
        return cs.getShort(parameterName);
    }


    @Override
    public int getInt(String parameterName) throws SQLException {
        return cs.getInt(parameterName);
    }


    @Override
    public long getLong(String parameterName) throws SQLException {
        return cs.getLong(parameterName);
    }


    @Override
    public float getFloat(String parameterName) throws SQLException {
        return cs.getFloat(parameterName);
    }


    @Override
    public double getDouble(String parameterName) throws SQLException {
        return cs.getDouble(parameterName);
    }


    @Override
    public byte[] getBytes(String parameterName) throws SQLException {
        return cs.getBytes(parameterName);
    }


    @Override
    public Date getDate(String parameterName) throws SQLException {
        return cs.getDate(parameterName);
    }


    @Override
    public Time getTime(String parameterName) throws SQLException {
        return cs.getTime(parameterName);
    }


    @Override
    public Timestamp getTimestamp(String parameterName) throws SQLException {
        return cs.getTimestamp(parameterName);
    }


    @Override
    public Object getObject(String parameterName) throws SQLException {
        return cs.getObject(parameterName);
    }


    @Override
    public BigDecimal getBigDecimal(String parameterName) throws SQLException {
        return cs.getBigDecimal(parameterName);
    }


    @Override
    public Object getObject(String parameterName, Map<String, Class<?>> map) throws SQLException {
        return cs.getObject(parameterName, map);
    }


    @Override
    public Ref getRef(String parameterName) throws SQLException {
        return cs.getRef(parameterName);
    }


    @Override
    public Blob getBlob(String parameterName) throws SQLException {
        return cs.getBlob(parameterName);
    }


    @Override
    public Clob getClob(String parameterName) throws SQLException {
        return cs.getClob(parameterName);
    }


    @Override
    public Array getArray(String parameterName) throws SQLException {
        return cs.getArray(parameterName);
    }


    @Override
    public Date getDate(String parameterName, Calendar cal) throws SQLException {
        return cs.getDate(parameterName, cal);
    }


    @Override
    public Time getTime(String parameterName, Calendar cal) throws SQLException {
        return cs.getTime(parameterName, cal);
    }


    @Override
    public Timestamp getTimestamp(String parameterName, Calendar cal) throws SQLException {
        return cs.getTimestamp(parameterName, cal);
    }


    @Override
    public URL getURL(String parameterName) throws SQLException {
        return cs.getURL(parameterName);
    }


    @Override
    public RowId getRowId(int parameterIndex) throws SQLException {
        return cs.getRowId(parameterIndex);
    }


    @Override
    public RowId getRowId(String parameterName) throws SQLException {
        return cs.getRowId(parameterName);
    }


    @Override
    public void setRowId(String parameterName, RowId x) throws SQLException {
        storeData(parameterName, x);
        cs.setRowId(parameterName, x);
    }


    @Override
    public void setNString(String parameterName, String value) throws SQLException {
        storeData(parameterName, value);
        cs.setNString(parameterName, value);
    }


    @Override
    public void setNCharacterStream(String parameterName, Reader value, long length) throws SQLException {
        storeData(parameterName, value);
        cs.setNCharacterStream(parameterName, value, length);
    }


    @Override
    public void setNClob(String parameterName, NClob value) throws SQLException {
        storeData(parameterName, value);
        cs.setNClob(parameterName, value);
    }


    @Override
    public void setClob(String parameterName, Reader reader, long length) throws SQLException {
        storeData(parameterName, reader);
        cs.setClob(parameterName, reader, length);
    }


    @Override
    public void setBlob(String parameterName, InputStream inputStream, long length) throws SQLException {
        storeData(parameterName, inputStream);
        cs.setBlob(parameterName, inputStream, length);
    }


    @Override
    public void setNClob(String parameterName, Reader reader, long length) throws SQLException {
        storeData(parameterName, reader);
        cs.setNClob(parameterName, reader, length);
    }


    @Override
    public NClob getNClob(int parameterIndex) throws SQLException {
        return cs.getNClob(parameterIndex);
    }


    @Override
    public NClob getNClob(String parameterName) throws SQLException {
        return cs.getNClob(parameterName);
    }


    @Override
    public void setSQLXML(String parameterName, SQLXML xmlObject) throws SQLException {
        storeData(parameterName, xmlObject);
        cs.setSQLXML(parameterName, xmlObject);
    }


    @Override
    public SQLXML getSQLXML(int parameterIndex) throws SQLException {
        return cs.getSQLXML(parameterIndex);
    }


    @Override
    public SQLXML getSQLXML(String parameterName) throws SQLException {
        return cs.getSQLXML(parameterName);
    }


    @Override
    public String getNString(int parameterIndex) throws SQLException {
        return cs.getNString(parameterIndex);
    }


    @Override
    public String getNString(String parameterName) throws SQLException {
        return cs.getNString(parameterName);
    }


    @Override
    public Reader getNCharacterStream(int parameterIndex) throws SQLException {
        return cs.getNCharacterStream(parameterIndex);
    }


    @Override
    public Reader getNCharacterStream(String parameterName) throws SQLException {
        return cs.getNCharacterStream(parameterName);
    }


    @Override
    public Reader getCharacterStream(int parameterIndex) throws SQLException {
        return cs.getCharacterStream(parameterIndex);
    }


    @Override
    public Reader getCharacterStream(String parameterName) throws SQLException {
        return cs.getCharacterStream(parameterName);
    }


    @Override
    public void setBlob(String parameterName, Blob x) throws SQLException {
        storeData(parameterName, x);
        cs.setBlob(parameterName, x);
    }


    @Override
    public void setClob(String parameterName, Clob x) throws SQLException {
        storeData(parameterName, x);
        cs.setClob(parameterName, x);
    }


    @Override
    public void setAsciiStream(String parameterName, InputStream x, long length) throws SQLException {
        storeData(parameterName, x);
        cs.setAsciiStream(parameterName, x, length);
    }


    @Override
    public void setBinaryStream(String parameterName, InputStream x, long length) throws SQLException {
        storeData(parameterName, x);
        cs.setBinaryStream(parameterName, x, length);
    }


    @Override
    public void setCharacterStream(String parameterName, Reader reader, long length) throws SQLException {
        storeData(parameterName, reader);
        cs.setCharacterStream(parameterName, reader, length);
    }


    @Override
    public void setAsciiStream(String parameterName, InputStream x) throws SQLException {
        storeData(parameterName, x);
        cs.setAsciiStream(parameterName, x);
    }


    @Override
    public void setBinaryStream(String parameterName, InputStream x) throws SQLException {
        storeData(parameterName, x);
        cs.setBinaryStream(parameterName, x);
    }


    @Override
    public void setCharacterStream(String parameterName, Reader reader) throws SQLException {
        storeData(parameterName, reader);
        cs.setCharacterStream(parameterName, reader);
    }


    @Override
    public void setNCharacterStream(String parameterName, Reader value) throws SQLException {
        storeData(parameterName, value);
        cs.setNCharacterStream(parameterName, value);
    }


    @Override
    public void setClob(String parameterName, Reader reader) throws SQLException {
        storeData(parameterName, reader);
        cs.setClob(parameterName, reader);
    }


    @Override
    public void setBlob(String parameterName, InputStream inputStream) throws SQLException {
        storeData(parameterName, inputStream);
        cs.setBlob(parameterName, inputStream);
    }


    @Override
    public void setNClob(String parameterName, Reader reader) throws SQLException {
        storeData(parameterName, reader);
        cs.setNClob(parameterName, reader);
    }


    @Override
    public <T> T getObject(int parameterIndex, Class<T> type) throws SQLException {
        return cs.getObject(parameterIndex, type);
    }


    @Override
    public <T> T getObject(String parameterName, Class<T> type) throws SQLException {
        return cs.getObject(parameterName, type);
    }


    @Override
    public ResultSet executeQuery() throws SQLException {
        return ExecutionWrapper.executeRs(this, sql, () -> cs.executeQuery());
    }


    @Override
    public int executeUpdate() throws SQLException {
        return ExecutionWrapper.execute(this, sql, () -> cs.executeUpdate());
    }


    @Override
    public void setNull(int parameterIndex, int sqlType) throws SQLException {
        storeData(parameterIndex, null);
        cs.setNull(parameterIndex, sqlType);
    }


    @Override
    public void setBoolean(int parameterIndex, boolean x) throws SQLException {
        storeData(parameterIndex, x);
        cs.setBoolean(parameterIndex, x);
    }


    @Override
    public void setByte(int parameterIndex, byte x) throws SQLException {
        storeData(parameterIndex, x);
        cs.setByte(parameterIndex, x);
    }


    @Override
    public void setShort(int parameterIndex, short x) throws SQLException {
        storeData(parameterIndex, x);
        cs.setShort(parameterIndex, x);
    }


    @Override
    public void setInt(int parameterIndex, int x) throws SQLException {
        storeData(parameterIndex, x);
        cs.setInt(parameterIndex, x);
    }


    @Override
    public void setLong(int parameterIndex, long x) throws SQLException {
        storeData(parameterIndex, x);
        cs.setLong(parameterIndex, x);
    }


    @Override
    public void setFloat(int parameterIndex, float x) throws SQLException {
        storeData(parameterIndex, x);
        cs.setFloat(parameterIndex, x);
    }


    @Override
    public void setDouble(int parameterIndex, double x) throws SQLException {
        storeData(parameterIndex, x);
        cs.setDouble(parameterIndex, x);
    }


    @Override
    public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
        storeData(parameterIndex, x);
        cs.setBigDecimal(parameterIndex, x);
    }


    @Override
    public void setString(int parameterIndex, String x) throws SQLException {
        storeData(parameterIndex, x);
        cs.setString(parameterIndex, x);
    }


    @Override
    public void setBytes(int parameterIndex, byte[] x) throws SQLException {
        storeData(parameterIndex, x);
        cs.setBytes(parameterIndex, x);
    }


    @Override
    public void setDate(int parameterIndex, Date x) throws SQLException {
        storeData(parameterIndex, x);
        cs.setDate(parameterIndex, x);
    }


    @Override
    public void setTime(int parameterIndex, Time x) throws SQLException {
        storeData(parameterIndex, x);
        cs.setTime(parameterIndex, x);
    }


    @Override
    public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
        storeData(parameterIndex, x);
        cs.setTimestamp(parameterIndex, x);
    }


    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
        storeData(parameterIndex, x);
        cs.setAsciiStream(parameterIndex, x, length);
    }


    @Override
    public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
        storeData(parameterIndex, x);
        cs.setUnicodeStream(parameterIndex, x, length);
    }


    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
        storeData(parameterIndex, x);
        cs.setBinaryStream(parameterIndex, x, length);
    }


    @Override
    public void clearParameters() throws SQLException {
        cs.clearParameters();
    }


    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
        storeData(parameterIndex, x);
        cs.setObject(parameterIndex, x, targetSqlType);
    }


    @Override
    public void setObject(int parameterIndex, Object x) throws SQLException {
        storeData(parameterIndex, x);
        cs.setObject(parameterIndex, x);
    }


    @Override
    public boolean execute() throws SQLException {
        return ExecutionWrapper.execute(this, sql, () -> cs.execute());
    }


    @Override
    public void addBatch() throws SQLException {
        super.addNewBatch();
        cs.addBatch();
    }


    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
        storeData(parameterIndex, reader);
        cs.setCharacterStream(parameterIndex, reader, length);
    }


    @Override
    public void setRef(int parameterIndex, Ref x) throws SQLException {
        storeData(parameterIndex, x);
        cs.setRef(parameterIndex, x);
    }


    @Override
    public void setBlob(int parameterIndex, Blob x) throws SQLException {
        storeData(parameterIndex, x);
        cs.setBlob(parameterIndex, x);
    }


    @Override
    public void setClob(int parameterIndex, Clob x) throws SQLException {
        storeData(parameterIndex, x);
        cs.setClob(parameterIndex, x);
    }


    @Override
    public void setArray(int parameterIndex, Array x) throws SQLException {
        storeData(parameterIndex, x);
        cs.setArray(parameterIndex, x);
    }


    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return cs.getMetaData();
    }


    @Override
    public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
        storeData(parameterIndex, x);
        cs.setDate(parameterIndex, x, cal);
    }


    @Override
    public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
        storeData(parameterIndex, x);
        cs.setTime(parameterIndex, x, cal);
    }


    @Override
    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
        storeData(parameterIndex, x);
        cs.setTimestamp(parameterIndex, x, cal);
    }


    @Override
    public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
        storeData(parameterIndex, null);
        cs.setNull(parameterIndex, sqlType, typeName);
    }


    @Override
    public void setURL(int parameterIndex, URL x) throws SQLException {
        storeData(parameterIndex, x);
        cs.setURL(parameterIndex, x);
    }


    @Override
    public ParameterMetaData getParameterMetaData() throws SQLException {
        return cs.getParameterMetaData();
    }


    @Override
    public void setRowId(int parameterIndex, RowId x) throws SQLException {
        storeData(parameterIndex, x);
        cs.setRowId(parameterIndex, x);
    }


    @Override
    public void setNString(int parameterIndex, String value) throws SQLException {
        storeData(parameterIndex, value);
        cs.setNString(parameterIndex, value);
    }


    @Override
    public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
        storeData(parameterIndex, value);
        cs.setNCharacterStream(parameterIndex, value, length);
    }


    @Override
    public void setNClob(int parameterIndex, NClob value) throws SQLException {
        storeData(parameterIndex, value);
        cs.setNClob(parameterIndex, value);
    }


    @Override
    public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
        storeData(parameterIndex, reader);
        cs.setClob(parameterIndex, reader, length);
    }


    @Override
    public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
        storeData(parameterIndex, inputStream);
        cs.setBlob(parameterIndex, inputStream, length);
    }


    @Override
    public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
        storeData(parameterIndex, reader);
        cs.setNClob(parameterIndex, reader, length);
    }


    @Override
    public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
        storeData(parameterIndex, xmlObject);
        cs.setSQLXML(parameterIndex, xmlObject);
    }


    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
        storeData(parameterIndex, x);
        cs.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
    }


    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
        storeData(parameterIndex, x);
        cs.setAsciiStream(parameterIndex, x, length);
    }


    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
        storeData(parameterIndex, x);
        cs.setBinaryStream(parameterIndex, x, length);
    }


    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
        storeData(parameterIndex, reader);
        cs.setCharacterStream(parameterIndex, reader, length);
    }


    @Override
    public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
        storeData(parameterIndex, x);
        cs.setAsciiStream(parameterIndex, x);
    }


    @Override
    public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
        storeData(parameterIndex, x);
        cs.setBinaryStream(parameterIndex, x);
    }


    @Override
    public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
        storeData(parameterIndex, reader);
        cs.setCharacterStream(parameterIndex, reader);
    }


    @Override
    public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
        storeData(parameterIndex, value);
        cs.setNCharacterStream(parameterIndex, value);
    }


    @Override
    public void setClob(int parameterIndex, Reader reader) throws SQLException {
        storeData(parameterIndex, reader);
        cs.setClob(parameterIndex, reader);
    }


    @Override
    public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
        storeData(parameterIndex, inputStream);
        cs.setBlob(parameterIndex, inputStream);
    }


    @Override
    public void setNClob(int parameterIndex, Reader reader) throws SQLException {
        storeData(parameterIndex, reader);
        cs.setNClob(parameterIndex, reader);
    }


    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        return ExecutionWrapper.executeRs(this, sql, () -> cs.executeQuery(sql));
    }


    @Override
    public int executeUpdate(String sql) throws SQLException {
        return ExecutionWrapper.execute(this, sql, () -> cs.executeUpdate(sql));
    }


    @Override
    public void close() throws SQLException {
        cs.close();
    }


    @Override
    public int getMaxFieldSize() throws SQLException {
        return cs.getMaxFieldSize();
    }


    @Override
    public void setMaxFieldSize(int max) throws SQLException {
        cs.setMaxFieldSize(max);
    }


    @Override
    public int getMaxRows() throws SQLException {
        return cs.getMaxRows();
    }


    @Override
    public void setMaxRows(int max) throws SQLException {
        cs.setMaxRows(max);
    }


    @Override
    public void setEscapeProcessing(boolean enable) throws SQLException {
        cs.setEscapeProcessing(enable);
    }


    @Override
    public int getQueryTimeout() throws SQLException {
        return cs.getQueryTimeout();
    }


    @Override
    public void setQueryTimeout(int seconds) throws SQLException {
        cs.setQueryTimeout(seconds);
    }


    @Override
    public void cancel() throws SQLException {
        cs.cancel();
    }


    @Override
    public SQLWarning getWarnings() throws SQLException {
        return cs.getWarnings();
    }


    @Override
    public void clearWarnings() throws SQLException {
        cs.clearWarnings();
    }


    @Override
    public void setCursorName(String name) throws SQLException {
        cs.setCursorName(name);
    }


    @Override
    public boolean execute(String sql) throws SQLException {
        return ExecutionWrapper.execute(this, sql, () -> cs.execute(sql));
    }


    @Override
    public ResultSet getResultSet() throws SQLException {
        return cs.getResultSet();
    }


    @Override
    public int getUpdateCount() throws SQLException {
        return cs.getUpdateCount();
    }


    @Override
    public boolean getMoreResults() throws SQLException {
        return cs.getMoreResults();
    }


    @Override
    public void setFetchDirection(int direction) throws SQLException {
        cs.setFetchDirection(direction);
    }


    @Override
    public int getFetchDirection() throws SQLException {
        return cs.getFetchDirection();
    }


    @Override
    public void setFetchSize(int rows) throws SQLException {
        cs.setFetchSize(rows);
    }


    @Override
    public int getFetchSize() throws SQLException {
        return cs.getFetchSize();
    }


    @Override
    public int getResultSetConcurrency() throws SQLException {
        return cs.getResultSetConcurrency();
    }


    @Override
    public int getResultSetType() throws SQLException {
        return cs.getResultSetType();
    }


    @Override
    public void addBatch(String sql) throws SQLException {
        cs.addBatch(sql);
    }


    @Override
    public void clearBatch() throws SQLException {
        cs.clearBatch();
    }


    @Override
    public int[] executeBatch() throws SQLException {
        return ExecutionWrapper.execute(this, sql, () -> cs.executeBatch());
    }


    @Override
    public Connection getConnection() throws SQLException {
        return cs.getConnection();
    }


    @Override
    public boolean getMoreResults(int current) throws SQLException {
        return cs.getMoreResults(current);
    }


    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        return cs.getGeneratedKeys();
    }


    @Override
    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        return ExecutionWrapper.execute(this, sql, () -> cs.executeUpdate(sql, autoGeneratedKeys));
    }


    @Override
    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
        return ExecutionWrapper.execute(this, sql, () -> cs.executeUpdate(sql, columnIndexes));
    }


    @Override
    public int executeUpdate(String sql, String[] columnNames) throws SQLException {
        return ExecutionWrapper.execute(this, sql, () -> cs.executeUpdate(sql, columnNames));
    }


    @Override
    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        return ExecutionWrapper.execute(this, sql, () -> cs.execute(sql, autoGeneratedKeys));
    }


    @Override
    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        return ExecutionWrapper.execute(this, sql, () -> cs.execute(sql, columnIndexes));
    }


    @Override
    public boolean execute(String sql, String[] columnNames) throws SQLException {
        return ExecutionWrapper.execute(this, sql, () -> cs.execute(sql, columnNames));
    }


    @Override
    public int getResultSetHoldability() throws SQLException {
        return cs.getResultSetHoldability();
    }


    @Override
    public boolean isClosed() throws SQLException {
        return cs.isClosed();
    }


    @Override
    public void setPoolable(boolean poolable) throws SQLException {
        cs.setPoolable(poolable);
    }


    @Override
    public boolean isPoolable() throws SQLException {
        return cs.isPoolable();
    }


    @Override
    public void closeOnCompletion() throws SQLException {
        cs.closeOnCompletion();
    }


    @Override
    public boolean isCloseOnCompletion() throws SQLException {
        return cs.isCloseOnCompletion();
    }


    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return cs.unwrap(iface);
    }


    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return cs.isWrapperFor(iface);
    }


    @Override
    public void setObject(String parameterName, Object x, SQLType targetSqlType, int scaleOrLength) throws SQLException {
        storeData(parameterName, x);
        cs.setObject(parameterName, x, targetSqlType, scaleOrLength);
    }


    @Override
    public void setObject(String parameterName, Object x, SQLType targetSqlType) throws SQLException {
        storeData(parameterName, x);
        cs.setObject(parameterName, x, targetSqlType);
    }


    @Override
    public void registerOutParameter(int parameterIndex, SQLType sqlType) throws SQLException {
        cs.registerOutParameter(parameterIndex, sqlType);
    }


    @Override
    public void registerOutParameter(int parameterIndex, SQLType sqlType, int scale) throws SQLException {
        cs.registerOutParameter(parameterIndex, sqlType, scale);
    }


    @Override
    public void registerOutParameter(int parameterIndex, SQLType sqlType, String typeName) throws SQLException {
        cs.registerOutParameter(parameterIndex, sqlType, typeName);
    }


    @Override
    public void registerOutParameter(String parameterName, SQLType sqlType) throws SQLException {
        cs.registerOutParameter(parameterName, sqlType);
    }


    @Override
    public void registerOutParameter(String parameterName, SQLType sqlType, int scale) throws SQLException {
        cs.registerOutParameter(parameterName, sqlType, scale);
    }


    @Override
    public void registerOutParameter(String parameterName, SQLType sqlType, String typeName) throws SQLException {
        cs.registerOutParameter(parameterName, sqlType, typeName);
    }


    @Override
    public void setObject(int parameterIndex, Object x, SQLType targetSqlType, int scaleOrLength) throws SQLException {
        storeData(parameterIndex, x);
        cs.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
    }


    @Override
    public void setObject(int parameterIndex, Object x, SQLType targetSqlType) throws SQLException {
        storeData(parameterIndex, x);
        cs.setObject(parameterIndex, x, targetSqlType);
    }


    @Override
    public long executeLargeUpdate() throws SQLException {
        return ExecutionWrapper.execute(this, sql, () -> cs.executeLargeUpdate());
    }


    @Override
    public long getLargeUpdateCount() throws SQLException {
        return cs.getLargeUpdateCount();
    }


    @Override
    public void setLargeMaxRows(long max) throws SQLException {
        cs.setLargeMaxRows(max);
    }


    @Override
    public long getLargeMaxRows() throws SQLException {
        return cs.getLargeMaxRows();
    }


    @Override
    public long[] executeLargeBatch() throws SQLException {
        return ExecutionWrapper.execute(this, sql, () -> cs.executeLargeBatch());
    }


    @Override
    public long executeLargeUpdate(String sql) throws SQLException {
        return ExecutionWrapper.execute(this, sql, () -> cs.executeLargeUpdate(sql));
    }


    @Override
    public long executeLargeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        return ExecutionWrapper.execute(this, sql, () -> cs.executeLargeUpdate(sql, autoGeneratedKeys));
    }


    @Override
    public long executeLargeUpdate(String sql, int[] columnIndexes) throws SQLException {
        return ExecutionWrapper.execute(this, sql, () -> cs.executeLargeUpdate(sql, columnIndexes));
    }


    @Override
    public long executeLargeUpdate(String sql, String[] columnNames) throws SQLException {
        return ExecutionWrapper.execute(this, sql, () -> cs.executeLargeUpdate(sql, columnNames));
    }


    @Override
    public String enquoteLiteral(String val) throws SQLException {
        return cs.enquoteLiteral(val);
    }


    @Override
    public String enquoteIdentifier(String identifier, boolean alwaysQuote) throws SQLException {
        return cs.enquoteIdentifier(identifier, alwaysQuote);
    }


    @Override
    public boolean isSimpleIdentifier(String identifier) throws SQLException {
        return cs.isSimpleIdentifier(identifier);
    }

}
