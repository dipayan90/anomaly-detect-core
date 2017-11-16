package com.kajjoy.datascience.anomaly.detect.core.publisher;

import com.kajjoy.datascience.anomaly.detect.core.config.KafkaConfig;
import com.kajjoy.datascience.anomaly.detect.core.config.MessageBrokerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaPublisher implements Publisher {
    private Producer<String, String> producer;
    private Properties props;

    private KafkaPublisher(){}

    public KafkaPublisher(MessageBrokerConfig messageBrokerConfig){
        if(messageBrokerConfig instanceof KafkaConfig){
            props = ((KafkaConfig) messageBrokerConfig).getProperties();
            if(props.getProperty("bootstrap.servers") == null){
                throw new IllegalArgumentException("In Kafka Config bootstrap server property needs to be provided");
            }
            if(props.getProperty("topic.name") == null){
                throw  new IllegalArgumentException("In Kafka Config kafka topic name must be specified");
            }

        }else {
            throw new IllegalArgumentException("If broker type is kafka you need to pass a KafkaConfig");
        }
    }

    @Override
    public void publish(String key,String value) {
        String topicName = props.getProperty("topic.name");
        if(producer != null){
            producer.send(new ProducerRecord<>(topicName,key,value));
        }else {
            getKafkaProducer(props).send(new ProducerRecord<>(topicName,key,value));
        }
    }

    private Producer<String,String> getKafkaProducer(Properties props){
        if(producer == null){
            producer = new KafkaProducer<>(props);
        }
        return producer;
    }

    protected void setProducer(Producer<String, String> producer){
        this.producer = producer;
    }

}
