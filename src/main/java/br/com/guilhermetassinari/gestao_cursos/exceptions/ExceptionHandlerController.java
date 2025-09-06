package br.com.guilhermetassinari.gestao_cursos.exceptions;

import br.com.guilhermetassinari.gestao_cursos.modules.courses.enums.CourseStatus;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlerController {

    private MessageSource messageSource;

    public ExceptionHandlerController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    // ExceptionHandler to get the ConstraintViolationException from Jakarta
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleConstraintViolation(ConstraintViolationException e) {
        List<ErrorMessageDTO> errors = e.getConstraintViolations().stream().map(cv -> new ErrorMessageDTO(cv.getMessage(), cv.getPropertyPath().toString())).toList();

        return ResponseEntity.badRequest().body(errors);
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

    @ExceptionHandler(InvalidCourseStatusException.class)
    public ResponseEntity<ErrorMessageDTO> handleInvalidCourseStatusException(InvalidCourseStatusException e) {
        ErrorMessageDTO error = new ErrorMessageDTO(e.getMessage(), "status");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessageDTO> handleEntityNotFoundException(EntityNotFoundException e) {
        ErrorMessageDTO error = new ErrorMessageDTO(e.getMessage(), "resource");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
