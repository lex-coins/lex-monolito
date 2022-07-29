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
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    @JoinTable(name="wallet_crypto",
            joinColumns = @JoinColumn(name="wallet_id"),
            inverseJoinColumns = @JoinColumn(name="crypto_id")
    )
    private List<Crypto> cryptos;
    private BigDecimal balance;
    private String publicKey;
    private String privateKey;
}
