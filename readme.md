**Introduction**

Detects anomaly/outlier when a stream of metrics are streamed over to the library.
Currently supported algorithms:

1. Simple Moving Average

Keeps track of the moving average and computes a range using a moving standard deviation. Based off how sparse the data
is the initial few results for anomalies may be erroneous. As the sample size increased the model starts to get more accurate.


**How Should I use it**

Outliers are streamed out using Kafka. You need to provide the kafka client library:

```xml
<dependency>
        <groupId>org.apache.kafka</groupId>
        <artifactId>kafka-clients</artifactId>
        <version>1.0.0</version>
        <scope>provided</scope>
  </dependency>
```

Other mechanisms of pushing out the anomalies will be added soon

_Usage_

```java
  private AnomalyDetectApi anomalyDetectApi = new AnomalyDetectApiImpl
            .Builder()
            .withThreshold(1)
            .withMessageBrokerType(MessageBrokerType.KAFKA)
            .withMessageBrokerConfig(
                    new KafkaConfig
                        .Builder()
                        .withTopicName("topic-name")
                        .withBootstrapServers("localhost:9092")
                        .build()
            );

        anomalyDetectApi.addDataPoint("string-metric-name",double-value);
```