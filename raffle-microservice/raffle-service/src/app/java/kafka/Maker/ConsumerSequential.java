package kafka.Maker;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import static kafka.Config.getLabConsumerProp;

public class ConsumerSequential {
    public static void main(String[] args) {
        ConsumerSequential con = new ConsumerSequential();
        con.start();
    }

    public void start() {
        Logger logger = LoggerFactory.getLogger(ConsumerSequential.class.getName());

        String groupId = "my-first-group";
        String topic = "some-topic";

        // create consumer configs
        Properties properties = getLabConsumerProp(groupId);

        // create consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        // subscribe consumer to our topic(s)
        consumer.subscribe(Arrays.asList(topic));

        // poll for new data
        while(true){
            ConsumerRecords<String, String> records =
                    consumer.poll(Duration.ofMillis(100)); // new in Kafka 2.0.0

            for (ConsumerRecord<String, String> record : records){
                logger.info("Key: " + record.key() + ", Value: " + record.value());
                logger.info("Partition: " + record.partition() + ", Offset:" + record.offset());
            }
        }
    }
}
