package com.github.rtmcached.models;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MemoryModelStorage<T extends Model> implements ModelStorage<T> {

    private final Map<Integer, T> storage = new HashMap<Integer, T>();

    @Override
    public T create(T instance) {
        Objects.requireNonNull(instance);
        if (instance.id != null)
            throw new IllegalArgumentException("Can not create an already saved instance, use update instead.");

        Integer id = (Integer) storage.size() + 1;
        instance.id = id;
        storage.put(id, instance);
        return instance;
    }

    @Override
    public T get(Integer id) {
        Objects.requireNonNull(id);

        return storage.get(id);
    }

    @Override
    public Collection<T> get() {
        return storage.values();
    }

    @Override
    public T update(T instance) {
        Objects.requireNonNull(instance);
        Objects.requireNonNull(instance.id, "Can not update an unsaved instance, use create first.");

        storage.put(instance.id, instance);
        return instance;
    }

    @Override
    public void delete(T instance) {
        Objects.requireNonNull(instance);
        Objects.requireNonNull(instance.id, "Can not delete an unsaved instance, use create first.");

        storage.remove(instance.id);
    }
}