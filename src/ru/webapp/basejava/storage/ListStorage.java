package ru.webapp.basejava.storage;

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
        checkExist(uuid);
        storage.add(resume);
    }

    protected void updateResume(int index, Resume resume) {
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
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

}
