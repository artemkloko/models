package com.github.rtmcached.models;

import org.junit.Assert;
import org.junit.Test;

class FirstModelMock extends Model { }

class SecondModelMock extends Model { }

public class MemoryStorageProviderTest {

    MemoryStorageProvider target = new MemoryStorageProvider();

    @Test
    public void test_getModelStorage_success() {
        MemoryModelStorage<FirstModelMock> firstStorage = target.getModelStorage(FirstModelMock.class);
        MemoryModelStorage<FirstModelMock> duplicateFirstStorage = target.getModelStorage(FirstModelMock.class);
        Assert.assertNotNull(firstStorage);
        Assert.assertSame(firstStorage, duplicateFirstStorage);
        MemoryModelStorage<SecondModelMock> secondStorage = target.getModelStorage(SecondModelMock.class);
        Assert.assertNotNull(secondStorage);
        Assert.assertNotSame(firstStorage, secondStorage);
    }

    @Test(expected=NullPointerException.class)
    public void test_getModelStorage_null() {
        target.getModelStorage(null);
    }
}