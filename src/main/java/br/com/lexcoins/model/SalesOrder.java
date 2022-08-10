package br.com.lexcoins.model;

import br.com.lexcoins.enums.SalesOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_SALES_ORDER")
public class SalesOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Person seller;
    private SalesOrderStatus status;
    @ManyToOne
    @JoinColumn(name = "crypto_id")
    private Crypto crypto;
    private BigDecimal price;
    private double amount;

}
