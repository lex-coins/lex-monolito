package br.com.lexcoins.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity(name = "TB_CRYPTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Crypto {

    @Id
    private Long id;
    private String name;
    private String abbreviation;
    private BigDecimal price;
    private String currency;

    
}
