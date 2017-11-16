package com.kajjoy.datascience.anomaly.detect.core.api;

import com.kajjoy.datascience.anomaly.detect.core.config.MessageBrokerConfig;
import com.kajjoy.datascience.anomaly.detect.core.config.MessageBrokerType;
import com.kajjoy.datascience.anomaly.detect.core.model.Stats;
import com.kajjoy.datascience.anomaly.detect.core.service.DetectionService;
import com.kajjoy.datascience.anomaly.detect.core.storage.InMemoryStorageRepository;
import com.kajjoy.datascience.anomaly.detect.core.storage.StorageRepository;

public class AnomalyDetectApiImpl implements AnomalyDetectApi {
    private AnomalyDetectApiImpl(){}

    private StorageRepository<Stats> repo;
    private DetectionService detectionService;

    private AnomalyDetectApiImpl(Builder builder){
        repo = InMemoryStorageRepository.getInMemoryStorageRepository();

        if(builder.threshold != 0 && builder.messageBrokerType != null && builder.messageBrokerConfig != null){
            detectionService =  DetectionService.getDetectionService(
                    builder.threshold,
                    builder.messageBrokerConfig,
                    builder.messageBrokerType
            );
        }

        if(builder.threshold != 0 && builder.messageBrokerType == null){
            detectionService = DetectionService.getDetectionService(builder.threshold);
        }

    }

    @Override
    public void addDataPoint(String key, double dataPoint) {
        Stats stats = repo.get(key);
        Stats updatedStats = detectionService.updateStats(key,stats,dataPoint);
        repo.put(key,updatedStats);
    }

    public static class Builder{
        int threshold;
        MessageBrokerType messageBrokerType;
        MessageBrokerConfig messageBrokerConfig;

        public Builder withThreshold(int threshold){
            this.threshold = threshold;
            return this;
        }

        public Builder withMessageBrokerType(MessageBrokerType messageBrokerType){
            this.messageBrokerType = messageBrokerType;
            return this;
        }

        public Builder withMessageBrokerConfig(MessageBrokerConfig messageBrokerConfig){
            this.messageBrokerConfig = messageBrokerConfig;
            return this;
        }

        public AnomalyDetectApiImpl build(){
           return new AnomalyDetectApiImpl(this);
        }
    }
}
