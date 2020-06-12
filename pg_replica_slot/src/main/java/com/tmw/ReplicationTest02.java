package com.tmw;

import org.postgresql.PGConnection;
import org.postgresql.PGProperty;
import org.postgresql.replication.PGReplicationStream;

import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author TMW
 * @date 2020/5/21 11:12
 */
public class ReplicationTest02 {
    public static void main(String[] args) throws Exception {
        PGConnection replConnection = PgConnect.getConnection();
        String url = "jdbc:postgresql://192.168.33.111:5432/olap";
        Properties props = new Properties();
        PGProperty.USER.set(props, "postgres");
        PGProperty.PASSWORD.set(props, "20191809");
        PGProperty.ASSUME_MIN_SERVER_VERSION.set(props, "9.4");
        PGProperty.REPLICATION.set(props, "database");
        PGProperty.PREFER_QUERY_MODE.set(props, "simple");
        Connection sqlConnection = DriverManager.getConnection(url, props);

        String slotName = "tmw_slot_002";

        replConnection.getReplicationAPI()
                .createReplicationSlot()
                .logical()
                .withSlotName(slotName)
                .withOutputPlugin("test_decoding")
                .make();

        //some changes after create replication slot to demonstrate receive it
        sqlConnection.setAutoCommit(true);
        Statement st = sqlConnection.createStatement();
        // st.execute("insert into test_logic_table(name) values('first tx changes')");
        // st.close();

        st = sqlConnection.createStatement();
        st.execute("update test_logic_table set name = 'second tx change' where pk = 1");
        st.close();

        st = sqlConnection.createStatement();
        st.execute("delete from test_logic_table where pk = 1");
        st.close();

        PGReplicationStream stream =
                replConnection.getReplicationAPI()
                        .replicationStream()
                        .logical()
                        .withSlotName(slotName)
                        .withSlotOption("include-xids", false)
                        .withSlotOption("skip-empty-xacts", true)
                        .withStatusInterval(20, TimeUnit.SECONDS)
                        .start();

        while (true) {
            //non blocking receive message
            ByteBuffer msg = stream.readPending();

            if (msg == null) {
                TimeUnit.MILLISECONDS.sleep(10L);
                continue;
            }

            int offset = msg.arrayOffset();
            byte[] source = msg.array();
            int length = source.length - offset;
            System.out.println(new String(source, offset, length));

            //feedback
            stream.setAppliedLSN(stream.getLastReceiveLSN());
            stream.setFlushedLSN(stream.getLastReceiveLSN());
        }
    }
}
