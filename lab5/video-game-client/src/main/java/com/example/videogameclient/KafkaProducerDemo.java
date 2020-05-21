package com.example.videogameclient;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Service;
import java.util.Properties;

@Service
public class KafkaProducerDemo {
  private final static String BOOTSTRAP_SERVERS = "kafka:9092";

    private static Producer<Integer, String> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        return new KafkaProducer<>(props);
    }
    public static void sendMessage(VideoGame entity, String message) throws Exception {
        final Producer<Integer, String> producer = createProducer();

        try {
            final ProducerRecord<Integer, String> record = new ProducerRecord<>(message, message + " "+ entity.getId() + " " + entity.getName());

            RecordMetadata metadata = producer.send(record).get();

            System.out.printf("sent record(key=%s value=%s) meta(partition=%d, offset=%d)", record.key(), record.value(), metadata.partition(), metadata.offset());

        } finally {
            producer.flush();
            producer.close();
        }
    }

}
