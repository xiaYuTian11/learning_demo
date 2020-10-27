package com.demo.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author TMW
 * @since 2020/10/27 13:55
 */
public class ProducerDemo {
    private final Producer<String, String> kafkaProducer;
    public final static String TOPIC = "java_topic";

    private ProducerDemo() {
        kafkaProducer = createKafkaProducer();
    }

    private Producer<String, String> createKafkaProducer() {
        Properties prop = new Properties();
        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.119.111:9092");
        prop.put(ProducerConfig.ACKS_CONFIG, "all");
        prop.put(ProducerConfig.RETRIES_CONFIG, 0);
        prop.put(ProducerConfig.BATCH_SIZE_CONFIG, 16 * 1024);
        prop.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        prop.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 32 * 1024 * 1024);
        prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return new KafkaProducer<>(prop);
    }

    private void producer() {
        for (int i = 0; i < 1000; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String key = "key" + i;
            String data = "hello kafka message:" + key;

            kafkaProducer.send(new ProducerRecord<>(TOPIC, key, data), new Callback() {

                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    System.out.println("生产完成" + recordMetadata.topic());
                }
            });
        }
    }

    public static void main(String[] args) {
        new ProducerDemo().producer();
    }
}
