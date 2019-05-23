package io.agilehandy.messages;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.rule.EmbeddedKafkaRule;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmbeddedKafkaTests {

    @ClassRule
    public static EmbeddedKafkaRule embeddedKafkaRule = new EmbeddedKafkaRule(1);

    @Autowired
    private KafkaTemplate<byte[], byte[]> template;

    @Autowired
    private KafkaProperties properties;

    @BeforeClass
    public static void setup() {
        String brokersAsString = embeddedKafkaRule.getEmbeddedKafka().getBrokersAsString();
        System.setProperty("spring.kafka.bootstrap-servers", brokersAsString);
    }

    @Test
    public void testSendReceive() {
        template.send("output", "{id:foo}".getBytes());
        Map<String, Object> configs = properties.buildConsumerProperties();
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, "output");
        configs.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        ConsumerFactory<byte[], byte[]> cf = new DefaultKafkaConsumerFactory<>(configs);
        Consumer<byte[], byte[]> consumer = cf.createConsumer();
        consumer.subscribe(Collections.singleton("output"));
        ConsumerRecords<byte[], byte[]> records = consumer.poll(Duration.ofSeconds(10));
        consumer.commitSync();
//       assertThat(records.);

    }

}