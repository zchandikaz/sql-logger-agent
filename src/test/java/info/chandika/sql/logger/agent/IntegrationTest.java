package info.chandika.sql.logger.agent;

import info.chandika.sql.logger.agent.WrapPostgresDriver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author : chandika
 * @since : 2022-09-24(Sat) 21:42
 **/
public class IntegrationTest {
    public static void main(String[] args) throws Exception {
        setup();

        var driver = new WrapPostgresDriver();
        var props = new Properties();
        props.setProperty("password", "verchaska@123");
        props.setProperty("user", "ve_live_db_user");
        try(var con = driver.connect("jdbc:postgresql://pgdb.veapp.net:5440/travel_wallet",props)) {

            executeQuery(con);
            executeQuery(con);
            executeQuery1(con);
            executeQuery1(con);
        }
    }

    private static void setup() {
        System.setProperty("connection.wrapper.caller.enabled", "true");
        System.setProperty("connection.wrapper.sout.enabled", "true");
        System.setProperty("connection.wrapper.enabled", "true");
        System.setProperty("connection.wrapper.richsql.enabled", "true");
        System.setProperty("connection.wrapper.testcall.enabled", "false");
        System.setProperty("connection.wrapper.caching.resultset.available", "true");
        System.setProperty("connection.wrapper.redis.url", "redis://localhost:6379/1");
        System.setProperty("connection.wrapper.caching.app.name", "test");
        System.setProperty("connection.wrapper.caching.resultset.ttl.sec", "50000");
    }

    private static void executeQuery(Connection con) throws SQLException {
        var ps = con.prepareStatement("select * from t_wallet_transfer_details limit ?");
        ps.setInt(1,10);
        var rs = ps.executeQuery();
        while (rs.next()){
            System.out.println(rs.getDouble("amount"));
        }
    }

    private static void executeQuery1(Connection con) throws SQLException {
        var ps = con.prepareStatement("select supplier0_.supplier_pid as supplier1_7_0_, supplier0_.product_code as product_2_7_0_, supplier0_.supplier_code as supplier3_7_0_, supplier0_.supplier_name as supplier4_7_0_ from m_supplier supplier0_ where supplier0_.supplier_pid= '2'");
//        ps.setInt(1,10);
        var rs = ps.executeQuery();
        while (rs.next()){
            System.out.println(rs.getString("supplier4_7_0_"));
        }
    }
}
