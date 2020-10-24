package com.applaudostudios.demo.config;

import com.applaudostudios.demo.config.errors.ErrorInfo;
import com.applaudostudios.demo.config.exceptions.ItemAlreadyCreatedException;
import com.applaudostudios.demo.config.exceptions.ItemDeleteException;
import com.applaudostudios.demo.config.exceptions.ItemNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
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

    @ExceptionHandler(ItemDeleteException.class)
    public ResponseEntity<ErrorInfo> handleItemDeleteException(HttpServletRequest request, final ItemDeleteException e) {
        log.error("Exception : ", e);
        return error(e, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorInfo> handleBadCredentialsException(HttpServletRequest request, final BadCredentialsException e) {
        log.error("Exception : ", e);
        return error(e, HttpStatus.UNAUTHORIZED, request);
    }

}
