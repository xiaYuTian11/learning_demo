package com.demo.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
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
        prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.119.111:9092");
        prop.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
        prop.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        prop.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        consumer = new KafkaConsumer<>(prop);

    }

    private void consumer() {
        consumer.subscribe(Collections.singletonList(ProducerDemo.TOPIC));
        while (true) {
            ConsumerRecords<String, String> poll = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> record : poll) {
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
            }
        }
    }

    public static void main(String[] args) {
        new ConsumerDemo().consumer();
    }
}
