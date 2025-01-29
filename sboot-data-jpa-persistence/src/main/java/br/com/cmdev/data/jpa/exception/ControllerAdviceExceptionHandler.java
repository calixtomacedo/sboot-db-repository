package br.com.cmdev.data.jpa.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
@Log4j2
public class ControllerAdviceExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseErrorMessage> handler(RuntimeException exception) {
        var classError = Arrays.stream(exception.getStackTrace()).findFirst();
        StringBuilder builderMessage = new StringBuilder();
        builderMessage.append(exception.getClass().getCanonicalName());
        builderMessage.append(" occurred in the class: ".concat(classError.get().getClassName()));
        builderMessage.append(" Method: ".concat(classError.get().getMethodName()));
        builderMessage.append(" Number line: ".concat(String.valueOf(classError.get().getLineNumber())));
        log.info("Error Stack Trace: {}", exception.getStackTrace());
        String messageError = exception.getCause() != null ? exception.getCause().getMessage() : exception.getMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR,  exception.getMessage(), builderMessage.toString(), messageError));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ResponseErrorFields>> handler(MethodArgumentNotValidException validException) {
        List<FieldError> fieldErrors = validException.getBindingResult().getFieldErrors();
        List<ResponseErrorFields> responseErrors = new ArrayList<>();
        fieldErrors.forEach(error -> {
            responseErrors.add(new ResponseErrorFields(error.getField(), error.getDefaultMessage()));
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseErrors);
    }
}
