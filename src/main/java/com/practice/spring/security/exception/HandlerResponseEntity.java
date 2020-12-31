package com.practice.spring.security.exception;


import com.practice.spring.security.common.respond.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice()
public class HandlerResponseEntity extends ResponseEntityExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(HandlerResponseEntity.class);

    @ExceptionHandler(InputRequestException.class)
    public ResponseEntity<ResponseData> handleBadRequestException(InputRequestException ex) {
        log.info("Bad request exception");
        return ResponseEntity.ok(ResponseData.ofFail(ex.getMessage()));
    }

    @ExceptionHandler(value = {IllegalArgumentException.class,
            IllegalStateException.class})
    public ResponseEntity<ResponseData> handleInternalServerException(InternalServerException ex) {
        log.info("Internal server exception");
        return ResponseEntity.ok(ResponseData.ofFail(ex.getMessage()));
    }

    @ExceptionHandler(ExistedDataException.class)
    public ResponseEntity<ResponseData> handleUserException(ExistedDataException ex) {
        log.info("Data existed exception");
        return ResponseEntity.ok(ResponseData.ofFail(ex.getMessage()));
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ResponseData> handleMaxSizeException(MaxUploadSizeExceededException ex) {
        log.info("File size exception");
        return ResponseEntity.ok(ResponseData.ofFail(ex.getMessage()));
    }
}
