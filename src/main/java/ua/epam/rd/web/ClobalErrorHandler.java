package ua.epam.rd.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by alex on 8/10/15.
 */

// приклад використання мапи до еррор сторінки через спрінгові анотації

@ControllerAdvice // працює лише з mvc-annotation driven прописаний в webContext
public class ClobalErrorHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundPizzaException.class)  // реєструємо, що даний клас має спрацьовувати на даний тип ексепшенів
    public ModelAndView exceptionHandler(Exception exception, HttpServletRequest request) {

        ModelAndView model = new ModelAndView("error"); // назва jsp

        model.addObject("ex", exception);
        model.addObject("url", request.getRequestURL());

        return model;
    }
}
