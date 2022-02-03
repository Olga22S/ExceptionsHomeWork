package ru.skypro.ExceptionsHomeWork.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ArrayIsFullException extends Exception {

    public ArrayIsFullException(String message) {
        super(message);
    }
}
