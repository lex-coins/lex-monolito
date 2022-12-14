package br.com.lexcoins.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "TB_HISTORIC")
public class Historic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String transactionType;
    private String toKeyPublic;
    private String fromKeyPublic;
    @ManyToOne
    private Wallet wallet;
    private Double amount;

}
