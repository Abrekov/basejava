package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    protected void fillGap(int index) {
        if (index < size) {
            System.arraycopy(storage, index + 1, storage, index, size - index);
        }
    }

    protected void insert(Resume resume) {
        int newIndex = -(getIndex(resume.getUuid())) - 1;
        System.arraycopy(storage, newIndex, storage, newIndex + 1, size - newIndex);
        storage[newIndex] = resume;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume();
        resume.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, resume);
    }
}
