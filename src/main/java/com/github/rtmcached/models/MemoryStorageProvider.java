package com.github.rtmcached.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MemoryStorageProvider implements StorageProvider  {

    private final Map<Class<?>, MemoryModelStorage<? extends Model>> storage = new HashMap<>();

    public <T extends Model> MemoryModelStorage<T> getModelStorage(Class<? extends Model> modelClass) {
        Objects.requireNonNull(modelClass);

        MemoryModelStorage<T> resultModelStorage = (MemoryModelStorage<T>) storage.get(modelClass);
        if (resultModelStorage == null) {
            resultModelStorage = new MemoryModelStorage<>();
            storage.put(modelClass, resultModelStorage);
        }
        return resultModelStorage;
    }
}