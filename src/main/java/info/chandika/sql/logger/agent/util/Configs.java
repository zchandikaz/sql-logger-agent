package info.chandika.sql.logger.agent.util;

/**
 * @author : chandika
 * @since : 2022-09-24(Sat) 21:00
 **/
public class Configs {

    public static boolean SOUT_ENABLED = System.getProperty("loggable.connection.sout.enabled", "false").equalsIgnoreCase("true");
    public static boolean TEST_CALL_ENABLED = System.getProperty("loggable.connection.testcall.enabled", "false").equalsIgnoreCase("true");

    public static boolean IS_DATA_STORED_STATEMENT_ENABLED = System.getProperty("loggable.connection.richsql.enabled", "true").equalsIgnoreCase("true");

    /**
     * Need to setup {@link Configs#CODEBASE_PACKAGES}
     */
    public static boolean CALLER_ENABLED = System.getProperty("loggable.connection.caller.enabled", "false").equalsIgnoreCase("true");
    public static String[] CODEBASE_PACKAGES = System.getProperty("loggable.connection.codebase.packages", "").trim().isEmpty() ? new String[]{} : System.getProperty("loggable.connection.codebase.packages", "").split(",");

    private Configs(){}


}
