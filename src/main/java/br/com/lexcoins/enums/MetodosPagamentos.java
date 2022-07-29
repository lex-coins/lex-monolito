package br.com.lexcoins.enums;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public enum MetodosPagamentos {

    PIX,
    TED,
    CARTAOCREDITO,
    CARTAODEBITO;

    MetodosPagamentos() {
    }
}
