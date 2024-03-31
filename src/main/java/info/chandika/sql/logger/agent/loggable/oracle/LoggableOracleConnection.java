package info.chandika.sql.logger.agent.loggable.oracle;

import info.chandika.sql.logger.agent.logger.ConnectionLogger;
import oracle.jdbc.LogicalTransactionId;
import oracle.jdbc.LogicalTransactionIdEventListener;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleOCIFailover;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleSavepoint;
import oracle.jdbc.OracleShardingKey;
import oracle.jdbc.aq.AQDequeueOptions;
import oracle.jdbc.aq.AQEnqueueOptions;
import oracle.jdbc.aq.AQMessage;
import oracle.jdbc.aq.AQMessageProperties;
import oracle.jdbc.aq.AQNotificationRegistration;
import oracle.jdbc.dcn.DatabaseChangeRegistration;
import oracle.jdbc.driver.HAManager;
import oracle.jdbc.internal.DatabaseSessionState;
import oracle.jdbc.internal.JMSDequeueOptions;
import oracle.jdbc.internal.JMSEnqueueOptions;
import oracle.jdbc.internal.JMSMessage;
import oracle.jdbc.internal.JMSNotificationRegistration;
import oracle.jdbc.internal.KeywordValueLong;
import oracle.jdbc.internal.NetStat;
import oracle.jdbc.internal.OracleArray;
import oracle.jdbc.internal.OracleBfile;
import oracle.jdbc.internal.OracleConnection;
import oracle.jdbc.internal.OracleLargeObject;
import oracle.jdbc.internal.OracleStatement;
import oracle.jdbc.internal.PDBChangeEventListener;
import oracle.jdbc.internal.ReplayContext;
import oracle.jdbc.internal.ResultSetCache;
import oracle.jdbc.internal.XSEventListener;
import oracle.jdbc.internal.XSKeyval;
import oracle.jdbc.internal.XSNamespace;
import oracle.jdbc.internal.XSPrincipal;
import oracle.jdbc.internal.XSSecureId;
import oracle.jdbc.internal.XSSessionParameters;
import oracle.jdbc.oracore.OracleTypeADT;
import oracle.jdbc.oracore.OracleTypeCLOB;
import oracle.jdbc.pool.OracleConnectionCacheCallback;
import oracle.jdbc.pool.OraclePooledConnection;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.BFILE;
import oracle.sql.BINARY_DOUBLE;
import oracle.sql.BINARY_FLOAT;
import oracle.sql.BLOB;
import oracle.sql.BfileDBAccess;
import oracle.sql.BlobDBAccess;
import oracle.sql.CLOB;
import oracle.sql.ClobDBAccess;
import oracle.sql.CustomDatum;
import oracle.sql.DATE;
import oracle.sql.Datum;
import oracle.sql.INTERVALDS;
import oracle.sql.INTERVALYM;
import oracle.sql.NUMBER;
import oracle.sql.StructDescriptor;
import oracle.sql.TIMESTAMP;
import oracle.sql.TIMESTAMPLTZ;
import oracle.sql.TIMESTAMPTZ;
import oracle.sql.TIMEZONETAB;
import oracle.sql.TypeDescriptor;

import javax.transaction.xa.XAResource;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.SocketException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.ShardingKey;
import java.sql.Statement;
import java.sql.Struct;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;
import java.util.concurrent.Executor;

/**
 * @author : chandika
 * @since : 2022-07-06(Wed) 10:46
 **/


public class LoggableOracleConnection implements OracleConnection {
    private final OracleConnection con;

    public LoggableOracleConnection(OracleConnection con) {
        this.con = con;
    }

    private Statement wrap(Statement st) {
        return new LoggableOracleStatement((OracleStatement)st);
    }

    private PreparedStatement wrap(PreparedStatement ps, String sql) {
        return new LoggableOraclePreparedStatement((OraclePreparedStatement)ps, sql);
    }

    private CallableStatement wrap(CallableStatement cs, String sql) {
        return new LoggableOracleCallableStatement((OracleCallableStatement)cs, sql);
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


    @Override
    public int getVarTypeMaxLenCompat() throws SQLException {
        return con.getVarTypeMaxLenCompat();
    }

    @Override
    public short getStructAttrNCsId() throws SQLException {
        return con.getStructAttrNCsId();
    }

    @Override
    public Properties getDBAccessProperties() throws SQLException {
        return con.getDBAccessProperties();
    }

    @Override
    public Properties getOCIHandles() throws SQLException {
        return con.getOCIHandles();
    }

    @Override
    public String getDatabaseProductVersion() throws SQLException {
        return con.getDatabaseProductVersion();
    }

    @Override
    public String getURL() throws SQLException {
        return con.getURL();
    }

    @Override
    public short getVersionNumber() throws SQLException {
        return con.getVersionNumber();
    }

    @Override
    public Map getJavaObjectTypeMap() {
        return con.getJavaObjectTypeMap();
    }

    @Override
    public void setJavaObjectTypeMap(Map map) {
        con.setJavaObjectTypeMap(map);
    }

    @Override
    public byte getInstanceProperty(InstanceProperty instanceProperty) throws SQLException {
        return con.getInstanceProperty(instanceProperty);
    }

    @Override
    public BfileDBAccess createBfileDBAccess() throws SQLException {
        return con.createBfileDBAccess();
    }

    @Override
    public BlobDBAccess createBlobDBAccess() throws SQLException {
        return con.createBlobDBAccess();
    }

    @Override
    public ClobDBAccess createClobDBAccess() throws SQLException {
        return con.createClobDBAccess();
    }

    @Override
    public void setDefaultFixedString(boolean b) {
        con.setDefaultFixedString(b);
    }

    @Override
    public boolean getDefaultFixedString() {
        return con.getDefaultFixedString();
    }

    @Override
    public oracle.jdbc.OracleConnection getWrapper() {
        return con.getWrapper();
    }

    @Override
    public Class classForNameAndSchema(String s, String s1) throws ClassNotFoundException {
        return con.classForNameAndSchema(s, s1);
    }

    @Override
    public void setFDO(byte[] bytes) throws SQLException {
        con.setFDO(bytes);
    }

    @Override
    public byte[] getFDO(boolean b) throws SQLException {
        return con.getFDO(b);
    }

    @Override
    public boolean getBigEndian() throws SQLException {
        return con.getBigEndian();
    }

    @Override
    public Object getDescriptor(byte[] bytes) {
        return con.getDescriptor(bytes);
    }

    @Override
    public void putDescriptor(byte[] bytes, Object o) throws SQLException {
        con.putDescriptor(bytes, o);
    }

    @Override
    public OracleConnection getPhysicalConnection() {
        return con.getPhysicalConnection();
    }

    @Override
    public void removeDescriptor(String s) {
        con.removeDescriptor(s);
    }

    @Override
    public void removeAllDescriptor() {
        con.removeAllDescriptor();
    }

    @Override
    public int numberOfDescriptorCacheEntries() {
        return con.numberOfDescriptorCacheEntries();
    }

    @Override
    public Enumeration descriptorCacheKeys() {
        return con.descriptorCacheKeys();
    }

    @Override
    public long getTdoCState(String s, String s1) throws SQLException {
        return con.getTdoCState(s, s1);
    }

    @Override
    public long getTdoCState(String s) throws SQLException {
        return con.getTdoCState(s);
    }

    @Override
    public BufferCacheStatistics getByteBufferCacheStatistics() {
        return con.getByteBufferCacheStatistics();
    }

    @Override
    public BufferCacheStatistics getCharBufferCacheStatistics() {
        return con.getCharBufferCacheStatistics();
    }

    @Override
    public Datum toDatum(CustomDatum customDatum) throws SQLException {
        return con.toDatum(customDatum);
    }

    @Override
    public short getDbCsId() throws SQLException {
        return con.getDbCsId();
    }

    @Override
    public short getJdbcCsId() throws SQLException {
        return con.getJdbcCsId();
    }

    @Override
    public short getNCharSet() {
        return con.getNCharSet();
    }

    @Override
    public ResultSet newArrayDataResultSet(Datum[] data, long l, int i, Map map) throws SQLException {
        return con.newArrayDataResultSet(data, l, i, map);
    }

    @Override
    public ResultSet newArrayDataResultSet(OracleArray oracleArray, long l, int i, Map map) throws SQLException {
        return con.newArrayDataResultSet(oracleArray, l, i, map);
    }

    @Override
    public ResultSet newArrayLocatorResultSet(ArrayDescriptor arrayDescriptor, byte[] bytes, long l, int i, Map map) throws SQLException {
        return con.newArrayLocatorResultSet(arrayDescriptor, bytes, l, i, map);
    }

    @Override
    public ResultSetMetaData newStructMetaData(StructDescriptor structDescriptor) throws SQLException {
        return con.newStructMetaData(structDescriptor);
    }

    @Override
    public void getForm(OracleTypeADT oracleTypeADT, OracleTypeCLOB oracleTypeCLOB, int i) throws SQLException {
        con.getForm(oracleTypeADT, oracleTypeCLOB, i);
    }

    @Override
    public int CHARBytesToJavaChars(byte[] bytes, int i, char[] chars) throws SQLException {
        return con.CHARBytesToJavaChars(bytes, i, chars);
    }

    @Override
    public int NCHARBytesToJavaChars(byte[] bytes, int i, char[] chars) throws SQLException {
        return con.NCHARBytesToJavaChars(bytes, i, chars);
    }

    @Override
    public boolean IsNCharFixedWith() {
        return con.IsNCharFixedWith();
    }

    @Override
    public short getDriverCharSet() {
        return con.getDriverCharSet();
    }

    @Override
    public int getC2SNlsRatio() {
        return con.getC2SNlsRatio();
    }

    @Override
    public int getMaxCharSize() throws SQLException {
        return con.getMaxCharSize();
    }

    @Override
    public int getMaxCharbyteSize() {
        return con.getMaxCharbyteSize();
    }

    @Override
    public int getMaxNCharbyteSize() {
        return con.getMaxNCharbyteSize();
    }

    @Override
    public boolean isCharSetMultibyte(short i) {
        return con.isCharSetMultibyte(i);
    }

    @Override
    public int javaCharsToCHARBytes(char[] chars, int i, byte[] bytes) throws SQLException {
        return con.javaCharsToCHARBytes(chars, i, bytes);
    }

    @Override
    public int javaCharsToNCHARBytes(char[] chars, int i, byte[] bytes) throws SQLException {
        return con.javaCharsToNCHARBytes(chars, i, bytes);
    }

    @Override
    public void setStartTime(long l) throws SQLException {
        con.setStartTime(l);
    }

    @Override
    public long getStartTime() throws SQLException {
        return con.getStartTime();
    }

    @Override
    public boolean isStatementCacheInitialized() {
        return con.isStatementCacheInitialized();
    }

    @Override
    public void getPropertyForPooledConnection(OraclePooledConnection oraclePooledConnection) throws SQLException {
        con.getPropertyForPooledConnection(oraclePooledConnection);
    }

    @Override
    public void setTypeMap(Map map) throws SQLException {
        con.setTypeMap(map);
    }

    @Override
    public String getProtocolType() {
        return con.getProtocolType();
    }

    @Override
    public Connection getLogicalConnection(OraclePooledConnection oraclePooledConnection, boolean b) throws SQLException {
        return con.getLogicalConnection(oraclePooledConnection, b);
    }

    @Override
    public void setTxnMode(int i) {
        con.setTxnMode(i);
    }

    @Override
    public int getTxnMode() {
        return con.getTxnMode();
    }

    @Override
    public int getHeapAllocSize() throws SQLException {
        return con.getHeapAllocSize();
    }

    @Override
    public int getOCIEnvHeapAllocSize() throws SQLException {
        return con.getOCIEnvHeapAllocSize();
    }

    @Override
    public void setAbandonedTimeoutEnabled(boolean b) throws SQLException {
        con.setAbandonedTimeoutEnabled(b);
    }

    @Override
    public int getHeartbeatNoChangeCount() throws SQLException {
        return con.getHeartbeatNoChangeCount();
    }

    @Override
    public void closeInternal(boolean b) throws SQLException {
        con.closeInternal(b);
    }

    @Override
    public void cleanupAndClose(boolean b) throws SQLException {
        con.cleanupAndClose(b);
    }

    @Override
    public OracleConnectionCacheCallback getConnectionCacheCallbackObj() throws SQLException {
        return con.getConnectionCacheCallbackObj();
    }

    @Override
    public Object getConnectionCacheCallbackPrivObj() throws SQLException {
        return con.getConnectionCacheCallbackPrivObj();
    }

    @Override
    public int getConnectionCacheCallbackFlag() throws SQLException {
        return con.getConnectionCacheCallbackFlag();
    }

    @Override
    public Properties getServerSessionInfo() throws SQLException {
        return con.getServerSessionInfo();
    }

    @Override
    public CLOB createClob(byte[] bytes) throws SQLException {
        return con.createClob(bytes);
    }

    @Override
    public CLOB createClobWithUnpickledBytes(byte[] bytes) throws SQLException {
        return con.createClobWithUnpickledBytes(bytes);
    }

    @Override
    public CLOB createClob(byte[] bytes, short i) throws SQLException {
        return con.createClob(bytes, i);
    }

    @Override
    public BLOB createBlob(byte[] bytes) throws SQLException {
        return con.createBlob(bytes);
    }

    @Override
    public BLOB createBlobWithUnpickledBytes(byte[] bytes) throws SQLException {
        return con.createBlobWithUnpickledBytes(bytes);
    }

    @Override
    public BFILE createBfile(byte[] bytes) throws SQLException {
        return con.createBfile(bytes);
    }

    @Override
    public boolean isDescriptorSharable(OracleConnection oracleConnection) throws SQLException {
        return con.isDescriptorSharable(oracleConnection);
    }

    @Override
    public OracleStatement refCursorCursorToStatement(int i) throws SQLException {
        return con.refCursorCursorToStatement(i);
    }

    @Override
    public XAResource getXAResource() throws SQLException {
        return con.getXAResource();
    }

    @Override
    public boolean isV8Compatible() throws SQLException {
        return con.isV8Compatible();
    }

    @Override
    public boolean getMapDateToTimestamp() {
        return con.getMapDateToTimestamp();
    }

    @Override
    public boolean getJDBCStandardBehavior() {
        return con.getJDBCStandardBehavior();
    }

    @Override
    public byte[] createLightweightSession(String s, KeywordValueLong[] keywordValueLongs, int i, KeywordValueLong[][] keywordValueLongs1, int[] ints) throws SQLException {
        return con.createLightweightSession(s, keywordValueLongs, i, keywordValueLongs1, ints);
    }

    @Override
    public void executeLightweightSessionPiggyback(int i, byte[] bytes, KeywordValueLong[] keywordValueLongs, int i1) throws SQLException {
        con.executeLightweightSessionPiggyback(i, bytes, keywordValueLongs, i1);
    }

    @Override
    public void doXSNamespaceOp(XSOperationCode xsOperationCode, byte[] bytes, XSNamespace[] xsNamespaces, XSNamespace[][] xsNamespaces1, XSSecureId xsSecureId) throws SQLException {
        con.doXSNamespaceOp(xsOperationCode, bytes, xsNamespaces, xsNamespaces1, xsSecureId);
    }

    @Override
    public void doXSNamespaceOp(XSOperationCode xsOperationCode, byte[] bytes, XSNamespace[] xsNamespaces, XSSecureId xsSecureId) throws SQLException {
        con.doXSNamespaceOp(xsOperationCode, bytes, xsNamespaces, xsSecureId);
    }

    @Override
    public byte[] doXSSessionCreateOp(XSSessionOperationCode xsSessionOperationCode, XSSecureId xsSecureId, byte[] bytes, XSPrincipal xsPrincipal, String s, XSNamespace[] xsNamespaces, XSSessionModeFlag xsSessionModeFlag, XSKeyval xsKeyval) throws SQLException {
        return con.doXSSessionCreateOp(xsSessionOperationCode, xsSecureId, bytes, xsPrincipal, s, xsNamespaces, xsSessionModeFlag, xsKeyval);
    }

    @Override
    public void doXSSessionDestroyOp(byte[] bytes, XSSecureId xsSecureId, byte[] bytes1) throws SQLException {
        con.doXSSessionDestroyOp(bytes, xsSecureId, bytes1);
    }

    @Override
    public void doXSSessionAttachOp(int i, byte[] bytes, XSSecureId xsSecureId, byte[] bytes1, XSPrincipal xsPrincipal, String[] strings, String[] strings1, String[] strings2, XSNamespace[] xsNamespaces, XSNamespace[] xsNamespaces1, XSNamespace[] xsNamespaces2, TIMESTAMPTZ timestamptz, TIMESTAMPTZ timestamptz1, int i1, long l, XSKeyval xsKeyval, int[] ints) throws SQLException {
        con.doXSSessionAttachOp(i, bytes, xsSecureId, bytes1, xsPrincipal, strings, strings1, strings2, xsNamespaces, xsNamespaces1, xsNamespaces2, timestamptz, timestamptz1, i1, l, xsKeyval, ints);
    }

    @Override
    public void doXSSessionDetachOp(int i, byte[] bytes, XSSecureId xsSecureId, boolean b) throws SQLException {
        con.doXSSessionDetachOp(i, bytes, xsSecureId, b);
    }

    @Override
    public void doXSSessionChangeOp(XSSessionSetOperationCode xsSessionSetOperationCode, byte[] bytes, XSSecureId xsSecureId, XSSessionParameters xsSessionParameters) throws SQLException {
        con.doXSSessionChangeOp(xsSessionSetOperationCode, bytes, xsSecureId, xsSessionParameters);
    }

    @Override
    public String getDefaultSchemaNameForNamedTypes() throws SQLException {
        return con.getDefaultSchemaNameForNamedTypes();
    }

    @Override
    public void setUsable(boolean b) {
        con.setUsable(b);
    }

    @Override
    public Class getClassForType(String s, Map<String, Class> map) {
        return con.getClassForType(s, map);
    }

    @Override
    public void addXSEventListener(XSEventListener xsEventListener) throws SQLException {
        con.addXSEventListener(xsEventListener);
    }

    @Override
    public void addXSEventListener(XSEventListener xsEventListener, Executor executor) throws SQLException {
        con.addXSEventListener(xsEventListener, executor);
    }

    @Override
    public void removeXSEventListener(XSEventListener xsEventListener) throws SQLException {
        con.removeXSEventListener(xsEventListener);
    }

    @Override
    public int getTimezoneVersionNumber() throws SQLException {
        return con.getTimezoneVersionNumber();
    }

    @Override
    public void removeAllXSEventListener() throws SQLException {
        con.removeAllXSEventListener();
    }

    @Override
    public TIMEZONETAB getTIMEZONETAB() throws SQLException {
        return con.getTIMEZONETAB();
    }

    @Override
    public String getDatabaseTimeZone() throws SQLException {
        return con.getDatabaseTimeZone();
    }

    @Override
    public boolean getTimestamptzInGmt() {
        return con.getTimestamptzInGmt();
    }

    @Override
    public boolean getUse1900AsYearForTime() {
        return con.getUse1900AsYearForTime();
    }

    @Override
    public boolean isDataInLocatorEnabled() throws SQLException {
        return con.isDataInLocatorEnabled();
    }

    @Override
    public boolean isLobStreamPosStandardCompliant() throws SQLException {
        return con.isLobStreamPosStandardCompliant();
    }

    @Override
    public long getCurrentSCN() throws SQLException {
        return con.getCurrentSCN();
    }

    @Override
    public EnumSet<TransactionState> getTransactionState() throws SQLException {
        return con.getTransactionState();
    }

    @Override
    public boolean isConnectionSocketKeepAlive() throws SocketException, SQLException {
        return con.isConnectionSocketKeepAlive();
    }

    @Override
    public void setReplayOperations(EnumSet<ReplayOperation> enumSet) throws SQLException {
        con.setReplayOperations(enumSet);
    }

    @Override
    public void setReplayingMode(boolean b) throws SQLException {
        con.setReplayingMode(b);
    }

    @Override
    public void beginNonRequestCalls() throws SQLException {
        con.beginNonRequestCalls();
    }

    @Override
    public void endNonRequestCalls() throws SQLException {
        con.endNonRequestCalls();
    }

    @Override
    public void setReplayContext(ReplayContext[] replayContexts) throws SQLException {
        con.setReplayContext(replayContexts);
    }

    @Override
    public ReplayContext[] getReplayContext() throws SQLException {
        return con.getReplayContext();
    }

    @Override
    public ReplayContext getLastReplayContext() throws SQLException {
        return con.getLastReplayContext();
    }

    @Override
    public void setLastReplayContext(ReplayContext replayContext) throws SQLException {
        con.setLastReplayContext(replayContext);
    }

    @Override
    public void registerEndReplayCallback(EndReplayCallback endReplayCallback) throws SQLException {
        con.registerEndReplayCallback(endReplayCallback);
    }

    @Override
    public int getEOC() throws SQLException {
        return con.getEOC();
    }

    @Override
    public byte[] getDerivedKeyInternal(byte[] bytes, int i) throws NoSuchAlgorithmException, InvalidKeySpecException, SQLException {
        return con.getDerivedKeyInternal(bytes, i);
    }

    @Override
    public short getExecutingRPCFunctionCode() {
        return con.getExecutingRPCFunctionCode();
    }

    @Override
    public String getExecutingRPCSQL() {
        return con.getExecutingRPCSQL();
    }

    @Override
    public void jmsEnqueue(String s, JMSEnqueueOptions jmsEnqueueOptions, JMSMessage jmsMessage, AQMessageProperties aqMessageProperties) throws SQLException {
        con.jmsEnqueue(s, jmsEnqueueOptions, jmsMessage, aqMessageProperties);
    }

    @Override
    public JMSMessage jmsDequeue(String s, JMSDequeueOptions jmsDequeueOptions) throws SQLException {
        return con.jmsDequeue(s, jmsDequeueOptions);
    }

    @Override
    public JMSMessage jmsDequeue(String s, JMSDequeueOptions jmsDequeueOptions, OutputStream outputStream) throws SQLException {
        return con.jmsDequeue(s, jmsDequeueOptions, outputStream);
    }

    @Override
    public JMSMessage jmsDequeue(String s, JMSDequeueOptions jmsDequeueOptions, String s1) throws SQLException {
        return con.jmsDequeue(s, jmsDequeueOptions, s1);
    }

    @Override
    public Map<String, JMSNotificationRegistration> registerJMSNotification(String[] strings, Map<String, Properties> map) throws SQLException {
        return con.registerJMSNotification(strings, map);
    }

    @Override
    public Map<String, JMSNotificationRegistration> registerJMSNotification(String[] strings, Map<String, Properties> map, String s) throws SQLException {
        return con.registerJMSNotification(strings, map, s);
    }

    @Override
    public void unregisterJMSNotification(JMSNotificationRegistration jmsNotificationRegistration) throws SQLException {
        con.unregisterJMSNotification(jmsNotificationRegistration);
    }

    @Override
    public void startJMSNotification(JMSNotificationRegistration jmsNotificationRegistration) throws SQLException {
        con.startJMSNotification(jmsNotificationRegistration);
    }

    @Override
    public void stopJMSNotification(JMSNotificationRegistration jmsNotificationRegistration) throws SQLException {
        con.stopJMSNotification(jmsNotificationRegistration);
    }

    @Override
    public void ackJMSNotification(JMSNotificationRegistration jmsNotificationRegistration, byte[] bytes, JMSNotificationRegistration.Directive directive) throws SQLException {
        con.ackJMSNotification(jmsNotificationRegistration, bytes, directive);
    }

    @Override
    public void ackJMSNotification(ArrayList<JMSNotificationRegistration> arrayList, byte[][] bytes, JMSNotificationRegistration.Directive directive) throws SQLException {
        con.ackJMSNotification(arrayList, bytes, directive);
    }

    @Override
    public int getNegotiatedSDU() throws SQLException {
        return con.getNegotiatedSDU();
    }

    @Override
    public byte getNegotiatedTTCVersion() throws SQLException {
        return con.getNegotiatedTTCVersion();
    }

    @Override
    public void setPDBChangeEventListener(PDBChangeEventListener pdbChangeEventListener) throws SQLException {
        con.setPDBChangeEventListener(pdbChangeEventListener);
    }

    @Override
    public void setPDBChangeEventListener(PDBChangeEventListener pdbChangeEventListener, Executor executor) throws SQLException {
        con.setPDBChangeEventListener(pdbChangeEventListener, executor);
    }

    @Override
    public void setChecksumMode(ChecksumMode checksumMode) throws SQLException {
        con.setChecksumMode(checksumMode);
    }

    @Override
    public ResultSetCache getResultSetCache() throws SQLException {
        return con.getResultSetCache();
    }

    @Override
    public void closeLogicalConnection() throws SQLException {
        con.closeLogicalConnection();
    }

    @Override
    public void cleanupAndClose() throws SQLException {
        con.cleanupAndClose();
    }

    @Override
    public boolean isLifecycleOpen() throws SQLException {
        return con.isLifecycleOpen();
    }

    @Override
    public void clearDrcpTagName() throws SQLException {
        con.clearDrcpTagName();
    }

    @Override
    public void setClientIdentifier(String s) throws SQLException {
        con.setClientIdentifier(s);
    }

    @Override
    public void clearClientIdentifier(String s) throws SQLException {
        con.clearClientIdentifier(s);
    }

    @Override
    public int freeTemporaryBlobsAndClobs() throws SQLException {
        return con.freeTemporaryBlobsAndClobs();
    }

    @Override
    public void setChunkInfo(OracleShardingKey oracleShardingKey, OracleShardingKey oracleShardingKey1, String s) throws SQLException {
        con.setChunkInfo(oracleShardingKey, oracleShardingKey1, s);
    }

    @Override
    public HAManager getHAManager() {
        return con.getHAManager();
    }

    @Override
    public void setHAManager(HAManager haManager) throws SQLException {
        con.setHAManager(haManager);
    }

    @Override
    public boolean isUsable(boolean b) {
        return con.isUsable(b);
    }

    @Override
    public boolean isNetworkCompressionEnabled() {
        return con.isNetworkCompressionEnabled();
    }

    @Override
    public NetStat getNetworkStat() {
        return con.getNetworkStat();
    }

    @Override
    public void sendRequestFlags() throws SQLException {
        con.sendRequestFlags();
    }

    @Override
    public boolean hasNoOpenHandles() throws SQLException {
        return con.hasNoOpenHandles();
    }

    @Override
    public void endRequest(boolean b) throws SQLException {
        con.endRequest(b);
    }

    @Override
    public DatabaseSessionState getDatabaseSessionState() throws SQLException {
        return con.getDatabaseSessionState();
    }

    @Override
    public void setDatabaseSessionState(DatabaseSessionState databaseSessionState) throws SQLException {
        con.setDatabaseSessionState(databaseSessionState);
    }

    @Override
    public boolean isSafelyClosed() throws SQLException {
        return con.isSafelyClosed();
    }

    @Override
    public void setSafelyClosed(boolean b) throws SQLException {
        con.setSafelyClosed(b);
    }

    @Override
    public void addLargeObject(OracleLargeObject oracleLargeObject) throws SQLException {
        con.addLargeObject(oracleLargeObject);
    }

    @Override
    public void removeLargeObject(OracleLargeObject oracleLargeObject) throws SQLException {
        con.removeLargeObject(oracleLargeObject);
    }

    @Override
    public void addBfile(OracleBfile oracleBfile) throws SQLException {
        con.addBfile(oracleBfile);
    }

    @Override
    public void removeBfile(OracleBfile oracleBfile) throws SQLException {
        con.removeBfile(oracleBfile);
    }

    @Override
    public PreparedStatement prepareStatement(String s, Properties properties) throws SQLException {
        return con.prepareStatement(s, properties);
    }

    @Override
    public CallableStatement prepareCall(String s, Properties properties) throws SQLException {
        return con.prepareCall(s, properties);
    }

    @Override
    public Properties getClientInfoInternal() throws SQLException {
        return con.getClientInfoInternal();
    }

    @Override
    public boolean getAutoCommitInternal() throws SQLException {
        return con.getAutoCommitInternal();
    }

    @Override
    public PreparedStatement prepareDirectPath(String s, String s1, String[] strings) throws SQLException {
        return con.prepareDirectPath(s, s1, strings);
    }

    @Override
    public PreparedStatement prepareDirectPath(String s, String s1, String[] strings, Properties properties) throws SQLException {
        return con.prepareDirectPath(s, s1, strings, properties);
    }

    @Override
    public PreparedStatement prepareDirectPath(String s, String s1, String[] strings, String s2) throws SQLException {
        return con.prepareDirectPath(s, s1, strings, s2);
    }

    @Override
    public PreparedStatement prepareDirectPath(String s, String s1, String[] strings, String s2, Properties properties) throws SQLException {
        return con.prepareDirectPath(s, s1, strings, s2, properties);
    }

    @Override
    public void commit(EnumSet<CommitOption> enumSet) throws SQLException {
        con.commit(enumSet);
    }

    @Override
    public void archive(int i, int i1, String s) throws SQLException {
        con.archive(i, i1, s);
    }

    @Override
    public void openProxySession(int i, Properties properties) throws SQLException {
        con.openProxySession(i, properties);
    }

    @Override
    public boolean getAutoClose() throws SQLException {
        return con.getAutoClose();
    }

    @Override
    public int getDefaultExecuteBatch() {
        return con.getDefaultExecuteBatch();
    }

    @Override
    public int getDefaultRowPrefetch() {
        return con.getDefaultRowPrefetch();
    }

    @Override
    public Object getDescriptor(String s) {
        return con.getDescriptor(s);
    }

    @Override
    public String[] getEndToEndMetrics() throws SQLException {
        return con.getEndToEndMetrics();
    }

    @Override
    public short getEndToEndECIDSequenceNumber() throws SQLException {
        return con.getEndToEndECIDSequenceNumber();
    }

    @Override
    public boolean getIncludeSynonyms() {
        return con.getIncludeSynonyms();
    }

    @Override
    public boolean getRestrictGetTables() {
        return con.getRestrictGetTables();
    }

    @Override
    public Object getJavaObject(String s) throws SQLException {
        return con.getJavaObject(s);
    }

    @Override
    public boolean getRemarksReporting() {
        return con.getRemarksReporting();
    }

    @Override
    public String getSQLType(Object o) throws SQLException {
        return con.getSQLType(o);
    }

    @Override
    public int getStmtCacheSize() {
        return con.getStmtCacheSize();
    }

    @Override
    public short getStructAttrCsId() throws SQLException {
        return con.getStructAttrCsId();
    }

    @Override
    public String getUserName() throws SQLException {
        return con.getUserName();
    }

    @Override
    public String getCurrentSchema() throws SQLException {
        return con.getCurrentSchema();
    }

    @Override
    public boolean getUsingXAFlag() {
        return con.getUsingXAFlag();
    }

    @Override
    public boolean getXAErrorFlag() {
        return con.getXAErrorFlag();
    }

    @Override
    public int pingDatabase() throws SQLException {
        return con.pingDatabase();
    }

    @Override
    public int pingDatabase(int i) throws SQLException {
        return con.pingDatabase(i);
    }

    @Override
    public void putDescriptor(String s, Object o) throws SQLException {
        con.putDescriptor(s, o);
    }

    @Override
    public void registerSQLType(String s, Class aClass) throws SQLException {
        con.registerSQLType(s, aClass);
    }

    @Override
    public void registerSQLType(String s, String s1) throws SQLException {
        con.registerSQLType(s, s1);
    }

    @Override
    public void setAutoClose(boolean b) throws SQLException {
        con.setAutoClose(b);
    }

    @Override
    public void setDefaultExecuteBatch(int i) throws SQLException {
        con.setDefaultExecuteBatch(i);
    }

    @Override
    public void setDefaultRowPrefetch(int i) throws SQLException {
        con.setDefaultRowPrefetch(i);
    }

    @Override
    public void setEndToEndMetrics(String[] strings, short i) throws SQLException {
        con.setEndToEndMetrics(strings, i);
    }

    @Override
    public void setIncludeSynonyms(boolean b) {
        con.setIncludeSynonyms(b);
    }

    @Override
    public void setRemarksReporting(boolean b) {
        con.setRemarksReporting(b);
    }

    @Override
    public void setRestrictGetTables(boolean b) {
        con.setRestrictGetTables(b);
    }

    @Override
    public void setStmtCacheSize(int i) throws SQLException {
        con.setStmtCacheSize(i);
    }

    @Override
    public void setStmtCacheSize(int i, boolean b) throws SQLException {
        con.setStmtCacheSize(i, b);
    }

    @Override
    public void setStatementCacheSize(int i) throws SQLException {
        con.setStatementCacheSize(i);
    }

    @Override
    public int getStatementCacheSize() throws SQLException {
        return con.getStatementCacheSize();
    }

    @Override
    public void setImplicitCachingEnabled(boolean b) throws SQLException {
        con.setImplicitCachingEnabled(b);
    }

    @Override
    public boolean getImplicitCachingEnabled() throws SQLException {
        return con.getImplicitCachingEnabled();
    }

    @Override
    public void setExplicitCachingEnabled(boolean b) throws SQLException {
        con.setExplicitCachingEnabled(b);
    }

    @Override
    public boolean getExplicitCachingEnabled() throws SQLException {
        return con.getExplicitCachingEnabled();
    }

    @Override
    public void purgeImplicitCache() throws SQLException {
        con.purgeImplicitCache();
    }

    @Override
    public void purgeExplicitCache() throws SQLException {
        con.purgeExplicitCache();
    }

    @Override
    public PreparedStatement getStatementWithKey(String s) throws SQLException {
        return con.getStatementWithKey(s);
    }

    @Override
    public CallableStatement getCallWithKey(String s) throws SQLException {
        return con.getCallWithKey(s);
    }

    @Override
    public void setUsingXAFlag(boolean b) {
        con.setUsingXAFlag(b);
    }

    @Override
    public void setXAErrorFlag(boolean b) {
        con.setXAErrorFlag(b);
    }

    @Override
    public void shutdown(DatabaseShutdownMode databaseShutdownMode) throws SQLException {
        con.shutdown(databaseShutdownMode);
    }

    @Override
    public void startup(String s, int i) throws SQLException {
        con.startup(s, i);
    }

    @Override
    public void startup(DatabaseStartupMode databaseStartupMode) throws SQLException {
        con.startup(databaseStartupMode);
    }

    @Override
    public void startup(DatabaseStartupMode databaseStartupMode, String s) throws SQLException {
        con.startup(databaseStartupMode, s);
    }

    @Override
    public PreparedStatement prepareStatementWithKey(String s) throws SQLException {
        return con.prepareStatementWithKey(s);
    }

    @Override
    public CallableStatement prepareCallWithKey(String s) throws SQLException {
        return con.prepareCallWithKey(s);
    }

    @Override
    public void setCreateStatementAsRefCursor(boolean b) {
        con.setCreateStatementAsRefCursor(b);
    }

    @Override
    public boolean getCreateStatementAsRefCursor() {
        return con.getCreateStatementAsRefCursor();
    }

    @Override
    public void setSessionTimeZone(String s) throws SQLException {
        con.setSessionTimeZone(s);
    }

    @Override
    public String getSessionTimeZone() {
        return con.getSessionTimeZone();
    }

    @Override
    public String getSessionTimeZoneOffset() throws SQLException {
        return con.getSessionTimeZoneOffset();
    }

    @Override
    public Properties getProperties() {
        return con.getProperties();
    }

    @Override
    public Connection _getPC() {
        return con._getPC();
    }

    @Override
    public boolean isLogicalConnection() {
        return con.isLogicalConnection();
    }

    @Override
    public void registerTAFCallback(OracleOCIFailover oracleOCIFailover, Object o) throws SQLException {
        con.registerTAFCallback(oracleOCIFailover, o);
    }

    @Override
    public oracle.jdbc.OracleConnection unwrap() {
        return con.unwrap();
    }

    @Override
    public void setWrapper(oracle.jdbc.OracleConnection oracleConnection) {
        con.setWrapper(oracleConnection);
    }

    @Override
    public OracleConnection physicalConnectionWithin() {
        return con.physicalConnectionWithin();
    }

    @Override
    public OracleSavepoint oracleSetSavepoint() throws SQLException {
        return con.oracleSetSavepoint();
    }

    @Override
    public OracleSavepoint oracleSetSavepoint(String s) throws SQLException {
        return con.oracleSetSavepoint(s);
    }

    @Override
    public void oracleRollback(OracleSavepoint oracleSavepoint) throws SQLException {
        con.oracleRollback(oracleSavepoint);
    }

    @Override
    public void oracleReleaseSavepoint(OracleSavepoint oracleSavepoint) throws SQLException {
        con.oracleReleaseSavepoint(oracleSavepoint);
    }

    @Override
    public void close(Properties properties) throws SQLException {
        con.close(properties);
    }

    @Override
    public void close(int i) throws SQLException {
        con.close(i);
    }

    @Override
    public boolean isProxySession() {
        return con.isProxySession();
    }

    @Override
    public void applyConnectionAttributes(Properties properties) throws SQLException {
        con.applyConnectionAttributes(properties);
    }

    @Override
    public Properties getConnectionAttributes() throws SQLException {
        return con.getConnectionAttributes();
    }

    @Override
    public Properties getUnMatchedConnectionAttributes() throws SQLException {
        return con.getUnMatchedConnectionAttributes();
    }

    @Override
    public void registerConnectionCacheCallback(OracleConnectionCacheCallback oracleConnectionCacheCallback, Object o, int i) throws SQLException {
        con.registerConnectionCacheCallback(oracleConnectionCacheCallback, o, i);
    }

    @Override
    public void setConnectionReleasePriority(int i) throws SQLException {
        con.setConnectionReleasePriority(i);
    }

    @Override
    public int getConnectionReleasePriority() throws SQLException {
        return con.getConnectionReleasePriority();
    }

    @Override
    public void setPlsqlWarnings(String s) throws SQLException {
        con.setPlsqlWarnings(s);
    }

    @Override
    public AQNotificationRegistration[] registerAQNotification(String[] strings, Properties[] properties, Properties properties1) throws SQLException {
        return con.registerAQNotification(strings, properties, properties1);
    }

    @Override
    public void unregisterAQNotification(AQNotificationRegistration aqNotificationRegistration) throws SQLException {
        con.unregisterAQNotification(aqNotificationRegistration);
    }

    @Override
    public AQMessage dequeue(String s, AQDequeueOptions aqDequeueOptions, byte[] bytes) throws SQLException {
        return con.dequeue(s, aqDequeueOptions, bytes);
    }

    @Override
    public AQMessage dequeue(String s, AQDequeueOptions aqDequeueOptions, String s1) throws SQLException {
        return con.dequeue(s, aqDequeueOptions, s1);
    }

    @Override
    public void enqueue(String s, AQEnqueueOptions aqEnqueueOptions, AQMessage aqMessage) throws SQLException {
        con.enqueue(s, aqEnqueueOptions, aqMessage);
    }

    @Override
    public DatabaseChangeRegistration registerDatabaseChangeNotification(Properties properties) throws SQLException {
        return con.registerDatabaseChangeNotification(properties);
    }

    @Override
    public DatabaseChangeRegistration getDatabaseChangeRegistration(int i) throws SQLException {
        return con.getDatabaseChangeRegistration(i);
    }

    @Override
    public void unregisterDatabaseChangeNotification(DatabaseChangeRegistration databaseChangeRegistration) throws SQLException {
        con.unregisterDatabaseChangeNotification(databaseChangeRegistration);
    }

    @Override
    public void unregisterDatabaseChangeNotification(int i, String s, int i1) throws SQLException {
        con.unregisterDatabaseChangeNotification(i, s, i1);
    }

    @Override
    public void unregisterDatabaseChangeNotification(int i) throws SQLException {
        con.unregisterDatabaseChangeNotification(i);
    }

    @Override
    public void unregisterDatabaseChangeNotification(long l, String s) throws SQLException {
        con.unregisterDatabaseChangeNotification(l, s);
    }

    @Override
    public ARRAY createARRAY(String s, Object o) throws SQLException {
        return con.createARRAY(s, o);
    }

    @Override
    public Array createOracleArray(String s, Object o) throws SQLException {
        return con.createOracleArray(s, o);
    }

    @Override
    public BINARY_DOUBLE createBINARY_DOUBLE(double v) throws SQLException {
        return con.createBINARY_DOUBLE(v);
    }

    @Override
    public BINARY_FLOAT createBINARY_FLOAT(float v) throws SQLException {
        return con.createBINARY_FLOAT(v);
    }

    @Override
    public DATE createDATE(Date date) throws SQLException {
        return con.createDATE(date);
    }

    @Override
    public DATE createDATE(Time time) throws SQLException {
        return con.createDATE(time);
    }

    @Override
    public DATE createDATE(Timestamp timestamp) throws SQLException {
        return con.createDATE(timestamp);
    }

    @Override
    public DATE createDATE(Date date, Calendar calendar) throws SQLException {
        return con.createDATE(date, calendar);
    }

    @Override
    public DATE createDATE(Time time, Calendar calendar) throws SQLException {
        return con.createDATE(time, calendar);
    }

    @Override
    public DATE createDATE(Timestamp timestamp, Calendar calendar) throws SQLException {
        return con.createDATE(timestamp, calendar);
    }

    @Override
    public DATE createDATE(String s) throws SQLException {
        return con.createDATE(s);
    }

    @Override
    public INTERVALDS createINTERVALDS(String s) throws SQLException {
        return con.createINTERVALDS(s);
    }

    @Override
    public INTERVALYM createINTERVALYM(String s) throws SQLException {
        return con.createINTERVALYM(s);
    }

    @Override
    public NUMBER createNUMBER(boolean b) throws SQLException {
        return con.createNUMBER(b);
    }

    @Override
    public NUMBER createNUMBER(byte b) throws SQLException {
        return con.createNUMBER(b);
    }

    @Override
    public NUMBER createNUMBER(short i) throws SQLException {
        return con.createNUMBER(i);
    }

    @Override
    public NUMBER createNUMBER(int i) throws SQLException {
        return con.createNUMBER(i);
    }

    @Override
    public NUMBER createNUMBER(long l) throws SQLException {
        return con.createNUMBER(l);
    }

    @Override
    public NUMBER createNUMBER(float v) throws SQLException {
        return con.createNUMBER(v);
    }

    @Override
    public NUMBER createNUMBER(double v) throws SQLException {
        return con.createNUMBER(v);
    }

    @Override
    public NUMBER createNUMBER(BigDecimal bigDecimal) throws SQLException {
        return con.createNUMBER(bigDecimal);
    }

    @Override
    public NUMBER createNUMBER(BigInteger bigInteger) throws SQLException {
        return con.createNUMBER(bigInteger);
    }

    @Override
    public NUMBER createNUMBER(String s, int i) throws SQLException {
        return con.createNUMBER(s, i);
    }

    @Override
    public TIMESTAMP createTIMESTAMP(Date date) throws SQLException {
        return con.createTIMESTAMP(date);
    }

    @Override
    public TIMESTAMP createTIMESTAMP(DATE date) throws SQLException {
        return con.createTIMESTAMP(date);
    }

    @Override
    public TIMESTAMP createTIMESTAMP(Time time) throws SQLException {
        return con.createTIMESTAMP(time);
    }

    @Override
    public TIMESTAMP createTIMESTAMP(Timestamp timestamp) throws SQLException {
        return con.createTIMESTAMP(timestamp);
    }

    @Override
    public TIMESTAMP createTIMESTAMP(Timestamp timestamp, Calendar calendar) throws SQLException {
        return con.createTIMESTAMP(timestamp, calendar);
    }

    @Override
    public TIMESTAMP createTIMESTAMP(String s) throws SQLException {
        return con.createTIMESTAMP(s);
    }

    @Override
    public TIMESTAMPTZ createTIMESTAMPTZ(Date date) throws SQLException {
        return con.createTIMESTAMPTZ(date);
    }

    @Override
    public TIMESTAMPTZ createTIMESTAMPTZ(Date date, Calendar calendar) throws SQLException {
        return con.createTIMESTAMPTZ(date, calendar);
    }

    @Override
    public TIMESTAMPTZ createTIMESTAMPTZ(Time time) throws SQLException {
        return con.createTIMESTAMPTZ(time);
    }

    @Override
    public TIMESTAMPTZ createTIMESTAMPTZ(Time time, Calendar calendar) throws SQLException {
        return con.createTIMESTAMPTZ(time, calendar);
    }

    @Override
    public TIMESTAMPTZ createTIMESTAMPTZ(Timestamp timestamp) throws SQLException {
        return con.createTIMESTAMPTZ(timestamp);
    }

    @Override
    public TIMESTAMPTZ createTIMESTAMPTZ(Timestamp timestamp, Calendar calendar) throws SQLException {
        return con.createTIMESTAMPTZ(timestamp, calendar);
    }

    @Override
    public TIMESTAMPTZ createTIMESTAMPTZ(Timestamp timestamp, ZoneId zoneId) throws SQLException {
        return con.createTIMESTAMPTZ(timestamp, zoneId);
    }

    @Override
    public TIMESTAMPTZ createTIMESTAMPTZ(String s) throws SQLException {
        return con.createTIMESTAMPTZ(s);
    }

    @Override
    public TIMESTAMPTZ createTIMESTAMPTZ(String s, Calendar calendar) throws SQLException {
        return con.createTIMESTAMPTZ(s, calendar);
    }

    @Override
    public TIMESTAMPTZ createTIMESTAMPTZ(DATE date) throws SQLException {
        return con.createTIMESTAMPTZ(date);
    }

    @Override
    public TIMESTAMPLTZ createTIMESTAMPLTZ(Date date, Calendar calendar) throws SQLException {
        return con.createTIMESTAMPLTZ(date, calendar);
    }

    @Override
    public TIMESTAMPLTZ createTIMESTAMPLTZ(Time time, Calendar calendar) throws SQLException {
        return con.createTIMESTAMPLTZ(time, calendar);
    }

    @Override
    public TIMESTAMPLTZ createTIMESTAMPLTZ(Timestamp timestamp, Calendar calendar) throws SQLException {
        return con.createTIMESTAMPLTZ(timestamp, calendar);
    }

    @Override
    public TIMESTAMPLTZ createTIMESTAMPLTZ(String s, Calendar calendar) throws SQLException {
        return con.createTIMESTAMPLTZ(s, calendar);
    }

    @Override
    public TIMESTAMPLTZ createTIMESTAMPLTZ(DATE date, Calendar calendar) throws SQLException {
        return con.createTIMESTAMPLTZ(date, calendar);
    }

    @Override
    public void cancel() throws SQLException {
        con.cancel();
    }

    @Override
    public void abort() throws SQLException {
        con.abort();
    }

    @Override
    public TypeDescriptor[] getAllTypeDescriptorsInCurrentSchema() throws SQLException {
        return con.getAllTypeDescriptorsInCurrentSchema();
    }

    @Override
    public TypeDescriptor[] getTypeDescriptorsFromListInCurrentSchema(String[] strings) throws SQLException {
        return con.getTypeDescriptorsFromListInCurrentSchema(strings);
    }

    @Override
    public TypeDescriptor[] getTypeDescriptorsFromList(String[][] strings) throws SQLException {
        return con.getTypeDescriptorsFromList(strings);
    }

    @Override
    public String getDataIntegrityAlgorithmName() throws SQLException {
        return con.getDataIntegrityAlgorithmName();
    }

    @Override
    public String getEncryptionAlgorithmName() throws SQLException {
        return con.getEncryptionAlgorithmName();
    }

    @Override
    public String getAuthenticationAdaptorName() throws SQLException {
        return con.getAuthenticationAdaptorName();
    }

    @Override
    public boolean isUsable() {
        return con.isUsable();
    }

    @Override
    public void setDefaultTimeZone(TimeZone timeZone) throws SQLException {
        con.setDefaultTimeZone(timeZone);
    }

    @Override
    public TimeZone getDefaultTimeZone() throws SQLException {
        return con.getDefaultTimeZone();
    }

    @Override
    public void setApplicationContext(String s, String s1, String s2) throws SQLException {
        con.setApplicationContext(s, s1, s2);
    }

    @Override
    public void clearAllApplicationContext(String s) throws SQLException {
        con.clearAllApplicationContext(s);
    }

    @Override
    public void addLogicalTransactionIdEventListener(LogicalTransactionIdEventListener logicalTransactionIdEventListener) throws SQLException {
        con.addLogicalTransactionIdEventListener(logicalTransactionIdEventListener);
    }

    @Override
    public void addLogicalTransactionIdEventListener(LogicalTransactionIdEventListener logicalTransactionIdEventListener, Executor executor) throws SQLException {
        con.addLogicalTransactionIdEventListener(logicalTransactionIdEventListener, executor);
    }

    @Override
    public void removeLogicalTransactionIdEventListener(LogicalTransactionIdEventListener logicalTransactionIdEventListener) throws SQLException {
        con.removeLogicalTransactionIdEventListener(logicalTransactionIdEventListener);
    }

    @Override
    public LogicalTransactionId getLogicalTransactionId() throws SQLException {
        return con.getLogicalTransactionId();
    }

    @Override
    public boolean isDRCPEnabled() throws SQLException {
        return con.isDRCPEnabled();
    }

    @Override
    public boolean isDRCPMultitagEnabled() throws SQLException {
        return con.isDRCPMultitagEnabled();
    }

    @Override
    public String getDRCPReturnTag() throws SQLException {
        return con.getDRCPReturnTag();
    }

    @Override
    public String getDRCPPLSQLCallbackName() throws SQLException {
        return con.getDRCPPLSQLCallbackName();
    }

    @Override
    public boolean attachServerConnection() throws SQLException {
        return con.attachServerConnection();
    }

    @Override
    public void detachServerConnection(String s) throws SQLException {
        con.detachServerConnection(s);
    }

    @Override
    public boolean needToPurgeStatementCache() throws SQLException {
        return con.needToPurgeStatementCache();
    }

    @Override
    public DRCPState getDRCPState() throws SQLException {
        return con.getDRCPState();
    }

    @Override
    public boolean setShardingKeyIfValid(OracleShardingKey oracleShardingKey, OracleShardingKey oracleShardingKey1, int i) throws SQLException {
        return con.setShardingKeyIfValid(oracleShardingKey, oracleShardingKey1, i);
    }

    @Override
    public void setShardingKey(OracleShardingKey oracleShardingKey, OracleShardingKey oracleShardingKey1) throws SQLException {
        con.setShardingKey(oracleShardingKey, oracleShardingKey1);
    }

    @Override
    public boolean setShardingKeyIfValid(OracleShardingKey oracleShardingKey, int i) throws SQLException {
        return con.setShardingKeyIfValid(oracleShardingKey, i);
    }

    @Override
    public void setShardingKey(OracleShardingKey oracleShardingKey) throws SQLException {
        con.setShardingKey(oracleShardingKey);
    }

    @Override
    public boolean isValid(ConnectionValidation connectionValidation, int i) throws SQLException {
        return con.isValid(connectionValidation, i);
    }

    @Override
    public String getEncryptionProviderName() throws SQLException {
        return con.getEncryptionProviderName();
    }

    @Override
    public String getChecksumProviderName() throws SQLException {
        return con.getChecksumProviderName();
    }

    @Override
    public void setACProxy(Object o) {
        con.setACProxy(o);
    }

    @Override
    public Object getACProxy() {
        return con.getACProxy();
    }
}
