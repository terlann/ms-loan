package az.kapitalbank.loan.exception;

import java.util.HashMap;
import java.util.Map;

import az.kapitalbank.loan.exception.model.ErrorCodes;
import az.kapitalbank.loan.exception.model.ExceptionModel;
import az.kapitalbank.loan.exception.model.SourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static nonapi.io.github.classgraph.utils.FastPathResolver.resolve;

@Slf4j
@RestControllerAdvice
public class BaseExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SourceNotFoundException.class)
    public ResponseEntity<ExceptionModel> handleSourceNotFindException(SourceNotFoundException ex) {
        log.error("Exception - {}", ex.getMessage());
        var code = ErrorCodes.ERROR_003.getCode();
        var message = ErrorCodes.ERROR_003.getMessage();
        var exceptionModel = ExceptionModel.of(code, message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionModel);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        log.info(ex.toString());
        log.info(request.toString());

        Map<String, String> warnings = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors())
            warnings.put(fieldError.getField(), resolve(fieldError.getDefaultMessage()));

        var code = ErrorCodes.BAD_REQUEST.getCode();
        var message = ErrorCodes.BAD_REQUEST.getMessage();
        var exceptionModel = ExceptionModel.of(code, message, warnings);
        return ResponseEntity.status(status).body(exceptionModel);
    }
}
