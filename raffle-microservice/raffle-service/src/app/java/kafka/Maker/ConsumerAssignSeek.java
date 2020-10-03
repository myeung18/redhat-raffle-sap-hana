package kafka.Maker;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import static kafka.Config.getLabConsumerProp;

/**
 * https://github.com/dilipsundarraj1/TeachApacheKafka/blob/master/kafka/src/main/java/com/learnkafka/consumer/ConsumerKafkaSSL.java
 */
public class ConsumerAssignSeek {
    private static Logger logger = LoggerFactory.getLogger(ConsumerAssignSeek.class.getName());

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        ConsumerAssignSeek cas = new ConsumerAssignSeek();
        cas.start();
    }

    public void start() {

        String topic = "some-topic";
        // create consumer configs
        Properties properties = getLabConsumerProp();

        // create consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

        // assign and seek are mostly used to replay data or fetch a specific message

        // assign
        TopicPartition partitionToReadFrom = new TopicPartition(topic, 0);
        long offsetToReadFrom = 15L;
        consumer.assign(Arrays.asList(partitionToReadFrom));

        // seek
        consumer.seek(partitionToReadFrom, offsetToReadFrom);

        int numberOfMessagesToRead = 5;
        boolean keepOnReading = true;
        int numberOfMessagesReadSoFar = 0;

        // poll for new data
        while (keepOnReading) {
            ConsumerRecords<String, String> records =
                    consumer.poll(Duration.ofMillis(100)); // new in Kafka 2.0.0

            for (ConsumerRecord<String, String> record : records) {
                numberOfMessagesReadSoFar += 1;
                logger.info("Key: " + record.key() + ", Value: " + record.value());
                logger.info("Partition: " + record.partition() + ", Offset:" + record.offset());
                if (numberOfMessagesReadSoFar >= numberOfMessagesToRead) {
                    keepOnReading = false; // to exit the while loop
                    break; // to exit the for loop
                }
            }
        }
        logger.info("Exiting the application");
    }
}
