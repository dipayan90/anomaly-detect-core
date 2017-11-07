package com.kajjoy.datascience.anomaly.detect.core.storage;

import com.kajjoy.datascience.anomaly.detect.core.model.Stats;

public class StorageApiImpl implements StorageApi{

    private static StorageApiImpl storageApiImpl;

    private StorageApiImpl(){}

    public static StorageApi getStorageApi(){
        if(storageApiImpl == null){
            storageApiImpl = new StorageApiImpl();
        }
        return storageApiImpl;
    }

    private StorageRepository<Stats> repo = InMemoryStorageRepository.getInMemoryStorageRepository();

    @Override
    public void update(String key, Stats value) {
        repo.put(key,value);
    }

    @Override
    public Stats get(String key) {
        return repo.get(key);
    }

    @Override
    public int getCount() {
        return repo.count();
    }
}
