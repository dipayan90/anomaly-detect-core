package com.kajjoy.datascience.anomaly.detect.core.service;

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

    int calculateMean(int var1,int var2){
        return (var1 + var2)/2;
    }

    int calculateMean(int[] numbers){
        if(numbers.length == 0){
            return 0;
        }
        return Arrays.stream(numbers).sum()/numbers.length;
    }
}
