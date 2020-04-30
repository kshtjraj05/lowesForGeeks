package com.lowes.lowesForGeeks.controller;

import com.lowes.lowesForGeeks.util.ErrorMessage;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ValidationException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ControllerExceptionHandler  {
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    ErrorMessage exceptionHandler(NoSuchElementException e){
        return new ErrorMessage("404",e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    ErrorMessage exceptionHandler(ValidationException e){
        return new ErrorMessage("400", e.getMessage());
    }
}
