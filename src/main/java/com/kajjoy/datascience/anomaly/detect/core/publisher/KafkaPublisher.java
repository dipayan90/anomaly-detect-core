package com.kajjoy.datascience.anomaly.detect.core.publisher;

import com.kajjoy.datascience.anomaly.detect.core.config.KafkaConfig;
import com.kajjoy.datascience.anomaly.detect.core.config.MessageBrokerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaPublisher implements Publisher {

    private Producer<String, String> producer;
    private String topicName;

    public KafkaPublisher(MessageBrokerConfig messageBrokerConfig){
        if(messageBrokerConfig instanceof KafkaConfig){
            Properties props = ((KafkaConfig) messageBrokerConfig).getProperties();
            if(props.getProperty("bootstrap.servers") == null){
                throw new IllegalArgumentException("In Kafka Config bootstrap server property needs to be provided");
            }
            if(props.getProperty("topic.name") == null){
                throw  new IllegalArgumentException("In Kafka Config kafka topic name must be specified");
            }
            producer = getProducer(props);
            topicName = props.getProperty("topic.name");
        }else {
            throw new IllegalArgumentException("If broker type is kafka you need to pass a KafkaConfig");
        }
    }

    @Override
    public void publish(String key,String value) {
        producer.send(new ProducerRecord<>(topicName,key,value));
    }

    private Producer<String, String> getProducer(Properties props){
        return producer == null ? new KafkaProducer<>(props) : producer;
    }

    protected void setProducer(Producer<String, String> producer){
        this.producer = producer;
    }

}
