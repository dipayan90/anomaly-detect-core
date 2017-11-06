package com.kajjoy.datascience.anomaly.detect.core.math;

import java.util.Arrays;

public class StatsService {
    private StatsService(){}
    private static StatsService statsService;
    public static StatsService getStatsService(){
        if(statsService == null){
            statsService = new StatsService();
        }
        return statsService;
    }

    double calculateMean(double var1,double var2){
        return (var1 + var2)/2;
    }

    double calculateMean(double[] numbers){
        if(numbers.length == 0){
            return 0;
        }
        return Arrays.stream(numbers).sum()/numbers.length;
    }

    double calculateStandardDeviation(double[] numbers){
        if(numbers.length == 0){
            return 0;
        }
        double mean = calculateMean(numbers);
        return Math.sqrt(Arrays.stream(numbers).map(num -> Math.pow((num-mean),2)).sum()/numbers.length);
    }
}
