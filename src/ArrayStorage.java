/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        int length = size();
        for (int i = 0; i < length; i++) {
            if (storage[i] == null) {
                break;
            }
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        storage[size()] = r;
        size++;
    }

    Resume get(String uuid) {
        int length = size();
        for (int i = 0; i < length; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int newSize = size() - 1;
        for (int i = 0; i <= newSize; i++) {
            if (storage[i].uuid.equals(uuid)) {
                System.arraycopy(storage, i + 1, storage, i, newSize - i);
                storage[newSize] = null;
                break;
            }
        }
        size = newSize;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int length = size();
        Resume[] resumes = new Resume[length];
        System.arraycopy(storage, 0, resumes, 0, length);
        return resumes;
    }

    int size() {
        return size;
    }
}
