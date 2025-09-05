package br.com.guilhermetassinari.gestao_cursos.exceptions;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController {

    private MessageSource messageSource;

    public ExceptionHandlerController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ErrorMessageDTO> dto = new ArrayList<>();

        e.getBindingResult().getFieldErrors().forEach((err) -> {
            String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());
            ErrorMessageDTO error = new ErrorMessageDTO(err.getField(), message);
            dto.add(error);
        });

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NameFoundException.class)
    public ResponseEntity<ErrorMessageDTO> handleUserFoundException(NameFoundException e) {
        ErrorMessageDTO error = new ErrorMessageDTO(e.getMessage(), "name");
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorMessageDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        Throwable cause = e.getCause();

        if (cause instanceof JsonMappingException jsonMappingException) {
            Throwable rootCause = jsonMappingException.getCause();

            if (rootCause instanceof StatusRequiredException) {
                ErrorMessageDTO error = new ErrorMessageDTO(rootCause.getMessage(), "status");
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }

        }
        ErrorMessageDTO error = new ErrorMessageDTO("Invalid request format", "request");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
