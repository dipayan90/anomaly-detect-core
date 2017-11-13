package com.kajjoy.datascience.anomaly.detect.core.publisher;


import com.kajjoy.datascience.anomaly.detect.core.config.KafkaConfig;
import com.kajjoy.datascience.anomaly.detect.core.config.MessageBrokerConfig;
import org.apache.kafka.clients.producer.MockProducer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class KafkaPublisherTest {

    private MockProducer<String, String> producer;

    @Before
    public void setUp() {
        producer = new MockProducer<>(
                true, new StringSerializer(), new StringSerializer());
    }


    @Test
    public void testKafkaProducer(){

        MessageBrokerConfig brokerConfig = new KafkaConfig
                .Builder()
                .withBootstrapServers("localhost:9092")
                .withTopicName("test-topic").build();

        KafkaPublisher kafkaPublisher = new KafkaPublisher(brokerConfig);

    }
}
