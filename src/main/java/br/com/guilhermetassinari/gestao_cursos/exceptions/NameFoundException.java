package br.com.guilhermetassinari.gestao_cursos.exceptions;

public class NameFoundException extends RuntimeException {
    public NameFoundException() {
        super("Course with this name already exists");
    }
}