package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not found!");
        } else {
            if (index < (size - 1)) {
                System.arraycopy(storage, index + 1, storage, index, size - (index + 1));
            }
            storage[size - 1] = null;
            size--;
        }
    }

    public void save(Resume resume) {
        if (size >= storage.length) {
            System.out.println("The storage is full");
        } else {
            int index = getIndex(resume.getUuid());
            if (index >= 0) {
                System.out.println("Resume " + resume.getUuid() + " already exists!");
            } else {
                int newIndex = -(index) - 1;
                System.arraycopy(storage, newIndex, storage, newIndex + 1, size - newIndex);
                storage[newIndex] = resume;
                size++;
            }
        }

    }

    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume();
        resume.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, resume);
    }
}
