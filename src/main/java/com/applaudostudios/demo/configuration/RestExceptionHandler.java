package com.applaudostudios.demo.configuration;

import com.applaudostudios.demo.configuration.errors.ErrorInfo;
import com.applaudostudios.demo.configuration.exceptions.ItemAlreadyCreatedException;
import com.applaudostudios.demo.configuration.exceptions.ItemNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

    private ResponseEntity<ErrorInfo> error(final Exception exception, final HttpStatus httpStatus,
                                            HttpServletRequest request) {
        return new ResponseEntity<>(new ErrorInfo(exception, request.getRequestURI()), httpStatus);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorInfo> handleRuntimeException(HttpServletRequest request, final RuntimeException e) {
        log.error("Exception : ", e);
        return error(e, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(ItemAlreadyCreatedException.class)
    public ResponseEntity<ErrorInfo> handleItemAlreadyCreatedException(HttpServletRequest request, final ItemAlreadyCreatedException e) {
        log.error("Exception : ", e);
        return error(e, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ErrorInfo> handleItemNotFoundException(HttpServletRequest request, final ItemNotFoundException e) {
        log.error("Exception : ", e);
        return error(e, HttpStatus.NOT_FOUND, request);
    }
}
