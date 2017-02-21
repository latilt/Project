package com.example.storage;

/**
 * Created by hokyeong on 2017. 2. 21..
 */
public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}

