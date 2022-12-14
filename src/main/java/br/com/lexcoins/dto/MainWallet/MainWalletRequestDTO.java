package br.com.lexcoins.dto.MainWallet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class MainWalletRequestDTO {

    private BigDecimal amount;
}
