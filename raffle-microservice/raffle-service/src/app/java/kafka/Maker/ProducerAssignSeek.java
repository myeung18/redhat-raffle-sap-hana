package kafka.Maker;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

import static kafka.Config.getLabProducerProp;

public class ProducerAssignSeek {
    private Logger logger = LoggerFactory.getLogger(ProducerAssignSeek.class);

    public static void main(String[] args) {
        ProducerAssignSeek pas = new ProducerAssignSeek();
        pas.start();
    }

    public void start() {
        String topic = "some-topic";
        int freq = 3;
        Properties prop = getLabProducerProp();

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(prop);

        for (int i = 0; i < freq; i++) {
            ProducerRecord<String, String> record =
                    new ProducerRecord<String, String>(topic, "test msg-" + i);

            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata r, Exception e) {
                    if (e != null) {
                        logger.info("Recd t:" + r.topic() + " p:" + r.partition() + ",os:"
                                + ", ts:" + r.timestamp());
                    } else {
                        logger.error("error while publishing ", e);
                    }
                }
            });
        }
        producer.flush();
        producer.close();
    }
}
