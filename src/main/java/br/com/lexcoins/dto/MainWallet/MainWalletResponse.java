package br.com.lexcoins.dto.MainWallet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MainWalletResponse {

    private BigDecimal amount;
}
