package az.kapitalbank.loan.exception;

import az.kapitalbank.loan.dto.response.WrapperResponse;
import az.kapitalbank.loan.exception.model.ErrorCodes;
import az.kapitalbank.loan.exception.model.ExceptionModel;
import az.kapitalbank.loan.exception.model.SourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        log.info(ex.toString());
        log.info(request.toString());
        return super.handleHttpMessageNotReadable(ex, headers, status, request);
    }
}
