package br.com.lexcoins.enums;

public enum SalesOrderStatus {

    WAITING("Waiting"),
    SOLD("Sold");

    private String salesStatusOrder;
    SalesOrderStatus(String salesStatusOrder) {
        this.salesStatusOrder = salesStatusOrder;
    }


}
