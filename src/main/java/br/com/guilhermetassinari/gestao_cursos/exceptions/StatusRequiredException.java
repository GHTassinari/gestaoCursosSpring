package br.com.guilhermetassinari.gestao_cursos.exceptions;

public class StatusRequiredException extends RuntimeException {
    public StatusRequiredException(String message) {
        super(message);
    }
}