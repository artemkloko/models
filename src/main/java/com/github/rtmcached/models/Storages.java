package com.github.rtmcached.models;

import java.util.Objects;

public class Storages {

    private Storages() { }

    private static StorageProvider storageProvider;

    public static void setStorage(StorageProvider s) {
        Objects.requireNonNull(s);

        storageProvider = s;
    }

    public static <T extends Model> ModelStorage<T> getStorage(Class<? extends Model> modelClass) {
        Objects.requireNonNull(storageProvider);

        return storageProvider.getModelStorage(modelClass);
    }
}