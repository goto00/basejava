package com.urise.webapp.exception;

public class ExistStorageException extends StorageException{
    public ExistStorageException(String uuid) {
        super("Resume with uuid " + uuid + " already exist in the storage", uuid);

    }
}
