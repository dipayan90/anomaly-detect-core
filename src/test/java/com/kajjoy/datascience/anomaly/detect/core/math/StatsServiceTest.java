package com.kajjoy.datascience.anomaly.detect.core.math;


import com.kajjoy.datascience.anomaly.detect.core.model.Stats;
import org.junit.Assert;
import org.junit.Test;

public class StatsServiceTest {

    private StatsService statsService = StatsService.getStatsService();

    @Test
    public void testStatsServiceMeanWithTwoNumbers(){
        Assert.assertEquals(15,statsService.calculateMean(10,20),0d);
        Assert.assertEquals(15,statsService.calculateMean(0,30),0d);
    }

    @Test
    public void testStatsServiceMeanWithNumberArray(){
        Assert.assertEquals(3,statsService.calculateMean(new double[]{1,2,3,4,5}),0d);
    }

    @Test
    public void testStandardDeviationFromExistingStandardDeviation(){
        double num1 = 10;
        double num2 = 20;
        Stats stats = new Stats();

        stats.setStandardDeviation(statsService.calculateNewStandardDeviation(num1,stats));
        stats.setMean(statsService.calculateNewMean(num1,stats));
        stats.setCount(stats.getCount() + 1);

        Assert.assertEquals(num1,stats.getMean(),0d);
        Assert.assertEquals(0,stats.getStandardDeviation(),0d);
        Assert.assertEquals(1,stats.getCount(),0d);

        stats.setStandardDeviation(statsService.calculateNewStandardDeviation(num2,stats));
        stats.setMean(statsService.calculateNewMean(num2,stats));
        stats.setCount(stats.getCount() + 1);

        Assert.assertEquals(2,stats.getCount(),0d);
        Assert.assertEquals(15,stats.getMean(),0d);
        Assert.assertEquals(7.07,stats.getStandardDeviation(),0.02);
    }

}
