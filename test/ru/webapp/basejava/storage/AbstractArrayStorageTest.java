package ru.webapp.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.webapp.basejava.exception.ExistStorageException;
import ru.webapp.basejava.exception.NotExistStorageException;
import ru.webapp.basejava.exception.StorageException;
import ru.webapp.basejava.model.Resume;

public abstract class AbstractArrayStorageTest {
    private static final int STORAGE_LIMIT = 10_000;
    private Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME1 = new Resume(UUID_1);
    private static final Resume RESUME2 = new Resume(UUID_2);
    private static final Resume RESUME3 = new Resume(UUID_3);

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME1);
        storage.save(RESUME2);
        storage.save(RESUME3);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        int size = storage.size();
        storage.delete(UUID_3);
        Assert.assertEquals(size - 1, storage.size());
        storage.get(UUID_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_4);
    }

    @Test
    public void save() {
        int size = storage.size();
        Resume resume = new Resume(UUID_4);
        storage.save(resume);
        Assert.assertEquals(size + 1, storage.size());
        Assert.assertEquals(resume, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME3);
    }

    @Test(expected = StorageException.class)
    public void saveFullStorage() {
        storage.clear();
        try {
            for (int i = 1; i <= STORAGE_LIMIT; i++) {
                storage.save(new Resume("uuid" + i));
            }
        } catch (StorageException e) {
            Assert.fail("The storage is full");
        }
        storage.save(new Resume("uuid10001"));
    }

    @Test
    public void update() {
        Resume resume = new Resume(UUID_1);
        storage.update(resume);
        Assert.assertSame(resume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        Resume resume = new Resume(UUID_4);
        storage.update(resume);
    }

    @Test
    public void clear() {
        Resume[] expected = new Resume[0];
        storage.clear();
        Assert.assertArrayEquals(expected, storage.getAll());
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals(RESUME3, storage.get(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void getAll() {
        Resume[] expected = new Resume[]{RESUME1, RESUME2, RESUME3};
        Resume[] actual = storage.getAll();
        Assert.assertArrayEquals(expected, actual);
        Assert.assertEquals(storage.size(), actual.length);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }
}