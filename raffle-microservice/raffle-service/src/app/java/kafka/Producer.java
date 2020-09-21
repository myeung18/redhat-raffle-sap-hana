package kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class Producer {

    public static void main(String[] args) {

        Producer pr = new Producer();
        pr.createProducer();

    }

    void createProducer() {
        String bootstrap = "127.0.0.1:9092";

        Properties prop = new Properties();
        prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
        prop.setProperty("key.serializer", StringSerializer.class.getName());
        prop.setProperty("value.serializer", StringSerializer.class.getName());

        KafkaProducer<String, String> prod = new KafkaProducer<>(prop);
        ProducerRecord<String, String> record = new ProducerRecord<>("first_topic", "from java proj");
        prod.send(record);

        prod.flush();
        prod.close();
    }
}
