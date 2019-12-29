package ru.webapp.basejava.storage;

import ru.webapp.basejava.model.Resume;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    protected List<Resume> storage = new LinkedList<>();

    public void clear() {
        storage.clear();
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
