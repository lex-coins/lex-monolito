package br.com.lexcoins.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany
    private List<Wallet> wallet;
    @OneToOne
    private MainWallet mainWallet;
    //SERIA INTERESSANTE O USUÁRIO UM ATRIBUTO PARAS AS FORMAS DE PAGAMENTO? (enum paymentos)

}
