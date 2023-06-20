package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("ERROR: No results were found. " +
                    "Resume with uuid \"" + uuid + "\" is not presented in the storage");
            return null;
        }
        return storage[index];
    }

    protected abstract int getIndex(String uuid);

    protected abstract void saveResume(Resume r, int index);
    protected abstract void deleteResume(int index);

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            storage[index] = r;
        } else {
            System.out.println("ERROR: Update wasn't complete. " +
                    "Resume with uuid " + r.getUuid() + " is not presented in the storage");
        }
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (size >= STORAGE_LIMIT) {
            System.out.println("ERROR: Storage is full. " +
                    "Resume with uuid " + r.getUuid() + " wasn't saved.");
        } else if (index >= 0) {
            System.out.println("ERROR: Saving wasn't complete. " +
                    "Resume with uuid " + r.getUuid() + " already presented in the storage");
        } else {
            saveResume(r, index);
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            deleteResume(index);
        } else {
            System.out.println("ERROR: Resume with uuid " + uuid + " is not presented in the storage");
        }
    }
}
