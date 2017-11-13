package com.kajjoy.datascience.anomaly.detect.core.config;

import java.util.Properties;

public class KafkaConfig implements MessageBrokerConfig{

    private String topicName;
    private String bootstrapServers;

    private KafkaConfig(){}

    private KafkaConfig(Builder builder){
        this.topicName = builder.topicName;
        this.bootstrapServers = builder.bootstrapServers;
    }

    public Properties getProperties(){
        Properties props = new Properties();
        props.put("bootstrap.servers", this.bootstrapServers);


        //If the request fails, the producer can automatically retry,
        props.put("retries", 0);

        //Specify buffer size in config
        props.put("batch.size", 16384);

        //Reduce the no of requests less than 0
        props.put("linger.ms", 1);

        //The buffer.memory controls the total amount of memory available to the producer for buffering.
        props.put("buffer.memory", 33554432);

        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        props.put("topic.name",this.topicName);
        return props;
    }

    public static class Builder{
        String topicName;
        String bootstrapServers;

        public Builder withTopicName(String topicName){
            this.topicName = topicName;
            return this;
        }

        public Builder withBootstrapServers(String servers){
            this.bootstrapServers = servers;
            return this;
        }

        public KafkaConfig build(){
            return new KafkaConfig(this);
        }

    }

}
