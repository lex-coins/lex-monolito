package br.com.lexcoins.exception;

public class PersonNotFoundException extends RuntimeException{

    public PersonNotFoundException(String msg){
        super(msg);
    }
}
