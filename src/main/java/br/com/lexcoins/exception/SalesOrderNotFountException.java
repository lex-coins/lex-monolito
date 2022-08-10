package br.com.lexcoins.exception;

public class SalesOrderNotFountException extends RuntimeException{

    public SalesOrderNotFountException(String msg){
        super(msg);
    }
}
