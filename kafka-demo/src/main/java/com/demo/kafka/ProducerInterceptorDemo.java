package com.demo.kafka;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @author TMW
 * @since 2020/10/27 22:21
 */
public class ProducerInterceptorDemo implements ProducerInterceptor<String, String> {

    private volatile long sendSuccess = 0;
    private volatile long sendFailure = 0;

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {

        String modifiedlValue = "tmw_" + record.value();

        return new ProducerRecord<>(record.topic(), record.partition(), record.timestamp(), record.key(), modifiedlValue, record.headers());
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if (exception == null) {
            sendSuccess++;
        } else {
            sendFailure++;
        }
    }

    @Override
    public void close() {
        double successRation = (double) sendSuccess / (sendFailure + sendSuccess);
        System.out.println("[INFO] 发送成功率=" + String.format("%f", successRation * 100 + "%"));
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
