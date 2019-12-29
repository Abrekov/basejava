package ru.webapp.basejava.storage;

import ru.webapp.basejava.exception.ExistStorageException;
import ru.webapp.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> storage = new ArrayList<>();

    public void deleteResume(int index) {
        storage.remove(index);
    }

    public void save(Resume resume) {
        String uuid = resume.getUuid();
        int index = getIndex(uuid);
        if (index >= 0) {
            throw new ExistStorageException(uuid);
        }
        storage.add(resume);
    }

    protected void addResume(int index, Resume resume) {
        storage.set(index, resume);
    }

    public void clear() {
        storage.clear();
    }

    protected Resume getResume(int index) {
        return storage.get(index);
    }

    public Resume[] getAll() {
        return (Resume[]) storage.toArray();
    }

    public int size() {
        return storage.size();
    }

    protected int getIndex(String uuid) {
        int size = storage.size();
        for (int i = 0; i < size; i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

}
