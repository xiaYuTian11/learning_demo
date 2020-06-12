package com.tmw;

import org.postgresql.PGConnection;
import org.postgresql.PGProperty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * @author TMW
 * @date 2020/5/21 9:29
 */
public class PgConnect {

    public static PGConnection getConnection() {
        String url = "jdbc:postgresql://192.168.33.111:5432/olap";
        Properties props = new Properties();
        PGProperty.USER.set(props, "postgres");
        PGProperty.PASSWORD.set(props, "20191809");
        PGProperty.ASSUME_MIN_SERVER_VERSION.set(props, "9.4");
        PGProperty.REPLICATION.set(props, "database");
        PGProperty.PREFER_QUERY_MODE.set(props, "simple");

        try {
            Connection con = DriverManager.getConnection(url, props);
            PGConnection replConnection = con.unwrap(PGConnection.class);
            return replConnection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
