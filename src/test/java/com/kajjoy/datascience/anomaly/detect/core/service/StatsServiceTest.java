package com.kajjoy.datascience.anomaly.detect.core.service;


import org.junit.Assert;
import org.junit.Test;

public class StatsServiceTest {

    private StatsService statsService = StatsService.getStatsService();

    @Test
    public void testStatsServiceMeanWithTwoNumbers(){
        Assert.assertEquals(15,statsService.calculateMean(10,20));
        Assert.assertEquals(15,statsService.calculateMean(0,30));
    }

    @Test
    public void testStatsServiceMeanWithNumberArray(){
        Assert.assertEquals(3,statsService.calculateMean(new int[]{1,2,3,4,5}));
    }

}
