package info.chandika.sql.logger.agent.loggable.oracle;

import info.chandika.sql.logger.agent.cache.ExecutionWrapper;
import info.chandika.sql.logger.agent.loggable.common.AbstractStatement;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleDataFactory;
import oracle.jdbc.OracleParameterMetaData;
import oracle.jdbc.dcn.DatabaseChangeRegistration;
import oracle.sql.ARRAY;
import oracle.sql.BFILE;
import oracle.sql.BINARY_DOUBLE;
import oracle.sql.BINARY_FLOAT;
import oracle.sql.BLOB;
import oracle.sql.CHAR;
import oracle.sql.CLOB;
import oracle.sql.CustomDatum;
import oracle.sql.CustomDatumFactory;
import oracle.sql.DATE;
import oracle.sql.Datum;
import oracle.sql.INTERVALDS;
import oracle.sql.INTERVALYM;
import oracle.sql.NUMBER;
import oracle.sql.OPAQUE;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.RAW;
import oracle.sql.REF;
import oracle.sql.ROWID;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import oracle.sql.TIMESTAMP;
import oracle.sql.TIMESTAMPLTZ;
import oracle.sql.TIMESTAMPTZ;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

/**
 * @author : chandika
 * @since : 2022-07-06(Wed) 11:21
 **/


public class LoggableOracleCallableStatement extends AbstractStatement implements OracleCallableStatement {

    private final OracleCallableStatement cs;
    private final String sql;

    public LoggableOracleCallableStatement(OracleCallableStatement cs) {
        this(cs, "N/A");
    }

    public LoggableOracleCallableStatement(OracleCallableStatement cs, String sql) {
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

    @Override
    public ARRAY getARRAY(int i) throws SQLException {
        return cs.getARRAY(i);
    }

    @Override
    public InputStream getAsciiStream(int i) throws SQLException {
        return cs.getAsciiStream(i);
    }

    @Override
    public BFILE getBFILE(int i) throws SQLException {
        return cs.getBFILE(i);
    }

    @Override
    public BFILE getBfile(int i) throws SQLException {
        return cs.getBfile(i);
    }

    @Override
    public InputStream getBinaryStream(int i) throws SQLException {
        return cs.getBinaryStream(i);
    }

    @Override
    public InputStream getBinaryStream(String s) throws SQLException {
        return cs.getBinaryStream(s);
    }

    @Override
    public BLOB getBLOB(int i) throws SQLException {
        return cs.getBLOB(i);
    }

    @Override
    public CHAR getCHAR(int i) throws SQLException {
        return cs.getCHAR(i);
    }

    @Override
    public CLOB getCLOB(int i) throws SQLException {
        return cs.getCLOB(i);
    }

    @Override
    public ResultSet getCursor(int i) throws SQLException {
        return cs.getCursor(i);
    }

    @Override
    public Object getCustomDatum(int i, CustomDatumFactory customDatumFactory) throws SQLException {
        return cs.getCustomDatum(i, customDatumFactory);
    }

    @Override
    public Object getORAData(int i, ORADataFactory oraDataFactory) throws SQLException {
        return cs.getORAData(i, oraDataFactory);
    }

    @Override
    public Object getObject(int i, OracleDataFactory oracleDataFactory) throws SQLException {
        return cs.getObject(i, oracleDataFactory);
    }

    @Override
    public Object getAnyDataEmbeddedObject(int i) throws SQLException {
        return cs.getAnyDataEmbeddedObject(i);
    }

    @Override
    public DATE getDATE(int i) throws SQLException {
        return cs.getDATE(i);
    }

    @Override
    public NUMBER getNUMBER(int i) throws SQLException {
        return cs.getNUMBER(i);
    }

    @Override
    public OPAQUE getOPAQUE(int i) throws SQLException {
        return cs.getOPAQUE(i);
    }

    @Override
    public Datum getOracleObject(int i) throws SQLException {
        return cs.getOracleObject(i);
    }

    @Override
    public RAW getRAW(int i) throws SQLException {
        return cs.getRAW(i);
    }

    @Override
    public REF getREF(int i) throws SQLException {
        return cs.getREF(i);
    }

    @Override
    public ROWID getROWID(int i) throws SQLException {
        return cs.getROWID(i);
    }

    @Override
    public STRUCT getSTRUCT(int i) throws SQLException {
        return cs.getSTRUCT(i);
    }

    @Override
    public INTERVALYM getINTERVALYM(int i) throws SQLException {
        return cs.getINTERVALYM(i);
    }

    @Override
    public INTERVALDS getINTERVALDS(int i) throws SQLException {
        return cs.getINTERVALDS(i);
    }

    @Override
    public TIMESTAMP getTIMESTAMP(int i) throws SQLException {
        return cs.getTIMESTAMP(i);
    }

    @Override
    public TIMESTAMPTZ getTIMESTAMPTZ(int i) throws SQLException {
        return cs.getTIMESTAMPTZ(i);
    }

    @Override
    public TIMESTAMPLTZ getTIMESTAMPLTZ(int i) throws SQLException {
        return cs.getTIMESTAMPLTZ(i);
    }

    @Override
    public InputStream getUnicodeStream(int i) throws SQLException {
        return cs.getUnicodeStream(i);
    }

    @Override
    public InputStream getUnicodeStream(String s) throws SQLException {
        return cs.getUnicodeStream(s);
    }

    @Override
    public void registerOutParameter(int i, int i1, int i2, int i3) throws SQLException {
        cs.registerOutParameter(i, i1, i2, i3);
    }

    @Override
    public void registerOutParameterBytes(int i, int i1, int i2, int i3) throws SQLException {
        cs.registerOutParameterBytes(i, i1, i2, i3);
    }

    @Override
    public void registerOutParameterChars(int i, int i1, int i2, int i3) throws SQLException {
        cs.registerOutParameterChars(i, i1, i2, i3);
    }

    @Override
    public int sendBatch() throws SQLException {
        return cs.sendBatch();
    }

    @Override
    public void setExecuteBatch(int i) throws SQLException {
        cs.setExecuteBatch(i);
    }

    @Override
    @Deprecated
    public Object getPlsqlIndexTable(int i) throws SQLException {
        return cs.getPlsqlIndexTable(i);
    }

    @Override
    @Deprecated
    public Object getPlsqlIndexTable(int i, Class aClass) throws SQLException {
        return cs.getPlsqlIndexTable(i, aClass);
    }

    @Override
    @Deprecated
    public Datum[] getOraclePlsqlIndexTable(int i) throws SQLException {
        return cs.getOraclePlsqlIndexTable(i);
    }

    @Override
    @Deprecated
    public void registerIndexTableOutParameter(int i, int i1, int i2, int i3) throws SQLException {
        cs.registerIndexTableOutParameter(i, i1, i2, i3);
    }

    @Override
    public void setBinaryFloat(String s, BINARY_FLOAT binaryFloat) throws SQLException {
        cs.setBinaryFloat(s, binaryFloat);
    }

    @Override
    public void setBinaryDouble(String s, BINARY_DOUBLE binaryDouble) throws SQLException {
        cs.setBinaryDouble(s, binaryDouble);
    }

    @Override
    public void setStringForClob(String s, String s1) throws SQLException {
        cs.setStringForClob(s, s1);
    }

    @Override
    public void setBytesForBlob(String s, byte[] bytes) throws SQLException {
        cs.setBytesForBlob(s, bytes);
    }

    @Override
    public void registerOutParameter(String s, int i, int i1, int i2) throws SQLException {
        cs.registerOutParameter(s, i, i1, i2);
    }

    @Override
    public void setBinaryFloat(String s, float v) throws SQLException {
        cs.setBinaryFloat(s, v);
    }

    @Override
    public void setBinaryDouble(String s, double v) throws SQLException {
        cs.setBinaryDouble(s, v);
    }

    @Override
    public void setFixedCHAR(String s, String s1) throws SQLException {
        cs.setFixedCHAR(s, s1);
    }

    @Override
    public void setCursor(String s, ResultSet resultSet) throws SQLException {
        cs.setCursor(s, resultSet);
    }

    @Override
    public void setROWID(String s, ROWID rowid) throws SQLException {
        cs.setROWID(s, rowid);
    }

    @Override
    public void setRAW(String s, RAW raw) throws SQLException {
        cs.setRAW(s, raw);
    }

    @Override
    public void setCHAR(String s, CHAR aChar) throws SQLException {
        cs.setCHAR(s, aChar);
    }

    @Override
    public void setDATE(String s, DATE date) throws SQLException {
        cs.setDATE(s, date);
    }

    @Override
    public void setNUMBER(String s, NUMBER number) throws SQLException {
        cs.setNUMBER(s, number);
    }

    @Override
    public void setBLOB(String s, BLOB blob) throws SQLException {
        cs.setBLOB(s, blob);
    }

    @Override
    public void setCLOB(String s, CLOB clob) throws SQLException {
        cs.setCLOB(s, clob);
    }

    @Override
    public void setBFILE(String s, BFILE bfile) throws SQLException {
        cs.setBFILE(s, bfile);
    }

    @Override
    public void setBfile(String s, BFILE bfile) throws SQLException {
        cs.setBfile(s, bfile);
    }

    @Override
    public void setINTERVALYM(String s, INTERVALYM intervalym) throws SQLException {
        cs.setINTERVALYM(s, intervalym);
    }

    @Override
    public void setINTERVALDS(String s, INTERVALDS intervalds) throws SQLException {
        cs.setINTERVALDS(s, intervalds);
    }

    @Override
    public void setTIMESTAMP(String s, TIMESTAMP timestamp) throws SQLException {
        cs.setTIMESTAMP(s, timestamp);
    }

    @Override
    public void setTIMESTAMPTZ(String s, TIMESTAMPTZ timestamptz) throws SQLException {
        cs.setTIMESTAMPTZ(s, timestamptz);
    }

    @Override
    public void setTIMESTAMPLTZ(String s, TIMESTAMPLTZ timestampltz) throws SQLException {
        cs.setTIMESTAMPLTZ(s, timestampltz);
    }

    @Override
    public void setUnicodeStream(String s, InputStream inputStream, int i) throws SQLException {
        cs.setUnicodeStream(s, inputStream, i);
    }

    @Override
    public void setArray(String s, Array array) throws SQLException {
        cs.setArray(s, array);
    }

    @Override
    public void setARRAY(String s, ARRAY array) throws SQLException {
        cs.setARRAY(s, array);
    }

    @Override
    public void setOPAQUE(String s, OPAQUE opaque) throws SQLException {
        cs.setOPAQUE(s, opaque);
    }

    @Override
    public void setStructDescriptor(String s, StructDescriptor structDescriptor) throws SQLException {
        cs.setStructDescriptor(s, structDescriptor);
    }

    @Override
    public void setSTRUCT(String s, STRUCT struct) throws SQLException {
        cs.setSTRUCT(s, struct);
    }

    @Override
    public void setCustomDatum(String s, CustomDatum customDatum) throws SQLException {
        cs.setCustomDatum(s, customDatum);
    }

    @Override
    public void setORAData(String s, ORAData oraData) throws SQLException {
        cs.setORAData(s, oraData);
    }

    @Override
    public void setRefType(String s, REF ref) throws SQLException {
        cs.setRefType(s, ref);
    }

    @Override
    public void setRef(String s, Ref ref) throws SQLException {
        cs.setRef(s, ref);
    }

    @Override
    public void setREF(String s, REF ref) throws SQLException {
        cs.setREF(s, ref);
    }

    @Override
    public void setOracleObject(String s, Datum datum) throws SQLException {
        cs.setOracleObject(s, datum);
    }

    @Override
    public void registerOutParameterAtName(String s, int i) throws SQLException {
        cs.registerOutParameterAtName(s, i);
    }

    @Override
    public void registerOutParameterAtName(String s, int i, int i1) throws SQLException {
        cs.registerOutParameterAtName(s, i, i1);
    }

    @Override
    public void registerOutParameterAtName(String s, int i, String s1) throws SQLException {
        cs.registerOutParameterAtName(s, i, s1);
    }

    @Override
    public String enquoteNCharLiteral(String val) throws SQLException {
        return cs.enquoteNCharLiteral(val);
    }

    @Override
    public void defineParameterTypeBytes(int i, int i1, int i2) throws SQLException {
        cs.defineParameterTypeBytes(i, i1, i2);
    }

    @Override
    public void defineParameterTypeChars(int i, int i1, int i2) throws SQLException {
        cs.defineParameterTypeChars(i, i1, i2);
    }

    @Override
    public void defineParameterType(int i, int i1, int i2) throws SQLException {
        cs.defineParameterType(i, i1, i2);
    }

    @Override
    public int getExecuteBatch() {
        return cs.getExecuteBatch();
    }

    @Override
    public void setARRAY(int i, ARRAY array) throws SQLException {
        cs.setARRAY(i, array);
    }

    @Override
    public void setBfile(int i, BFILE bfile) throws SQLException {
        cs.setBfile(i, bfile);
    }

    @Override
    public void setBFILE(int i, BFILE bfile) throws SQLException {
        cs.setBFILE(i, bfile);
    }

    @Override
    public void setBLOB(int i, BLOB blob) throws SQLException {
        cs.setBLOB(i, blob);
    }

    @Override
    public void setCHAR(int i, CHAR aChar) throws SQLException {
        cs.setCHAR(i, aChar);
    }

    @Override
    public void setCLOB(int i, CLOB clob) throws SQLException {
        cs.setCLOB(i, clob);
    }

    @Override
    public void setCursor(int i, ResultSet resultSet) throws SQLException {
        cs.setCursor(i, resultSet);
    }

    @Override
    public void setCustomDatum(int i, CustomDatum customDatum) throws SQLException {
        cs.setCustomDatum(i, customDatum);
    }

    @Override
    public void setORAData(int i, ORAData oraData) throws SQLException {
        cs.setORAData(i, oraData);
    }

    @Override
    public void setDATE(int i, DATE date) throws SQLException {
        cs.setDATE(i, date);
    }

    @Override
    public void setFixedCHAR(int i, String s) throws SQLException {
        cs.setFixedCHAR(i, s);
    }

    @Override
    public void setNUMBER(int i, NUMBER number) throws SQLException {
        cs.setNUMBER(i, number);
    }

    @Override
    public void setBinaryFloat(int i, float v) throws SQLException {
        cs.setBinaryFloat(i, v);
    }

    @Override
    public void setBinaryFloat(int i, BINARY_FLOAT binaryFloat) throws SQLException {
        cs.setBinaryFloat(i, binaryFloat);
    }

    @Override
    public void setBinaryDouble(int i, double v) throws SQLException {
        cs.setBinaryDouble(i, v);
    }

    @Override
    public void setBinaryDouble(int i, BINARY_DOUBLE binaryDouble) throws SQLException {
        cs.setBinaryDouble(i, binaryDouble);
    }

    @Override
    public void setOPAQUE(int i, OPAQUE opaque) throws SQLException {
        cs.setOPAQUE(i, opaque);
    }

    @Override
    public void setOracleObject(int i, Datum datum) throws SQLException {
        cs.setOracleObject(i, datum);
    }

    @Override
    public void setStructDescriptor(int i, StructDescriptor structDescriptor) throws SQLException {
        cs.setStructDescriptor(i, structDescriptor);
    }

    @Override
    public void setRAW(int i, RAW raw) throws SQLException {
        cs.setRAW(i, raw);
    }

    @Override
    public void setREF(int i, REF ref) throws SQLException {
        cs.setREF(i, ref);
    }

    @Override
    public void setRefType(int i, REF ref) throws SQLException {
        cs.setRefType(i, ref);
    }

    @Override
    public void setROWID(int i, ROWID rowid) throws SQLException {
        cs.setROWID(i, rowid);
    }

    @Override
    public void setSTRUCT(int i, STRUCT struct) throws SQLException {
        cs.setSTRUCT(i, struct);
    }

    @Override
    public void setTIMESTAMP(int i, TIMESTAMP timestamp) throws SQLException {
        cs.setTIMESTAMP(i, timestamp);
    }

    @Override
    public void setTIMESTAMPTZ(int i, TIMESTAMPTZ timestamptz) throws SQLException {
        cs.setTIMESTAMPTZ(i, timestamptz);
    }

    @Override
    public void setTIMESTAMPLTZ(int i, TIMESTAMPLTZ timestampltz) throws SQLException {
        cs.setTIMESTAMPLTZ(i, timestampltz);
    }

    @Override
    public void setINTERVALYM(int i, INTERVALYM intervalym) throws SQLException {
        cs.setINTERVALYM(i, intervalym);
    }

    @Override
    public void setINTERVALDS(int i, INTERVALDS intervalds) throws SQLException {
        cs.setINTERVALDS(i, intervalds);
    }

    @Override
    public void setNullAtName(String s, int i, String s1) throws SQLException {
        cs.setNullAtName(s, i, s1);
    }

    @Override
    public void setNullAtName(String s, int i) throws SQLException {
        cs.setNullAtName(s, i);
    }

    @Override
    public void setBooleanAtName(String s, boolean b) throws SQLException {
        cs.setBooleanAtName(s, b);
    }

    @Override
    public void setByteAtName(String s, byte b) throws SQLException {
        cs.setByteAtName(s, b);
    }

    @Override
    public void setShortAtName(String s, short i) throws SQLException {
        cs.setShortAtName(s, i);
    }

    @Override
    public void setIntAtName(String s, int i) throws SQLException {
        cs.setIntAtName(s, i);
    }

    @Override
    public void setLongAtName(String s, long l) throws SQLException {
        cs.setLongAtName(s, l);
    }

    @Override
    public void setFloatAtName(String s, float v) throws SQLException {
        cs.setFloatAtName(s, v);
    }

    @Override
    public void setDoubleAtName(String s, double v) throws SQLException {
        cs.setDoubleAtName(s, v);
    }

    @Override
    public void setBinaryFloatAtName(String s, float v) throws SQLException {
        cs.setBinaryFloatAtName(s, v);
    }

    @Override
    public void setBinaryFloatAtName(String s, BINARY_FLOAT binaryFloat) throws SQLException {
        cs.setBinaryFloatAtName(s, binaryFloat);
    }

    @Override
    public void setBinaryDoubleAtName(String s, double v) throws SQLException {
        cs.setBinaryDoubleAtName(s, v);
    }

    @Override
    public void setBinaryDoubleAtName(String s, BINARY_DOUBLE binaryDouble) throws SQLException {
        cs.setBinaryDoubleAtName(s, binaryDouble);
    }

    @Override
    public void setBigDecimalAtName(String s, BigDecimal bigDecimal) throws SQLException {
        cs.setBigDecimalAtName(s, bigDecimal);
    }

    @Override
    public void setStringAtName(String s, String s1) throws SQLException {
        cs.setStringAtName(s, s1);
    }

    @Override
    public void setStringForClob(int i, String s) throws SQLException {
        cs.setStringForClob(i, s);
    }

    @Override
    public void setStringForClobAtName(String s, String s1) throws SQLException {
        cs.setStringForClobAtName(s, s1);
    }

    @Override
    public void setFixedCHARAtName(String s, String s1) throws SQLException {
        cs.setFixedCHARAtName(s, s1);
    }

    @Override
    public void setCursorAtName(String s, ResultSet resultSet) throws SQLException {
        cs.setCursorAtName(s, resultSet);
    }

    @Override
    public void setROWIDAtName(String s, ROWID rowid) throws SQLException {
        cs.setROWIDAtName(s, rowid);
    }

    @Override
    public void setArrayAtName(String s, Array array) throws SQLException {
        cs.setArrayAtName(s, array);
    }

    @Override
    public void setARRAYAtName(String s, ARRAY array) throws SQLException {
        cs.setARRAYAtName(s, array);
    }

    @Override
    public void setOPAQUEAtName(String s, OPAQUE opaque) throws SQLException {
        cs.setOPAQUEAtName(s, opaque);
    }

    @Override
    public void setStructDescriptorAtName(String s, StructDescriptor structDescriptor) throws SQLException {
        cs.setStructDescriptorAtName(s, structDescriptor);
    }

    @Override
    public void setSTRUCTAtName(String s, STRUCT struct) throws SQLException {
        cs.setSTRUCTAtName(s, struct);
    }

    @Override
    public void setRAWAtName(String s, RAW raw) throws SQLException {
        cs.setRAWAtName(s, raw);
    }

    @Override
    public void setCHARAtName(String s, CHAR aChar) throws SQLException {
        cs.setCHARAtName(s, aChar);
    }

    @Override
    public void setDATEAtName(String s, DATE date) throws SQLException {
        cs.setDATEAtName(s, date);
    }

    @Override
    public void setNUMBERAtName(String s, NUMBER number) throws SQLException {
        cs.setNUMBERAtName(s, number);
    }

    @Override
    public void setBLOBAtName(String s, BLOB blob) throws SQLException {
        cs.setBLOBAtName(s, blob);
    }

    @Override
    public void setBlobAtName(String s, Blob blob) throws SQLException {
        cs.setBlobAtName(s, blob);
    }

    @Override
    public void setBlobAtName(String s, InputStream inputStream, long l) throws SQLException {
        cs.setBlobAtName(s, inputStream, l);
    }

    @Override
    public void setBlobAtName(String s, InputStream inputStream) throws SQLException {
        cs.setBlobAtName(s, inputStream);
    }

    @Override
    public void setCLOBAtName(String s, CLOB clob) throws SQLException {
        cs.setCLOBAtName(s, clob);
    }

    @Override
    public void setClobAtName(String s, Clob clob) throws SQLException {
        cs.setClobAtName(s, clob);
    }

    @Override
    public void setClobAtName(String s, Reader reader, long l) throws SQLException {
        cs.setClobAtName(s, reader, l);
    }

    @Override
    public void setClobAtName(String s, Reader reader) throws SQLException {
        cs.setClobAtName(s, reader);
    }

    @Override
    public void setBFILEAtName(String s, BFILE bfile) throws SQLException {
        cs.setBFILEAtName(s, bfile);
    }

    @Override
    public void setBfileAtName(String s, BFILE bfile) throws SQLException {
        cs.setBfileAtName(s, bfile);
    }

    @Override
    public void setBytesAtName(String s, byte[] bytes) throws SQLException {
        cs.setBytesAtName(s, bytes);
    }

    @Override
    public void setBytesForBlob(int i, byte[] bytes) throws SQLException {
        cs.setBytesForBlob(i, bytes);
    }

    @Override
    public void setBytesForBlobAtName(String s, byte[] bytes) throws SQLException {
        cs.setBytesForBlobAtName(s, bytes);
    }

    @Override
    public void setDateAtName(String s, Date date) throws SQLException {
        cs.setDateAtName(s, date);
    }

    @Override
    public void setDateAtName(String s, Date date, Calendar calendar) throws SQLException {
        cs.setDateAtName(s, date, calendar);
    }

    @Override
    public void setTimeAtName(String s, Time time) throws SQLException {
        cs.setTimeAtName(s, time);
    }

    @Override
    public void setTimeAtName(String s, Time time, Calendar calendar) throws SQLException {
        cs.setTimeAtName(s, time, calendar);
    }

    @Override
    public void setTimestampAtName(String s, Timestamp timestamp) throws SQLException {
        cs.setTimestampAtName(s, timestamp);
    }

    @Override
    public void setTimestampAtName(String s, Timestamp timestamp, Calendar calendar) throws SQLException {
        cs.setTimestampAtName(s, timestamp, calendar);
    }

    @Override
    public void setINTERVALYMAtName(String s, INTERVALYM intervalym) throws SQLException {
        cs.setINTERVALYMAtName(s, intervalym);
    }

    @Override
    public void setINTERVALDSAtName(String s, INTERVALDS intervalds) throws SQLException {
        cs.setINTERVALDSAtName(s, intervalds);
    }

    @Override
    public void setTIMESTAMPAtName(String s, TIMESTAMP timestamp) throws SQLException {
        cs.setTIMESTAMPAtName(s, timestamp);
    }

    @Override
    public void setTIMESTAMPTZAtName(String s, TIMESTAMPTZ timestamptz) throws SQLException {
        cs.setTIMESTAMPTZAtName(s, timestamptz);
    }

    @Override
    public void setTIMESTAMPLTZAtName(String s, TIMESTAMPLTZ timestampltz) throws SQLException {
        cs.setTIMESTAMPLTZAtName(s, timestampltz);
    }

    @Override
    public void setAsciiStreamAtName(String s, InputStream inputStream, int i) throws SQLException {
        cs.setAsciiStreamAtName(s, inputStream, i);
    }

    @Override
    public void setAsciiStreamAtName(String s, InputStream inputStream, long l) throws SQLException {
        cs.setAsciiStreamAtName(s, inputStream, l);
    }

    @Override
    public void setAsciiStreamAtName(String s, InputStream inputStream) throws SQLException {
        cs.setAsciiStreamAtName(s, inputStream);
    }

    @Override
    public void setBinaryStreamAtName(String s, InputStream inputStream, int i) throws SQLException {
        cs.setBinaryStreamAtName(s, inputStream, i);
    }

    @Override
    public void setBinaryStreamAtName(String s, InputStream inputStream, long l) throws SQLException {
        cs.setBinaryStreamAtName(s, inputStream, l);
    }

    @Override
    public void setBinaryStreamAtName(String s, InputStream inputStream) throws SQLException {
        cs.setBinaryStreamAtName(s, inputStream);
    }

    @Override
    public void setCharacterStreamAtName(String s, Reader reader, long l) throws SQLException {
        cs.setCharacterStreamAtName(s, reader, l);
    }

    @Override
    public void setCharacterStreamAtName(String s, Reader reader) throws SQLException {
        cs.setCharacterStreamAtName(s, reader);
    }

    @Override
    public void setUnicodeStreamAtName(String s, InputStream inputStream, int i) throws SQLException {
        cs.setUnicodeStreamAtName(s, inputStream, i);
    }

    @Override
    public void setCustomDatumAtName(String s, CustomDatum customDatum) throws SQLException {
        cs.setCustomDatumAtName(s, customDatum);
    }

    @Override
    public void setORADataAtName(String s, ORAData oraData) throws SQLException {
        cs.setORADataAtName(s, oraData);
    }

    @Override
    public void setObjectAtName(String s, Object o, int i, int i1) throws SQLException {
        cs.setObjectAtName(s, o, i, i1);
    }

    @Override
    public void setObjectAtName(String s, Object o, int i) throws SQLException {
        cs.setObjectAtName(s, o, i);
    }

    @Override
    public void setRefTypeAtName(String s, REF ref) throws SQLException {
        cs.setRefTypeAtName(s, ref);
    }

    @Override
    public void setRefAtName(String s, Ref ref) throws SQLException {
        cs.setRefAtName(s, ref);
    }

    @Override
    public void setREFAtName(String s, REF ref) throws SQLException {
        cs.setREFAtName(s, ref);
    }

    @Override
    public void setObjectAtName(String s, Object o) throws SQLException {
        cs.setObjectAtName(s, o);
    }

    @Override
    public void setOracleObjectAtName(String s, Datum datum) throws SQLException {
        cs.setOracleObjectAtName(s, datum);
    }

    @Override
    public void setURLAtName(String s, URL url) throws SQLException {
        cs.setURLAtName(s, url);
    }

    @Override
    public void setCheckBindTypes(boolean b) {
        cs.setCheckBindTypes(b);
    }

    @Override
    @Deprecated
    public void setPlsqlIndexTable(int i, Object o, int i1, int i2, int i3, int i4) throws SQLException {
        cs.setPlsqlIndexTable(i, o, i1, i2, i3, i4);
    }

    @Override
    public void setFormOfUse(int i, short i1) {
        cs.setFormOfUse(i, i1);
    }

    @Override
    public void setDisableStmtCaching(boolean b) {
        cs.setDisableStmtCaching(b);
    }

    @Override
    public OracleParameterMetaData OracleGetParameterMetaData() throws SQLException {
        return cs.OracleGetParameterMetaData();
    }

    @Override
    public void registerReturnParameter(int i, int i1) throws SQLException {
        cs.registerReturnParameter(i, i1);
    }

    @Override
    public void registerReturnParameter(int i, int i1, int i2) throws SQLException {
        cs.registerReturnParameter(i, i1, i2);
    }

    @Override
    public void registerReturnParameter(int i, int i1, String s) throws SQLException {
        cs.registerReturnParameter(i, i1, s);
    }

    @Override
    public ResultSet getReturnResultSet() throws SQLException {
        return cs.getReturnResultSet();
    }

    @Override
    public void setNCharacterStreamAtName(String s, Reader reader, long l) throws SQLException {
        cs.setNCharacterStreamAtName(s, reader, l);
    }

    @Override
    public void setNCharacterStreamAtName(String s, Reader reader) throws SQLException {
        cs.setNCharacterStreamAtName(s, reader);
    }

    @Override
    public void setNClobAtName(String s, NClob nClob) throws SQLException {
        cs.setNClobAtName(s, nClob);
    }

    @Override
    public void setNClobAtName(String s, Reader reader, long l) throws SQLException {
        cs.setNClobAtName(s, reader, l);
    }

    @Override
    public void setNClobAtName(String s, Reader reader) throws SQLException {
        cs.setNClobAtName(s, reader);
    }

    @Override
    public void setNStringAtName(String s, String s1) throws SQLException {
        cs.setNStringAtName(s, s1);
    }

    @Override
    public void setRowIdAtName(String s, RowId rowId) throws SQLException {
        cs.setRowIdAtName(s, rowId);
    }

    @Override
    public void setSQLXMLAtName(String s, SQLXML sqlxml) throws SQLException {
        cs.setSQLXMLAtName(s, sqlxml);
    }

    @Override
    public void clearDefines() throws SQLException {
        cs.clearDefines();
    }

    @Override
    public void defineColumnType(int i, int i1) throws SQLException {
        cs.defineColumnType(i, i1);
    }

    @Override
    public void defineColumnType(int i, int i1, int i2) throws SQLException {
        cs.defineColumnType(i, i1, i2);
    }

    @Override
    public void defineColumnType(int i, int i1, int i2, short i3) throws SQLException {
        cs.defineColumnType(i, i1, i2, i3);
    }

    @Override
    public void defineColumnTypeBytes(int i, int i1, int i2) throws SQLException {
        cs.defineColumnTypeBytes(i, i1, i2);
    }

    @Override
    public void defineColumnTypeChars(int i, int i1, int i2) throws SQLException {
        cs.defineColumnTypeChars(i, i1, i2);
    }

    @Override
    public void defineColumnType(int i, int i1, String s) throws SQLException {
        cs.defineColumnType(i, i1, s);
    }

    @Override
    public int getRowPrefetch() {
        return cs.getRowPrefetch();
    }

    @Override
    public void setRowPrefetch(int i) throws SQLException {
        cs.setRowPrefetch(i);
    }

    @Override
    public int getLobPrefetchSize() throws SQLException {
        return cs.getLobPrefetchSize();
    }

    @Override
    public void setLobPrefetchSize(int i) throws SQLException {
        cs.setLobPrefetchSize(i);
    }

    @Override
    public void closeWithKey(String s) throws SQLException {
        cs.closeWithKey(s);
    }

    @Override
    public int creationState() {
        return cs.creationState();
    }

    @Override
    public boolean isNCHAR(int i) throws SQLException {
        return cs.isNCHAR(i);
    }

    @Override
    public void setDatabaseChangeRegistration(DatabaseChangeRegistration databaseChangeRegistration) throws SQLException {
        cs.setDatabaseChangeRegistration(databaseChangeRegistration);
    }

    @Override
    public String[] getRegisteredTableNames() throws SQLException {
        return cs.getRegisteredTableNames();
    }

    @Override
    public long getRegisteredQueryId() throws SQLException {
        return cs.getRegisteredQueryId();
    }
}
