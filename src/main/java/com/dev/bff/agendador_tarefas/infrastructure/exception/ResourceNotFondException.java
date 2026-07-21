package com.dev.bff.agendador_tarefas.infrastructure.exception;

public class ResourceNotFondException extends RuntimeException {
    public ResourceNotFondException(String mensagem) {
        super(mensagem);
    }
    public ResourceNotFondException (String mensagem, Throwable throwable){
        super(mensagem, throwable);
    }
}
