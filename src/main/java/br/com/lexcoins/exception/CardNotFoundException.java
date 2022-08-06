package br.com.lexcoins.exception;

public class CardNotFoundException extends RuntimeException{

    public CardNotFoundException(String msg){
        super(msg);
    }
}
