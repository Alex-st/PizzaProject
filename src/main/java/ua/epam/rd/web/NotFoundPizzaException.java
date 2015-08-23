package ua.epam.rd.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.management.RuntimeErrorException;

/**
 * Created by alex on 8/10/15.
 */

// в responseStatus маємо вказати тип. В такому випадку у випадку помилки замість стектрейсу буде лише помилка 404
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Pizza not found")
public class NotFoundPizzaException extends RuntimeException {

    public NotFoundPizzaException(String string) {
        super(string);
    }
}
