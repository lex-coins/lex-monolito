package br.com.lexcoins.enums;


public enum MethodPayments {

    PIX("Pix"),
    TED("Ted"),
    CARTAOCREDITO("Cartão de crédito"),
    CARTAODEBITO("Cartão de débito");

    private String methodPayments;
    MethodPayments(String methodPayments) {
        this.methodPayments = methodPayments;
    }
}
