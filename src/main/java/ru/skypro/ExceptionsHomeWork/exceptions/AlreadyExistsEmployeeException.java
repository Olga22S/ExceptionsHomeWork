package ru.skypro.ExceptionsHomeWork.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AlreadyExistsEmployeeException extends RuntimeException {

    public AlreadyExistsEmployeeException(String message) {
        super(message);
    }
}
