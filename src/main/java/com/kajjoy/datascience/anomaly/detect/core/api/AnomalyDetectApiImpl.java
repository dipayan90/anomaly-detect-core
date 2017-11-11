package com.kajjoy.datascience.anomaly.detect.core.api;

import com.kajjoy.datascience.anomaly.detect.core.model.Stats;
import com.kajjoy.datascience.anomaly.detect.core.service.DetectionService;
import com.kajjoy.datascience.anomaly.detect.core.storage.InMemoryStorageRepository;
import com.kajjoy.datascience.anomaly.detect.core.storage.StorageRepository;

public class AnomalyDetectApiImpl implements AnomalyDetectApi {
    private AnomalyDetectApiImpl(){}

    private int threshold;
    private StorageRepository<Stats> repo;
    private DetectionService detectionService;

    private AnomalyDetectApiImpl(Builder builder){
        this.threshold = builder.threshold;
        repo = InMemoryStorageRepository.getInMemoryStorageRepository();
        detectionService =  DetectionService.getDetectionService(threshold);
    }

    @Override
    public void addDataPoint(String key, double dataPoint) {
        Stats stats = repo.get(key);
        Stats updatedStats = detectionService.updateStats(stats,dataPoint);
        repo.put(key,updatedStats);
    }

    public static class Builder{
        int threshold;

        public Builder withThreshold(int threshold){
            this.threshold = threshold;
            return this;
        }

        public AnomalyDetectApiImpl build(){
           return new AnomalyDetectApiImpl(this);
        }
    }
}
