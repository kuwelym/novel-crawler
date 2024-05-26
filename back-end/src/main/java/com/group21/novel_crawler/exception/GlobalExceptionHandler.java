package com.group21.novel_crawler.exception;

import com.group21.novel_crawler.common.ResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ResponseObject handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseObject(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null);
    }

    @ExceptionHandler(InvalidRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseObject handleInvalidRequestException(InvalidRequestException ex) {
        return new ResponseObject(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody ResponseObject handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return new ResponseObject(HttpStatus.CONFLICT.value(), ex.getMessage(), null);
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody ResponseObject handleUnauthorizedException(UnauthorizedException ex) {
        return new ResponseObject(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), null);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ResponseObject handleInternalServerErrorException(InternalServerErrorException ex) {
        return new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null);
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ResponseObject handleInternalServerErrorException(IOException  ex) {
        log.error(ex.getMessage());
        return new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null);
    }
}
