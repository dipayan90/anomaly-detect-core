package com.kajjoy.datascience.anomaly.detect.core;


import com.kajjoy.datascience.anomaly.detect.core.api.AnomalyDetectApi;
import com.kajjoy.datascience.anomaly.detect.core.api.AnomalyDetectApiImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Random;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationTest {

    private AnomalyDetectApi anomalyDetectApi = new AnomalyDetectApiImpl.Builder()
            .withThreshold(1).build();

    @Test
    public void test(){
        new Random().ints(100, 0, 100).forEach(e -> {
            System.out.println("number is: "+ e);
            anomalyDetectApi.addDataPoint("sample",e);
        });
    }
}
