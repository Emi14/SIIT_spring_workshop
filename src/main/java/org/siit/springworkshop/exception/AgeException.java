package org.siit.springworkshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid age. Maximum age is 90.")
public class AgeException extends Exception{

    public AgeException(String message) {
        super(message);
    }
}
