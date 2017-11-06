package com.kajjoy.datascience.anomaly.detect.core.storage;

import com.kajjoy.datascience.anomaly.detect.core.model.Stats;

import java.util.HashMap;
import java.util.Map;

public class InMemoryStorageRepository implements StorageRepository<Stats> {

    private InMemoryStorageRepository(){}

    private static InMemoryStorageRepository inMemoryStorageRepository;

    public static InMemoryStorageRepository getInMemoryStorageRepository(){
        if(inMemoryStorageRepository == null){
            inMemoryStorageRepository = new InMemoryStorageRepository();
        }
        return inMemoryStorageRepository;
    }

    private Map<String,Stats> repo = new HashMap<>();

    @Override
    public Stats get(String key) {
        if(key != null && !key.equals("") && repo.containsKey(key)){
            return repo.get(key);
        }
        return null;
    }

    @Override
    public void put(String key, Stats value) {
        if(key != null && !key.equals("")){
            repo.put(key,value);
        }
    }

    @Override
    public int count() {
        return repo.size();
    }
}
