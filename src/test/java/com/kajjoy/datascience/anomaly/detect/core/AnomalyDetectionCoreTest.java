package com.kajjoy.datascience.anomaly.detect.core;


import com.kajjoy.datascience.anomaly.detect.core.api.AnomalyDetectApi;
import com.kajjoy.datascience.anomaly.detect.core.api.AnomalyDetectApiImpl;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Random;

@RunWith(MockitoJUnitRunner.class)
public class AnomalyDetectionCoreTest {

    private Logger logger = Logger.getLogger(AnomalyDetectionCoreTest.class);

    private AnomalyDetectApi anomalyDetectApi = new AnomalyDetectApiImpl
            .Builder()
            .withThreshold(1)
            .build();

    @Test
    public void playApplication(){
        new Random().ints(100, 0, 100).forEach(e -> {
            logger.debug("number is: "+ e);
            anomalyDetectApi.addDataPoint("sample",e);
        });
    }

    @Test
    public void testApplication(){
        int[] intArray = new int[]{23,27,26,21,20,22,99,20,1,25,23,27,21,20,88,3,27};
        Arrays.stream(intArray).forEach(e -> {
            anomalyDetectApi.addDataPoint("sample",e);
        });
    }
}
