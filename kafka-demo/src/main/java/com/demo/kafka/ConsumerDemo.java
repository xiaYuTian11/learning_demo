package com.demo.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

/**
 * @author TMW
 * @since 2020/10/27 13:55
 */
public class ConsumerDemo {
    private final KafkaConsumer<String, String> consumer;

    private ConsumerDemo() {
        Properties prop = new Properties();
        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.119.111:9092");
        prop.put("group.id", "test");
        prop.put("enable.auto.commit", true);
        prop.put("auto.commit.interval.ms", "1000");
        prop.put("key.deserializer", StringDeserializer.class.getName());
        prop.put("value.deserializer", StringDeserializer.class.getName());
        consumer = new KafkaConsumer<>(prop);
    }

    private void consumer() {
        consumer.subscribe(Collections.singletonList(ProducerDemo.TOPIC));
        while (true) {
            ConsumerRecords<String, String> poll = consumer.poll(100);
            for (ConsumerRecord<String, String> record : poll) {
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
            }
        }
    }

    public static void main(String[] args) {
        new ConsumerDemo().consumer();
    }
}
