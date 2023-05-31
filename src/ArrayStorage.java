import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        int resumeQty = this.size();
        for (int i = 0; i < resumeQty; i++) {
            if (storage[i] != null) {
                storage[i] = null;
            }
            else {
                break;
            }
        }
    }

    void save(Resume r) {
        int freeSpaceIndex = this.size();
        storage[freeSpaceIndex] = r;
    }

    Resume get(String uuid) {
        Resume result = null;
        int resumeQty = this.size();
        for (int i = 0; i < resumeQty; i++) {
            if (storage[i].uuid.equals(uuid)) {
                result = storage[i];
                break;
            }
        }
        return result;
    }

    void delete(String uuid) {
        int resumeQty = this.size();
        for (int i = 0; i < resumeQty; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = null;
                for (int j = i + 1; j < resumeQty; j++) {
                    storage[j - 1] = storage[j];
                    storage[j] = null;
                }
                break;
            }
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int resumesQty = this.size();
        return Arrays.copyOf(storage, resumesQty);
    }

    int size() {
        int size = 0;
        for (Resume resume:
                storage) {
            if (resume != null) {
                size++;
            }
        }
        return size;
    }
}
