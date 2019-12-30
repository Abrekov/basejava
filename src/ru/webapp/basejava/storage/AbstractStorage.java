package ru.webapp.basejava.storage;

import ru.webapp.basejava.exception.ExistStorageException;
import ru.webapp.basejava.exception.NotExistStorageException;
import ru.webapp.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void delete(String uuid) {
        int index = checkNotExists(uuid);
        deleteResume(index);
    }

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        int index = checkNotExists(uuid);
        updateResume(index, resume);
    }

    public Resume get(String uuid) {
        int index = checkNotExists(uuid);
        return getResume(index);
    }

    protected int checkNotExists(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return index;
    }

    protected int checkExist(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            throw new ExistStorageException(uuid);
        }
        return index;
    }

    protected abstract void deleteResume(int index);

    protected abstract int getIndex(String uuid);

    protected abstract void updateResume(int index, Resume resume);

    protected abstract Resume getResume(int index);
}
