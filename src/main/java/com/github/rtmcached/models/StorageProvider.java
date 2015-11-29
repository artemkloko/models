package com.github.rtmcached.models;

public interface StorageProvider {

    public <T extends Model> ModelStorage<T> getModelStorage(Class<? extends Model> modelClass);
}