package ru.webapp.basejava.storage;

import ru.webapp.basejava.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    private static final Comparator<Resume> RESUME_COMPARATOR = ((o1, o2) -> o1.getUuid().compareTo(o2.getUuid()));

    @Override
    protected void remove(int index) {
        if (index < size) {
            System.arraycopy(storage, index + 1, storage, index, size - index);
        }
    }

    @Override
    protected void insert(Resume resume, int index) {
        int newIndex = -index - 1;
        System.arraycopy(storage, newIndex, storage, newIndex + 1, size - newIndex);
        storage[newIndex] = resume;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume resume = new Resume(uuid, "Name");
        return Arrays.binarySearch(storage, 0, size, resume, RESUME_COMPARATOR);
    }
}
