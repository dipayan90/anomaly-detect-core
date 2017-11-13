package com.kajjoy.datascience.anomaly.detect.core.service;

import com.kajjoy.datascience.anomaly.detect.core.config.MessageBrokerConfig;
import com.kajjoy.datascience.anomaly.detect.core.config.MessageBrokerType;
import com.kajjoy.datascience.anomaly.detect.core.math.StatsService;
import com.kajjoy.datascience.anomaly.detect.core.model.Stats;
import com.kajjoy.datascience.anomaly.detect.core.publisher.KafkaPublisher;
import com.kajjoy.datascience.anomaly.detect.core.publisher.Publisher;

public class DetectionService {

    private static DetectionService detectionService;

    private int threshold;
    private MessageBrokerConfig messageBrokerConfig;
    private MessageBrokerType messageBrokerType;
    private Publisher publisher;

    private DetectionService(){}

    private DetectionService(int threshold){
        this.threshold = threshold;
    }

    private DetectionService(int threshold, MessageBrokerConfig messageBrokerConfig,MessageBrokerType messageBrokerType){
        this.threshold = threshold;
        this.messageBrokerConfig = messageBrokerConfig;
        this.messageBrokerType = messageBrokerType;
    }

    public static DetectionService getDetectionService(int threshold,
                                                       MessageBrokerConfig messageBrokerConfig,
                                                       MessageBrokerType messageBrokerType){
        if(detectionService == null){
            detectionService = new DetectionService(threshold,messageBrokerConfig,messageBrokerType);
        }
        return detectionService;
    }

    public static DetectionService getDetectionService(int threshold){
        if(detectionService == null){
            detectionService = new DetectionService(threshold);
        }
        return detectionService;
    }

    private StatsService statsService = StatsService.getStatsService();

    public Stats updateStats(String key,Stats stats,double dataPoint){
        if(stats == null){
            stats = new Stats();
        }
        if(isAnomaly(dataPoint,stats)){
            //publish anomaly
            System.out.println("Anomaly is: "+ dataPoint);

            if(messageBrokerType != null && messageBrokerConfig != null){
                switch (messageBrokerType){
                    case KAFKA:{
                        publisher = new KafkaPublisher(messageBrokerConfig);
                    }
                }
                publisher.publish(key,String.valueOf(dataPoint));
            }


        }
        stats.setStandardDeviation(statsService.calculateNewStandardDeviation(dataPoint,stats));
        stats.setMean(statsService.calculateNewMean(dataPoint,stats));
        stats.setCount(stats.getCount() + 1);
        return stats;
    }

    private boolean isAnomaly(double dataPoint,Stats stats){
        if(stats != null){
            if(stats.getStandardDeviation() != null && stats.getMean() != null){
                double range = stats.getStandardDeviation() * threshold;
                double upperRange = stats.getMean() + range;
                double lowerRange = stats.getMean() - range;
                if(dataPoint > upperRange || dataPoint<lowerRange){
                    return true;
                }
            }
        }
            return false;
    }


}
