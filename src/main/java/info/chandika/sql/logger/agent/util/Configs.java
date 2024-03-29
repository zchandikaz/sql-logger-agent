package info.chandika.sql.logger.agent.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author : chandika
 * @since : 2022-09-24(Sat) 21:00
 **/
public class Configs {
    private static final Logger LOGGER = Logger.getLogger(Configs.class.getName());

    public static boolean CALLER_ENABLED = System.getProperty("connection.wrapper.caller.enabled", "false").equalsIgnoreCase("true");
    public static boolean SOUT_ENABLED = System.getProperty("connection.wrapper.sout.enabled", "false").equalsIgnoreCase("true");
    public static boolean WRAP_DRIVER_ENABLED = System.getProperty("connection.wrapper.enabled", "false").equalsIgnoreCase("true");
    public static boolean IS_DATA_STORED_STATEMENT_ENABLED = System.getProperty("connection.wrapper.richsql.enabled", "false").equalsIgnoreCase("true");
    public static boolean TEST_CALL_ENABLED = System.getProperty("connection.wrapper.testcall.enabled", "false").equalsIgnoreCase("true");
    public static boolean RESULT_CACHING_AVAILABLE = System.getProperty("connection.wrapper.caching.resultset.available", "false").equalsIgnoreCase("true");
    public static String REDIS_CONN_URL = System.getProperty("connection.wrapper.redis.url");
    public static Long RESULT_CACHING_TTL = Long.parseLong(System.getProperty("connection.wrapper.caching.resultset.ttl.sec", "86400"));
    public static String APP_NAME = System.getProperty("connection.wrapper.caching.app.name", "app");
    public static String[] CODEBASE_PACKAGES = System.getProperty("connection.wrapper.codebase.packages", "").trim().isEmpty() ? new String[]{} : System.getProperty("connection.wrapper.codebase.packages", "").split(",");
    public static Set<String> RESULT_CACHING_IGNORE_SQL = new HashSet<>();

    static {
        try {
            var ignoreSqlPath = System.getProperty("connection.wrapper.caching.ignore.sql.path");
            if (ignoreSqlPath != null) {
                var isql = Files.readString(Path.of(ignoreSqlPath));
                RESULT_CACHING_IGNORE_SQL = new HashSet<>(List.of(isql.trim().split("\n")));
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "", e);
        }
    }
}
