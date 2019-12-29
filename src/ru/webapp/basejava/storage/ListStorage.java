package ru.webapp.basejava.storage;

import ru.webapp.basejava.model.Resume;

public class ListStorage extends AbstractStorage {

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        storage.set(index, resume);
    }

    @Override
    public void save(Resume resume) {
        storage.add(resume);
    }

    @Override
    public Resume get(String uuid) {
        return storage.get(getIndex(uuid));
    }

    @Override
    public void delete(String uuid) {
        storage.remove(getIndex(uuid));
    }

    @Override
    public Resume[] getAll() {
        return (Resume[]) storage.toArray();
    }

}
