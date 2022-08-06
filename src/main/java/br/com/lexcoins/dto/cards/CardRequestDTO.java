package br.com.lexcoins.dto.cards;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.CreditCardNumber;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CardRequestDTO {
    @NotEmpty
    private String network;
    @NotEmpty
    @Size(max = 16)
    private String number;
    @NotEmpty
    private String name;
    @NotEmpty
    @Size(max = 3)
    private String cvv;
    @NotNull
    @JsonFormat(pattern = "MM/yyyy")
    private String expiration;
}