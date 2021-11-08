package myapps;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class KafkaProd {
    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-pipe");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put("value.serializer", "myapps.DatasetSerializer");
        final StreamsBuilder builder = new StreamsBuilder();

        Producer<String, Map<String, Object>> producer = new KafkaProducer<String, Map<String, Object>>(
                props, new StringSerializer(), new DatasetSerializer()
        );

        for (int i = 0; i < 100000; i++) {
            Map<String, Object> x = new HashMap<>();
            x.put("dataset", "asdasd"+String.valueOf(i) );
            x.put("namespace", "asdasd"+String.valueOf(i) );
            if (i%2 == 0)
                x.put("other", new HashMap<String, String>());

            producer.send(new ProducerRecord<String, Map<String, Object>>("quickstart-events", String.valueOf(i),x));
            Thread.sleep(1000);
        }

    }

}
