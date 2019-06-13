package com.santander.controlegastos.web.advice;

import com.santander.controlegastos.commons.Messages;
import com.santander.controlegastos.exception.ResourceNotFoundException;
import com.santander.controlegastos.vo.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

import static java.time.Instant.now;
import static java.util.Arrays.asList;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.status;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(final Exception e) {
        log.error(e.getMessage(), e);
        return status(INTERNAL_SERVER_ERROR)
                .body(this.constructErrorResponse(INTERNAL_SERVER_ERROR, Messages.SERVER_ERROR.getMessage()));
    }

    @ExceptionHandler({ResourceNotFoundException.class, EmptyResultDataAccessException.class, EntityNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleResourceNotFound(final Exception e) {
        return status(NOT_FOUND)
                .body(this.constructErrorResponse(NOT_FOUND, Messages.RESOURCE_NOT_FOUND.getMessage()));
    }

    private ErrorResponse constructErrorResponse(final HttpStatus httpStatus, final String... messages) {
        return ErrorResponse.builder()
                .timestamp(now())
                .status(httpStatus.value())
                .error(httpStatus.getReasonPhrase())
                .messages(asList(messages))
                .build();
    }
}
