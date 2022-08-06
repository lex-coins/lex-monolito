package br.com.lexcoins.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_WALLET")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Crypto crypto;
    private Double amount;
    private String publicKey;
    private String privateKey;
    @OneToMany
    private List<Historic> historics;

    @ManyToOne
    @JoinColumn(name="person_id")
    private Person person;
}
