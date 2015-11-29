package com.github.rtmcached.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

class ModelMock extends Model {

    public String title;
}

public class MemoryModelStorageTest {

    MemoryModelStorage<ModelMock> target;
    ModelMock firstInstance;
    ModelMock secondInstance;
    ModelMock thirdInstance;

    @Before
    public void context() {
        target = new MemoryModelStorage<ModelMock>();
        firstInstance = new ModelMock();
        firstInstance.title = "first instance";
        secondInstance = new ModelMock();
        secondInstance.title = "second instance";
        thirdInstance = new ModelMock();
        thirdInstance.title = "third instance";
    }

    // create

    @Test
    public void test_create_successful() {
        ModelMock result = target.create(firstInstance);
        Assert.assertNotNull(result.id);
        Assert.assertEquals("first instance", result.title);
    }

    @Test
    public void test_create_assigns_different_ids() {
        // action
        ModelMock firstResult = target.create(firstInstance);
        ModelMock secondResult = target.create(secondInstance);
        ModelMock thirdResult = target.create(thirdInstance);
        // assertions
        Assert.assertNotSame(firstResult.id, secondResult.id);
        Assert.assertNotSame(secondResult.id, thirdResult.id);
        Assert.assertNotSame(thirdResult.id, firstResult.id);
    }

    @Test(expected=NullPointerException.class)
    public void test_create_null() {
        target.create(null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void test_create_already_saved_instance() {
        target.create(firstInstance);
        target.create(firstInstance);
    }

    // get one

    @Test
    public void test_get_one_successful() {
        // context
        target.create(firstInstance);
        target.create(secondInstance);
        target.create(thirdInstance);
        // action
        ModelMock result = target.get(secondInstance.id);
        // assertions
        Assert.assertEquals(secondInstance.id, result.id);
        Assert.assertEquals("second instance", result.title);
    }

    @Test
    public void test_get_one_successful_empty() {
        ModelMock result = target.get(1);
        Assert.assertNull(result);
    }

    @Test(expected=NullPointerException.class)
    public void test_get_one_null() {
        target.get(null);
    }

    // get all

    @Test
    public void test_get_all_successful() {
        // context
        target.create(firstInstance);
        target.create(secondInstance);
        // action
        Collection<ModelMock> result = target.get();
        // assertions
        Assert.assertEquals(2, result.size());
        // context
        target.create(thirdInstance);
        // action
        result = target.get();
        // assertions
        Assert.assertEquals(3, result.size());
    }

    @Test
    public void test_get_all_successful_empty() {
        // action
        Collection<ModelMock> result = target.get();
        // assertions
        Assert.assertEquals(0, result.size());
    }

    // update

    @Test
    public void test_update_successful() {
        // context
        target.create(firstInstance);
        target.create(secondInstance);
        target.create(thirdInstance);
        secondInstance.title = "updated second title";
        // action
        ModelMock result = target.update(secondInstance);
        // assertions for returned instance
        Assert.assertEquals(secondInstance.id, result.id);
        Assert.assertEquals("updated second title", result.title);
        // assertions for stored instance
        result = target.get(secondInstance.id);
        Assert.assertEquals("updated second title", result.title);
    }

    @Test(expected=NullPointerException.class)
    public void test_update_null() {
        target.update(null);
    }

    @Test(expected=NullPointerException.class)
    public void test_update_unsaved_instance() {
        target.update(firstInstance);
    }

    // delete

    @Test
    public void test_delete_successful() {
        // context
        target.create(firstInstance);
        target.create(secondInstance);
        target.create(thirdInstance);
        // action
        target.delete(secondInstance);
        // assertions
        Collection<ModelMock> result = target.get();
        Assert.assertEquals(2, result.size());
        for (ModelMock instance : result)
            Assert.assertNotSame(secondInstance.id, instance.id);
    }

    @Test(expected=NullPointerException.class)
    public void test_delete_null() {
        target.delete(null);
    }

    @Test(expected=NullPointerException.class)
    public void test_delete_unsaved_instance() {
        target.delete(firstInstance);
    }
}