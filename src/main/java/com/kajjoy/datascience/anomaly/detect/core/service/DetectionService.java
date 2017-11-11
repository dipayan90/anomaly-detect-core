package com.kajjoy.datascience.anomaly.detect.core.service;

import com.kajjoy.datascience.anomaly.detect.core.math.StatsService;
import com.kajjoy.datascience.anomaly.detect.core.model.Stats;

public class DetectionService {

    private static DetectionService detectionService;

    int threshold;

    private DetectionService(){}

    private DetectionService(int threshold){
        this.threshold = threshold;
    }

    public static DetectionService getDetectionService(int threshold){
        if(detectionService == null){
            detectionService = new DetectionService(threshold);
        }
        return detectionService;
    }

    private StatsService statsService = StatsService.getStatsService();

    public Stats updateStats(Stats stats,double dataPoint){
        if(stats == null){
            stats = new Stats();
        }
        if(isAnomaly(dataPoint,stats)){
            //publish anomaly
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
                if(dataPoint > upperRange && dataPoint<lowerRange){
                    return true;
                }
            }
        }
            return false;
    }


}
