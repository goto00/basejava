package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp(){
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals(new Resume(UUID_1), storage.get(UUID_1));
    }

    @Test
    public void getAll() {
        Assert.assertEquals(3, storage.getAll().length);
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Resume updateResume = new Resume(UUID_1);
        storage.update(updateResume);
        Assert.assertEquals(updateResume, storage.get(UUID_1));
    }

    @Test
    public void save() {
        Resume newResume = new Resume(UUID_4);
        storage.save(newResume);
        Assert.assertEquals(newResume, storage.get(UUID_4));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test(expected = ExistStorageException.class)
    public void saveExisted() {
        storage.save(new Resume(UUID_1));
    }

    @Test(expected = StorageException.class)
    public void saveWithOverflow() {
        while (storage.size() < 10000) {
            try {
                storage.save(new Resume(UUID.randomUUID().toString()));
            } catch (Exception e) {
                Assert.fail("Exception happens early than expected");
            }
        }
        storage.save(new Resume(UUID.randomUUID().toString()));
    }
}