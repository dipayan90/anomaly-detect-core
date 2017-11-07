package com.kajjoy.datascience.anomaly.detect.core.model;

import lombok.Data;

@Data
public class Stats {
    Integer count;
    Double mean;
    Double standardDeviation;

    public Stats(){
        this.count = 0;
        this.mean = 0d;
        this.standardDeviation = 0d;
    }

}
