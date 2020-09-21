package kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class Config {
    public static Properties getLocalProp(String... group) {
        String bootstrapServers = "127.0.0.1:9092";
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        if (group != null && group.length > 0) {
            properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, group[0]);
        }

        return properties;
    }

    public static Properties getLabConsumerProp(String... group) {
        String bootstrapServers = "tenant1-kafka-bootstrap-strimzi-tenant1.apps.mw-ocp4.cloud.lab.eng.bos.redhat.com:443";
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
//        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "none");
        properties.put("security.protocol", "SSL");
        properties.put("ssl.truststore.location", "src/app/java/kafka/truststore_team_lab1.jks");
        properties.put("ssl.truststore.password", "password");
//        properties.put("ssl.keystore.location","src/app/java/kafka/truststore_team_lab2.jks");
//        properties.put("ssl.keystore.password","");
//        properties.put("ssl.key.password","");
        if (group != null && group.length > 0) {
            properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, group[0]);
        }

        return properties;
    }
    public static Properties getLabProducerProp(String... group) {
        String bootstrapServers = "tenant1-kafka-bootstrap-strimzi-tenant1.apps.mw-ocp4.cloud.lab.eng.bos.redhat.com:443";
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put("security.protocol", "SSL");
        properties.put("ssl.truststore.location", "src/app/java/kafka/truststore_team_lab1.jks");
        properties.put("ssl.truststore.password", "password");

        return properties;

    }
}
