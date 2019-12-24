package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not found!");
        } else {
            System.arraycopy(storage, index + 1, storage, index, size - index);
            storage[size - 1] = null;
            size--;
        }
    }

    @Override
    public void save(Resume resume) {
        if (size >= storage.length) {
            System.out.println("The storage is full");
        } else {
            int index = getIndex(resume.getUuid());
            if (index >= 0) {
                System.out.println("Resume " + resume.getUuid() + " already exists!");
            } else {
                index = -(index) - 1;
                if (index != size) {
                    System.arraycopy(storage, index, storage, index + 1, size - index);
                }
                storage[index] = resume;
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
