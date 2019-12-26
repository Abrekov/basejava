package ru.webapp.basejava.storage;

import ru.webapp.basejava.model.Resume;

public interface Storage {

    void clear();

    void update(Resume resume);

    void save(Resume resume);

    Resume get(String uuid);

    void delete(String uuid);

    Resume[] getAll();

    int size();
}
