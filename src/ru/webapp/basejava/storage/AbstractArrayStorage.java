package ru.webapp.basejava.storage;

import ru.webapp.basejava.exception.StorageException;
import ru.webapp.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    private static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void deleteResume(int index) {
        size--;
        remove(index);
        storage[size] = null;
    }

    public void save(Resume resume) {
        String uuid = resume.getUuid();
        if (size >= storage.length) {
            throw new StorageException("The storage is full", uuid);
        }
        int index = checkExist(uuid);
        insert(resume, index);
        size++;
    }

    protected void updateResume(int index, Resume resume) {
        storage[index] = resume;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected Resume getResume(int index) {
        return storage[index];
    }

    /**
     * @return array, contains only Resumes in ru.webapp.basejava.storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    protected abstract void insert(Resume resume, int index);

    protected abstract void remove(int index);

}
