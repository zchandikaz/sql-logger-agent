package info.chandika.sql.logger.agent.loggable.oracle;

import info.chandika.sql.logger.agent.cache.ExecutionWrapper;
import info.chandika.sql.logger.agent.loggable.common.AbstractStatement;
import info.chandika.sql.logger.agent.util.StatementData;
import oracle.jdbc.OracleParameterMetaData;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.dcn.DatabaseChangeRegistration;
import oracle.sql.ARRAY;
import oracle.sql.BFILE;
import oracle.sql.BINARY_DOUBLE;
import oracle.sql.BINARY_FLOAT;
import oracle.sql.BLOB;
import oracle.sql.CHAR;
import oracle.sql.CLOB;
import oracle.sql.CustomDatum;
import oracle.sql.DATE;
import oracle.sql.Datum;
import oracle.sql.INTERVALDS;
import oracle.sql.INTERVALYM;
import oracle.sql.NUMBER;
import oracle.sql.OPAQUE;
import oracle.sql.ORAData;
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
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author : chandika
 * @since : 2022-07-06(Wed) 11:19
 **/


public class LoggableOraclePreparedStatement extends AbstractStatement implements OraclePreparedStatement {
    private final OraclePreparedStatement ps;
    List<StatementData> dataList = new ArrayList<>();

    private final String sql;

    public LoggableOraclePreparedStatement(OraclePreparedStatement ps) {
        this(ps, "N/A");
    }

    public LoggableOraclePreparedStatement(OraclePreparedStatement ps, String sql) {
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

    public void defineParameterTypeBytes(int i, int i1, int i2) throws SQLException {
        ps.defineParameterTypeBytes(i, i1, i2);
    }

    public void defineParameterTypeChars(int i, int i1, int i2) throws SQLException {
        ps.defineParameterTypeChars(i, i1, i2);
    }

    public void defineParameterType(int i, int i1, int i2) throws SQLException {
        ps.defineParameterType(i, i1, i2);
    }

    public int getExecuteBatch() {
        return ps.getExecuteBatch();
    }

    public int sendBatch() throws SQLException {
        return ps.sendBatch();
    }

    public void setARRAY(int i, ARRAY array) throws SQLException {
        ps.setARRAY(i, array);
    }

    public void setBfile(int i, BFILE bfile) throws SQLException {
        ps.setBfile(i, bfile);
    }

    public void setBFILE(int i, BFILE bfile) throws SQLException {
        ps.setBFILE(i, bfile);
    }

    public void setBLOB(int i, BLOB blob) throws SQLException {
        ps.setBLOB(i, blob);
    }

    public void setCHAR(int i, CHAR aChar) throws SQLException {
        ps.setCHAR(i, aChar);
    }

    public void setCLOB(int i, CLOB clob) throws SQLException {
        ps.setCLOB(i, clob);
    }

    public void setCursor(int i, ResultSet resultSet) throws SQLException {
        ps.setCursor(i, resultSet);
    }

    public void setCustomDatum(int i, CustomDatum customDatum) throws SQLException {
        ps.setCustomDatum(i, customDatum);
    }

    public void setORAData(int i, ORAData oraData) throws SQLException {
        ps.setORAData(i, oraData);
    }

    public void setDATE(int i, DATE date) throws SQLException {
        ps.setDATE(i, date);
    }

    public void setExecuteBatch(int i) throws SQLException {
        ps.setExecuteBatch(i);
    }

    public void setFixedCHAR(int i, String s) throws SQLException {
        ps.setFixedCHAR(i, s);
    }

    public void setNUMBER(int i, NUMBER number) throws SQLException {
        ps.setNUMBER(i, number);
    }

    public void setBinaryFloat(int i, float v) throws SQLException {
        ps.setBinaryFloat(i, v);
    }

    public void setBinaryFloat(int i, BINARY_FLOAT binaryFloat) throws SQLException {
        ps.setBinaryFloat(i, binaryFloat);
    }

    public void setBinaryDouble(int i, double v) throws SQLException {
        ps.setBinaryDouble(i, v);
    }

    public void setBinaryDouble(int i, BINARY_DOUBLE binaryDouble) throws SQLException {
        ps.setBinaryDouble(i, binaryDouble);
    }

    public void setOPAQUE(int i, OPAQUE opaque) throws SQLException {
        ps.setOPAQUE(i, opaque);
    }

    public void setOracleObject(int i, Datum datum) throws SQLException {
        ps.setOracleObject(i, datum);
    }

    public void setStructDescriptor(int i, StructDescriptor structDescriptor) throws SQLException {
        ps.setStructDescriptor(i, structDescriptor);
    }

    public void setRAW(int i, RAW raw) throws SQLException {
        ps.setRAW(i, raw);
    }

    public void setREF(int i, REF ref) throws SQLException {
        ps.setREF(i, ref);
    }

    public void setRefType(int i, REF ref) throws SQLException {
        ps.setRefType(i, ref);
    }

    public void setROWID(int i, ROWID rowid) throws SQLException {
        ps.setROWID(i, rowid);
    }

    public void setSTRUCT(int i, STRUCT struct) throws SQLException {
        ps.setSTRUCT(i, struct);
    }

    public void setTIMESTAMP(int i, TIMESTAMP timestamp) throws SQLException {
        ps.setTIMESTAMP(i, timestamp);
    }

    public void setTIMESTAMPTZ(int i, TIMESTAMPTZ timestamptz) throws SQLException {
        ps.setTIMESTAMPTZ(i, timestamptz);
    }

    public void setTIMESTAMPLTZ(int i, TIMESTAMPLTZ timestampltz) throws SQLException {
        ps.setTIMESTAMPLTZ(i, timestampltz);
    }

    public void setINTERVALYM(int i, INTERVALYM intervalym) throws SQLException {
        ps.setINTERVALYM(i, intervalym);
    }

    public void setINTERVALDS(int i, INTERVALDS intervalds) throws SQLException {
        ps.setINTERVALDS(i, intervalds);
    }

    public void setNullAtName(String s, int i, String s1) throws SQLException {
        ps.setNullAtName(s, i, s1);
    }

    public void setNullAtName(String s, int i) throws SQLException {
        ps.setNullAtName(s, i);
    }

    public void setBooleanAtName(String s, boolean b) throws SQLException {
        ps.setBooleanAtName(s, b);
    }

    public void setByteAtName(String s, byte b) throws SQLException {
        ps.setByteAtName(s, b);
    }

    public void setShortAtName(String s, short i) throws SQLException {
        ps.setShortAtName(s, i);
    }

    public void setIntAtName(String s, int i) throws SQLException {
        ps.setIntAtName(s, i);
    }

    public void setLongAtName(String s, long l) throws SQLException {
        ps.setLongAtName(s, l);
    }

    public void setFloatAtName(String s, float v) throws SQLException {
        ps.setFloatAtName(s, v);
    }

    public void setDoubleAtName(String s, double v) throws SQLException {
        ps.setDoubleAtName(s, v);
    }

    public void setBinaryFloatAtName(String s, float v) throws SQLException {
        ps.setBinaryFloatAtName(s, v);
    }

    public void setBinaryFloatAtName(String s, BINARY_FLOAT binaryFloat) throws SQLException {
        ps.setBinaryFloatAtName(s, binaryFloat);
    }

    public void setBinaryDoubleAtName(String s, double v) throws SQLException {
        ps.setBinaryDoubleAtName(s, v);
    }

    public void setBinaryDoubleAtName(String s, BINARY_DOUBLE binaryDouble) throws SQLException {
        ps.setBinaryDoubleAtName(s, binaryDouble);
    }

    public void setBigDecimalAtName(String s, BigDecimal bigDecimal) throws SQLException {
        ps.setBigDecimalAtName(s, bigDecimal);
    }

    public void setStringAtName(String s, String s1) throws SQLException {
        ps.setStringAtName(s, s1);
    }

    public void setStringForClob(int i, String s) throws SQLException {
        ps.setStringForClob(i, s);
    }

    public void setStringForClobAtName(String s, String s1) throws SQLException {
        ps.setStringForClobAtName(s, s1);
    }

    public void setFixedCHARAtName(String s, String s1) throws SQLException {
        ps.setFixedCHARAtName(s, s1);
    }

    public void setCursorAtName(String s, ResultSet resultSet) throws SQLException {
        ps.setCursorAtName(s, resultSet);
    }

    public void setROWIDAtName(String s, ROWID rowid) throws SQLException {
        ps.setROWIDAtName(s, rowid);
    }

    public void setArrayAtName(String s, Array array) throws SQLException {
        ps.setArrayAtName(s, array);
    }

    public void setARRAYAtName(String s, ARRAY array) throws SQLException {
        ps.setARRAYAtName(s, array);
    }

    public void setOPAQUEAtName(String s, OPAQUE opaque) throws SQLException {
        ps.setOPAQUEAtName(s, opaque);
    }

    public void setStructDescriptorAtName(String s, StructDescriptor structDescriptor) throws SQLException {
        ps.setStructDescriptorAtName(s, structDescriptor);
    }

    public void setSTRUCTAtName(String s, STRUCT struct) throws SQLException {
        ps.setSTRUCTAtName(s, struct);
    }

    public void setRAWAtName(String s, RAW raw) throws SQLException {
        ps.setRAWAtName(s, raw);
    }

    public void setCHARAtName(String s, CHAR aChar) throws SQLException {
        ps.setCHARAtName(s, aChar);
    }

    public void setDATEAtName(String s, DATE date) throws SQLException {
        ps.setDATEAtName(s, date);
    }

    public void setNUMBERAtName(String s, NUMBER number) throws SQLException {
        ps.setNUMBERAtName(s, number);
    }

    public void setBLOBAtName(String s, BLOB blob) throws SQLException {
        ps.setBLOBAtName(s, blob);
    }

    public void setBlobAtName(String s, Blob blob) throws SQLException {
        ps.setBlobAtName(s, blob);
    }

    public void setBlobAtName(String s, InputStream inputStream, long l) throws SQLException {
        ps.setBlobAtName(s, inputStream, l);
    }

    public void setBlobAtName(String s, InputStream inputStream) throws SQLException {
        ps.setBlobAtName(s, inputStream);
    }

    public void setCLOBAtName(String s, CLOB clob) throws SQLException {
        ps.setCLOBAtName(s, clob);
    }

    public void setClobAtName(String s, Clob clob) throws SQLException {
        ps.setClobAtName(s, clob);
    }

    public void setClobAtName(String s, Reader reader, long l) throws SQLException {
        ps.setClobAtName(s, reader, l);
    }

    public void setClobAtName(String s, Reader reader) throws SQLException {
        ps.setClobAtName(s, reader);
    }

    public void setBFILEAtName(String s, BFILE bfile) throws SQLException {
        ps.setBFILEAtName(s, bfile);
    }

    public void setBfileAtName(String s, BFILE bfile) throws SQLException {
        ps.setBfileAtName(s, bfile);
    }

    public void setBytesAtName(String s, byte[] bytes) throws SQLException {
        ps.setBytesAtName(s, bytes);
    }

    public void setBytesForBlob(int i, byte[] bytes) throws SQLException {
        ps.setBytesForBlob(i, bytes);
    }

    public void setBytesForBlobAtName(String s, byte[] bytes) throws SQLException {
        ps.setBytesForBlobAtName(s, bytes);
    }

    public void setDateAtName(String s, Date date) throws SQLException {
        ps.setDateAtName(s, date);
    }

    public void setDateAtName(String s, Date date, Calendar calendar) throws SQLException {
        ps.setDateAtName(s, date, calendar);
    }

    public void setTimeAtName(String s, Time time) throws SQLException {
        ps.setTimeAtName(s, time);
    }

    public void setTimeAtName(String s, Time time, Calendar calendar) throws SQLException {
        ps.setTimeAtName(s, time, calendar);
    }

    public void setTimestampAtName(String s, Timestamp timestamp) throws SQLException {
        ps.setTimestampAtName(s, timestamp);
    }

    public void setTimestampAtName(String s, Timestamp timestamp, Calendar calendar) throws SQLException {
        ps.setTimestampAtName(s, timestamp, calendar);
    }

    public void setINTERVALYMAtName(String s, INTERVALYM intervalym) throws SQLException {
        ps.setINTERVALYMAtName(s, intervalym);
    }

    public void setINTERVALDSAtName(String s, INTERVALDS intervalds) throws SQLException {
        ps.setINTERVALDSAtName(s, intervalds);
    }

    public void setTIMESTAMPAtName(String s, TIMESTAMP timestamp) throws SQLException {
        ps.setTIMESTAMPAtName(s, timestamp);
    }

    public void setTIMESTAMPTZAtName(String s, TIMESTAMPTZ timestamptz) throws SQLException {
        ps.setTIMESTAMPTZAtName(s, timestamptz);
    }

    public void setTIMESTAMPLTZAtName(String s, TIMESTAMPLTZ timestampltz) throws SQLException {
        ps.setTIMESTAMPLTZAtName(s, timestampltz);
    }

    public void setAsciiStreamAtName(String s, InputStream inputStream, int i) throws SQLException {
        ps.setAsciiStreamAtName(s, inputStream, i);
    }

    public void setAsciiStreamAtName(String s, InputStream inputStream, long l) throws SQLException {
        ps.setAsciiStreamAtName(s, inputStream, l);
    }

    public void setAsciiStreamAtName(String s, InputStream inputStream) throws SQLException {
        ps.setAsciiStreamAtName(s, inputStream);
    }

    public void setBinaryStreamAtName(String s, InputStream inputStream, int i) throws SQLException {
        ps.setBinaryStreamAtName(s, inputStream, i);
    }

    public void setBinaryStreamAtName(String s, InputStream inputStream, long l) throws SQLException {
        ps.setBinaryStreamAtName(s, inputStream, l);
    }

    public void setBinaryStreamAtName(String s, InputStream inputStream) throws SQLException {
        ps.setBinaryStreamAtName(s, inputStream);
    }

    public void setCharacterStreamAtName(String s, Reader reader, long l) throws SQLException {
        ps.setCharacterStreamAtName(s, reader, l);
    }

    public void setCharacterStreamAtName(String s, Reader reader) throws SQLException {
        ps.setCharacterStreamAtName(s, reader);
    }

    public void setUnicodeStreamAtName(String s, InputStream inputStream, int i) throws SQLException {
        ps.setUnicodeStreamAtName(s, inputStream, i);
    }

    public void setCustomDatumAtName(String s, CustomDatum customDatum) throws SQLException {
        ps.setCustomDatumAtName(s, customDatum);
    }

    public void setORADataAtName(String s, ORAData oraData) throws SQLException {
        ps.setORADataAtName(s, oraData);
    }

    public void setObjectAtName(String s, Object o, int i, int i1) throws SQLException {
        ps.setObjectAtName(s, o, i, i1);
    }

    public void setObjectAtName(String s, Object o, int i) throws SQLException {
        ps.setObjectAtName(s, o, i);
    }

    public void setRefTypeAtName(String s, REF ref) throws SQLException {
        ps.setRefTypeAtName(s, ref);
    }

    public void setRefAtName(String s, Ref ref) throws SQLException {
        ps.setRefAtName(s, ref);
    }

    public void setREFAtName(String s, REF ref) throws SQLException {
        ps.setREFAtName(s, ref);
    }

    public void setObjectAtName(String s, Object o) throws SQLException {
        ps.setObjectAtName(s, o);
    }

    public void setOracleObjectAtName(String s, Datum datum) throws SQLException {
        ps.setOracleObjectAtName(s, datum);
    }

    public void setURLAtName(String s, URL url) throws SQLException {
        ps.setURLAtName(s, url);
    }

    public void setCheckBindTypes(boolean b) {
        ps.setCheckBindTypes(b);
    }

    @Deprecated
    public void setPlsqlIndexTable(int i, Object o, int i1, int i2, int i3, int i4) throws SQLException {
        ps.setPlsqlIndexTable(i, o, i1, i2, i3, i4);
    }

    public void setFormOfUse(int i, short i1) {
        ps.setFormOfUse(i, i1);
    }

    public void setDisableStmtCaching(boolean b) {
        ps.setDisableStmtCaching(b);
    }

    public OracleParameterMetaData OracleGetParameterMetaData() throws SQLException {
        return ps.OracleGetParameterMetaData();
    }

    public void registerReturnParameter(int i, int i1) throws SQLException {
        ps.registerReturnParameter(i, i1);
    }

    public void registerReturnParameter(int i, int i1, int i2) throws SQLException {
        ps.registerReturnParameter(i, i1, i2);
    }

    public void registerReturnParameter(int i, int i1, String s) throws SQLException {
        ps.registerReturnParameter(i, i1, s);
    }

    public ResultSet getReturnResultSet() throws SQLException {
        return ps.getReturnResultSet();
    }

    public void setNCharacterStreamAtName(String s, Reader reader, long l) throws SQLException {
        ps.setNCharacterStreamAtName(s, reader, l);
    }

    public void setNCharacterStreamAtName(String s, Reader reader) throws SQLException {
        ps.setNCharacterStreamAtName(s, reader);
    }

    public void setNClobAtName(String s, NClob nClob) throws SQLException {
        ps.setNClobAtName(s, nClob);
    }

    public void setNClobAtName(String s, Reader reader, long l) throws SQLException {
        ps.setNClobAtName(s, reader, l);
    }

    public void setNClobAtName(String s, Reader reader) throws SQLException {
        ps.setNClobAtName(s, reader);
    }

    public void setNStringAtName(String s, String s1) throws SQLException {
        ps.setNStringAtName(s, s1);
    }

    public void setRowIdAtName(String s, RowId rowId) throws SQLException {
        ps.setRowIdAtName(s, rowId);
    }

    public void setSQLXMLAtName(String s, SQLXML sqlxml) throws SQLException {
        ps.setSQLXMLAtName(s, sqlxml);
    }

    public void clearDefines() throws SQLException {
        ps.clearDefines();
    }

    public void defineColumnType(int i, int i1) throws SQLException {
        ps.defineColumnType(i, i1);
    }

    public void defineColumnType(int i, int i1, int i2) throws SQLException {
        ps.defineColumnType(i, i1, i2);
    }

    public void defineColumnType(int i, int i1, int i2, short i3) throws SQLException {
        ps.defineColumnType(i, i1, i2, i3);
    }

    public void defineColumnTypeBytes(int i, int i1, int i2) throws SQLException {
        ps.defineColumnTypeBytes(i, i1, i2);
    }

    public void defineColumnTypeChars(int i, int i1, int i2) throws SQLException {
        ps.defineColumnTypeChars(i, i1, i2);
    }

    public void defineColumnType(int i, int i1, String s) throws SQLException {
        ps.defineColumnType(i, i1, s);
    }

    public int getRowPrefetch() {
        return ps.getRowPrefetch();
    }

    public void setRowPrefetch(int i) throws SQLException {
        ps.setRowPrefetch(i);
    }

    public int getLobPrefetchSize() throws SQLException {
        return ps.getLobPrefetchSize();
    }

    public void setLobPrefetchSize(int i) throws SQLException {
        ps.setLobPrefetchSize(i);
    }

    public void closeWithKey(String s) throws SQLException {
        ps.closeWithKey(s);
    }

    public int creationState() {
        return ps.creationState();
    }

    public boolean isNCHAR(int i) throws SQLException {
        return ps.isNCHAR(i);
    }

    public void setDatabaseChangeRegistration(DatabaseChangeRegistration databaseChangeRegistration) throws SQLException {
        ps.setDatabaseChangeRegistration(databaseChangeRegistration);
    }

    public String[] getRegisteredTableNames() throws SQLException {
        return ps.getRegisteredTableNames();
    }

    public long getRegisteredQueryId() throws SQLException {
        return ps.getRegisteredQueryId();
    }
}
