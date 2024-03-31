package info.chandika.sql.logger.agent.instrumentation.transformer;

import java.lang.instrument.Instrumentation;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author : chandika
 * @since : 2024-03-27(Wed) 20:30
 **/
public enum DriverType {
    H2("H2", "org.h2.Driver"),
    POSTGRES("POSTGRES", "org.postgresql.Driver"),
    ORACLE("ORACLE", "oracle.jdbc.driver.OracleDriver"),
    MYSQL("MYSQL", "com.mysql.jdbc.Driver");

    private static final Logger LOGGER = Logger.getLogger(DriverType.class.getName());

    private String code;
    private String className;
    private Class<?> clazz;
    private boolean available = false;

    public static void init(Instrumentation instrumentation) {
        for (DriverType driverType : values()) {
            driverType.initValue(instrumentation);
        }
    }

    private void initValue(Instrumentation instrumentation) {
        try {
            clazz = getClass(instrumentation, className);
            available = true;
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Error while init DriverType - {0}", new Object[]{e.getMessage()});
        }
    }

    DriverType(String code, String className) {
        this.code = code;
        this.className = className;
    }

    public String getCode() {
        return code;
    }

    public String getClassName() {
        return className;
    }

    private static Class<?> getClass(Instrumentation instrumentation, String className) throws ClassNotFoundException {

        // see if we can get the class using forName
        try {
            return Class.forName(className);
        } catch (Exception ex) {
            LOGGER.log(Level.INFO, "Class not found with Class.forName - " + className);
        }

        // otherwise iterate all loaded classes and find what we want
        for (Class<?> clazz : instrumentation.getAllLoadedClasses()) {
            if (clazz.getName().equals(className)) {
                return clazz;
            }
        }

        throw new ClassNotFoundException(className + " not found.");
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public boolean isAvailable() {
        return available;
    }
}
