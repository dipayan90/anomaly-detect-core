package com.kajjoy.datascience.anomaly.detect.core.storage;


import com.kajjoy.datascience.anomaly.detect.core.model.Stats;

public interface StorageApi {
    void update(String key,Stats value);
    Stats get(String key);
    int getCount();
}
