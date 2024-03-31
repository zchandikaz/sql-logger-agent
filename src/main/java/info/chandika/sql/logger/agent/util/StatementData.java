package info.chandika.sql.logger.agent.util;

/**
 * @author : chandika
 * @since : 2022-07-19(Tue) 21:00
 **/


public class StatementData {
    private Class dataType;
    private Object data;

    public StatementData(Class dataType, Object data) {
        this.dataType = dataType;
        this.data = data;
    }

    public String getSqlValue() {
        if (data == null) {
            return "null";
        }
        return "'" + data.toString() + "'";
    }


}
