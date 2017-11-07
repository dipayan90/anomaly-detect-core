package com.kajjoy.datascience.anomaly.detect.core.math;

import com.kajjoy.datascience.anomaly.detect.core.model.Stats;

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
        return Math.sqrt(Arrays.stream(numbers)
                .map(num -> Math.pow((num-mean),2))
                .sum()
                /numbers.length);
    }

    double calculateNewMean(double newNumber,Stats stats){
        if(stats.getCount() == 0){
            return newNumber;
        }
        return ( stats.getMean() + newNumber ) / (stats.getCount() + 1);
    }

    double calculateNewStandardDeviation(double newNumber, Stats stats){
        if(stats.getCount() == 0){
            return 0;
        }
        double newMean = calculateNewMean(newNumber,stats);
        double oldMean = stats.getMean();
        double oldVariance = Math.pow(stats.getStandardDeviation(),2);
        Integer count = stats.getCount() + 1;
        double newVariance = ( ( (count - 2) * oldVariance ) + ((newNumber - newMean) * (newNumber - oldMean)))/( count - 1 );
        return Math.sqrt(newVariance);
    }
}
