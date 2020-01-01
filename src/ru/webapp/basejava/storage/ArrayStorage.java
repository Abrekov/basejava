package ru.webapp.basejava.storage;

import ru.webapp.basejava.model.Resume;

/**
 * Array based ru.webapp.basejava.storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected void remove(int index) {
        storage[index] = storage[size - 1];
    }

    @Override
    protected void insert(Resume resume, int index) {
        storage[size] = resume;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
