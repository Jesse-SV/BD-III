package com.example.danielmeuprojeto.domain.exception;

public class BadRequestException extends RuntimeException{
    
    public BadRequestException(String mensagem){
        super(mensagem);
    }
}
