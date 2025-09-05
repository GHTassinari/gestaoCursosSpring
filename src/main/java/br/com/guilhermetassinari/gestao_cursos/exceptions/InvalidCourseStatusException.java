package br.com.guilhermetassinari.gestao_cursos.exceptions;

public class InvalidCourseStatusException extends RuntimeException {
    public InvalidCourseStatusException(String message) {
        super(message);
    }
}