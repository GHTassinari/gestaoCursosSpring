package br.com.guilhermetassinari.gestao_cursos.exceptions;

public class InvalidCourseSatusException extends RuntimeException {
    public InvalidCourseSatusException(String message) {
        super(message);
    }
}