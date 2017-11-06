package com.kajjoy.datascience.anomaly.detect.core.storage;


public interface StorageApi {
    void update(String key,double value);
    double get(String key);
    int getCount();
}
