package com.tmw;

import lombok.extern.java.Log;
import org.postgresql.replication.LogSequenceNumber;
import org.postgresql.replication.PGReplicationStream;

import java.nio.ByteBuffer;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

/**
 * @author TMW
 * @date 2020/5/21 9:41
 */
@Log
public class PgReplicaTest {

    public static void main(String[] args) throws Exception {
        createReplication();
        // createLogicalReplicationWithLsn();

        while (true) {
            //non blocking receive message
            ByteBuffer msg = createLogicalReplicationWithTime().readPending();

            if (msg == null) {
                TimeUnit.MILLISECONDS.sleep(10L);
                continue;
            }

            int offset = msg.arrayOffset();
            byte[] source = msg.array();
            int length = source.length - offset;
            System.out.println(new String(source, offset, length));
        }
    }

    public static void createReplication() throws SQLException {
        PgConnect.getConnection().getReplicationAPI()
                .createReplicationSlot()
                .logical()
                .withSlotName("demo_logical_slot_002")
                .withOutputPlugin("test_decoding")
                .make();
        log.info("create replication slot...");
    }

    public static void createLogicalReplication() throws SQLException {
        PGReplicationStream stream =
                PgConnect.getConnection().getReplicationAPI()
                        .replicationStream()
                        .logical()
                        .withSlotName("demo_logical_slot")
                        .withSlotOption("include-xids", false)
                        .withSlotOption("skip-empty-xacts", true)
                        .start();
        log.info("create logical replication ...");
    }

    public static void createLogicalReplicationWithLsn() throws SQLException {
        LogSequenceNumber lsn = LogSequenceNumber.valueOf("6F/E3C53568");
        PGReplicationStream stream =
                PgConnect.getConnection().getReplicationAPI()
                        .replicationStream()
                        .logical()
                        .withSlotName("demo_logical_slot")
                        .withSlotOption("include-xids", false)
                        .withSlotOption("skip-empty-xacts", true)
                        .withStartPosition(lsn)
                        .start();
        log.info("create logical replication with lsn ...");
    }

    public static PGReplicationStream createLogicalReplicationWithTime() throws SQLException {
        PGReplicationStream stream =
                PgConnect.getConnection().getReplicationAPI()
                        .replicationStream()
                        .logical()
                        .withSlotName("demo_logical_slot_002")
                        .withSlotOption("include-xids", false)
                        .withSlotOption("skip-empty-xacts", true)
                        .withStatusInterval(20, TimeUnit.SECONDS)
                        .start();
        log.info("create logical replication with lsn ...");
        return stream;
    }

}
