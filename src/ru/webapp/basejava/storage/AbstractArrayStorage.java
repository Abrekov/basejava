package ru.webapp.basejava.storage;

import ru.webapp.basejava.exception.StorageException;
import ru.webapp.basejava.model.Resume;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {
    private static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected void doUpdate(Resume resume, Object index) {
        storage[(Integer) index] = resume;
    }

    public List<Resume> getAllSorted() {
        List<Resume> list = Arrays.asList(Arrays.copyOf(storage, size));
        Collections.sort(list);
        return list;
    }

    @Override
    protected void doSave(Resume resume, Object index) {
        if (size >= storage.length) {
            throw new StorageException("The storage is full", resume.getUuid());
        }
        insert(resume, (Integer) index);
        size++;
    }

    @Override
    protected void doDelete(Object index) {
        remove((Integer) index);
        storage[--size] = null;
    }

    @Override
    protected Resume doGet(Object index) {
        return storage[(Integer) index];
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    protected abstract void insert(Resume resume, int index);

    protected abstract void remove(int index);

}
