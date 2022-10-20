package az.kapitalbank.loan.exception.handler;

import az.kapitalbank.loan.constants.Error;
import az.kapitalbank.loan.dto.response.ErrorResponseDto;
import az.kapitalbank.loan.exception.CommonException;
import java.util.HashMap;
import java.util.Map;
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

@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String EXCEPTION = "Error Response - {} , Exception - {}";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        log.error("Request - {}, Exception: {} ", request.toString(), ex);
        Map<String, String> warnings = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            warnings.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        var errorResponseDto = new ErrorResponseDto(Error.INVALID_REQUEST, warnings);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ErrorResponseDto> commonException(
            CommonException ex) {
        log.error(EXCEPTION, ex.getError(), ex);
        var errorResponseDto = new ErrorResponseDto(ex.getError());
        return ResponseEntity.status(ex.getError().getStatus()).body(errorResponseDto);
    }
}
