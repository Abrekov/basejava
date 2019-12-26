package ru.webapp.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.webapp.basejava.exception.ExistStorageException;
import ru.webapp.basejava.exception.NotExistStorageException;
import ru.webapp.basejava.exception.StorageException;
import ru.webapp.basejava.model.Resume;

public abstract class AbstractArrayStorageTest {
    private Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete("uuid3");
        storage.get("uuid3");
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("uuid4");
    }

    @Test
    public void save() throws Exception {
        Resume resume = new Resume("uuid4");
        storage.save(resume);
        Assert.assertEquals(resume, storage.get("uuid4"));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        Resume resume = new Resume("uuid3");
        storage.save(resume);
    }

    @Test(expected = StorageException.class)
    public void saveFullStorage() {
        try {
            for (int i = 4; i <= 10_000; i++) {
                storage.save(new Resume("uuid" + i));
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume("uuid10001"));
    }

    @Test
    public void update() throws Exception {
        Resume resume = new Resume("uuid1");
        storage.update(resume);
        Assert.assertEquals(resume, storage.get("uuid1"));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        Resume resume = new Resume("uuid4");
        storage.update(resume);
    }

    @Test
    public void clear() throws Exception {
        Resume[] expected = new Resume[0];
        storage.clear();
        Assert.assertArrayEquals(expected, storage.getAll());
    }

    @Test
    public void get() throws Exception {
        Resume resume = new Resume("uuid3");
        Assert.assertEquals(resume, storage.get("uuid3"));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void getAll() throws Exception {
        Resume[] expected = new Resume[]{new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};
        Assert.assertArrayEquals(expected, storage.getAll());
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }
}