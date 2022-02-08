package ru.skypro.ExceptionsHomeWork.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NotCorrectNameException extends Exception {

    public NotCorrectNameException(String message) {
        super(message);
    }
}
