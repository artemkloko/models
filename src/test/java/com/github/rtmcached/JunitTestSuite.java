package com.github.rtmcached;

import com.github.rtmcached.models.MemoryModelStorageTest;
import com.github.rtmcached.models.MemoryStorageProviderTest;
import com.github.rtmcached.models.StoragesTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    MemoryModelStorageTest.class,
    MemoryStorageProviderTest.class,
    StoragesTest.class
})
public class JunitTestSuite { }