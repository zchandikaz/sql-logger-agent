package info.chandika.sql.logger.agent.instrumentation.transformer;

/**
 * @author : chandika
 * @since : 2024-03-27(Wed) 20:30
 **/
public enum DriverType {
    ORACLE("ORACLE", ""),
    POSTGRES("POSTGRES", "org.postgresql.Driver");

    private String code;
    private String className;

    DriverType(String code, String className) {
        this.code = code;
        this.className = className;
    }
}
