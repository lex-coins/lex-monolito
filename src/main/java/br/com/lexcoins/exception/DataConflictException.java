package br.com.lexcoins.exception;

public class DataConflictException extends RuntimeException{

    public DataConflictException(String msg){
        super(msg);
    }
}
