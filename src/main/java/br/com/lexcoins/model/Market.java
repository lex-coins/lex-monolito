package br.com.lexcoins.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "TB_BROKER")

public class Market {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany
    private List<Book>books;
    private BigDecimal cryptoExchangeRate;
    @ManyToOne
    @JoinColumn(name = "main_wallet_id")
    private MainWallet mainWallet;
}
