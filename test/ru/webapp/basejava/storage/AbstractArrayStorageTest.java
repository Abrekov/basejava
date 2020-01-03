package ru.webapp.basejava.storage;

import org.junit.Assert;
import org.junit.Test;
import ru.webapp.basejava.exception.StorageException;
import ru.webapp.basejava.model.Resume;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {
    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveFullStorage() {
        storage.clear();
        try {
            for (int i = 1; i <= STORAGE_LIMIT; i++) {
                storage.save(new Resume("uuid" + i, "fullName"));
            }
        } catch (StorageException e) {
            Assert.fail("The storage is full");
        }
        storage.save(new Resume("uuid10001", "fullName"));
    }
}
