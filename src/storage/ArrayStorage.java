package storage;

import model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    boolean resumeExists(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    void update(Resume resume) {
        if (resumeExists(resume.uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].uuid.equals(resume.uuid)) {
                    storage[i] = resume;
                }
            }
        } else {
            System.out.println("model.Resume not found!");
        }
    }

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size < 10000) {
            if (!resumeExists(r.uuid)) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("model.Resume already exists!");
            }
        } else {
            System.out.println("The storage is full");
        }
    }

    public Resume get(String uuid) {
        if (resumeExists(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].uuid.equals(uuid)) {
                    return storage[i];
                }
            }
        } else {
            System.out.println("model.Resume not found!");
        }
        return null;
    }

    public void delete(String uuid) {
        if (resumeExists(uuid)) {
            int newSize = size - 1;
            for (int i = 0; i <= newSize; i++) {
                if (storage[i].uuid.equals(uuid)) {
                    System.arraycopy(storage, i + 1, storage, i, newSize - i);
                    storage[newSize] = null;
                    break;
                }
            }
            size = newSize;
        } else {
            System.out.println("model.Resume not found!");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
