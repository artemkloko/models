package com.github.rtmcached.models;

import java.util.Collection;

public interface ModelStorage<T extends Model> {

    public T create(T instance);

    public T get(Integer id);

    public Collection<T> get();

    public T update(T instance);

    public void delete(T instance);
}