package ru.webapp.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.webapp.basejava.exception.ExistStorageException;
import ru.webapp.basejava.exception.NotExistStorageException;
import ru.webapp.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractStorageTest {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String NAME_1 = "fullName1";
    private static final String NAME_2 = "fullName2";
    private static final String NAME_3 = "fullName3";
    private static final String NAME_4 = "fullName4";
    private static final Resume RESUME1 = new Resume(UUID_1, NAME_1);
    private static final Resume RESUME2 = new Resume(UUID_2, NAME_2);
    private static final Resume RESUME3 = new Resume(UUID_3, NAME_3);
    private static final Resume RESUME4 = new Resume(UUID_4, NAME_4);

    protected AbstractStorageTest(Storage storage) {
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
        storage.save(RESUME4);
        Assert.assertEquals(size + 1, storage.size());
        Assert.assertEquals(RESUME4, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME3);
    }

    @Test
    public void update() {
        Resume resume = new Resume(UUID_1, NAME_1);
        storage.update(resume);
        Assert.assertSame(resume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        Resume resume = new Resume(UUID_4, NAME_4);
        storage.update(resume);
    }

    @Test
    public void clear() {
        storage.clear();
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
    public void getAllSorted() {
        List<Resume> actual = storage.getAllSorted();
        Assert.assertEquals(3, actual.size());
        Assert.assertEquals(Arrays.asList(RESUME1, RESUME2, RESUME3), actual);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }
}