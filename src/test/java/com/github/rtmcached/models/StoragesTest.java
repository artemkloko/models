package com.github.rtmcached.models;

import org.junit.Assert;
import org.junit.Test;

class ModelMock extends Model { }

class StorageProviderMock implements StorageProvider {

    @Override
    public <T extends Model> MemoryModelStorage<T> getModelStorage(Class<? extends Model> modelClass) {
        throw new RuntimeException("Method invoked successfully.");
    }
}

public class StoragesTest {

    @Test(expected=NullPointerException.class)
    public void test_setStorage_null() {
        Storages.setStorage(null);
    }

    @Test(expected=NullPointerException.class)
    public void test_getStorage_without_setStorage() {
        Storages.getStorage(ModelMock.class);
    }

    @Test
    public void test_getStorage_success() {
        Storages.setStorage(new StorageProviderMock());
        try {
            ModelStorage<ModelMock> storage = Storages.getStorage(ModelMock.class);
        } catch (RuntimeException e) {
            Assert.assertEquals("Method invoked successfully.", e.getMessage());
            return;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.fail("Storages.getStorage() did not invoke getModelStorage() on the storage stored by Storages.setStorage().");
    }
}