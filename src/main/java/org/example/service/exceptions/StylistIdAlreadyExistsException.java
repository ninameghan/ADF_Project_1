package org.example.service.exceptions;

import org.springframework.dao.DuplicateKeyException;

public class StylistIdAlreadyExistsException extends DuplicateKeyException {
    public StylistIdAlreadyExistsException(String msg) {
        super(msg);
    }
}
