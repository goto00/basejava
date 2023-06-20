package com.urise.webapp.exception;

public class NotExistStorageException extends StorageException{
    public NotExistStorageException(String uuid) {
        super("Resume with uuid \"" + uuid + "\" is not exist in the storage", uuid);
    }
}
