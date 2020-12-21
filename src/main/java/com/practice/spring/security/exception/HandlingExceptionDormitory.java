package com.practice.spring.security.exception;


import com.practice.spring.security.bundle.MessageBundle;
import com.practice.spring.security.common.respond.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice()
public class HandlingExceptionDormitory extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(HandlingExceptionDormitory.class);

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException ex) {
        return ResponseEntity.ok(ResponseData.ofFail(ex.getMessage()));
    }

    @ExceptionHandler(value = {IllegalArgumentException.class,
            IllegalStateException.class})
    public ResponseEntity<?> handleInternalServerException(InternalServerException ex) {
        return ResponseEntity.ok(ResponseData.ofFail(MessageBundle.getMessage("", ex.getMessage())));
    }

    @ExceptionHandler(ExistedDataException.class)
    public ResponseEntity<?> handleUserException(ExistedDataException ex) {
        logger.error("Invalid Input Exception: ", ex.getMessage());
        return ResponseEntity.ok(ResponseData.ofFail(ex.getMessage()));
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<?> handleMaxSizeException(MaxUploadSizeExceededException ex) {
        return ResponseEntity.ok(ResponseData.ofFail(ex.getMessage()));
    }


}
