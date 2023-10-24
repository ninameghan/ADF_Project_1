package org.example.service.exceptions;

import org.springframework.dao.DuplicateKeyException;

public class SalonIdAlreadyExistsException extends DuplicateKeyException {
    public SalonIdAlreadyExistsException(String msg) {
        super(msg);
    }
}
