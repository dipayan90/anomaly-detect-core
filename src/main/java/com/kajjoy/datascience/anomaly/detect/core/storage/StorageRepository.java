package com.kajjoy.datascience.anomaly.detect.core.storage;

public interface StorageRepository<T> {

    T get(String key);
    void put(String key,T value);
    int count();

}
