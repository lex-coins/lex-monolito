package br.com.lexcoins.mappers;

import br.com.lexcoins.dto.person.PersonRequestDTO;
import br.com.lexcoins.dto.wallet.WalletRequestDTO;
import br.com.lexcoins.dto.wallet.WalletResponseDTO;
import br.com.lexcoins.model.Person;
import br.com.lexcoins.model.Wallet;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WalletMapper {

    final ModelMapper modelMapper;

    public Wallet walletRequestDtoToWalletMapper(WalletRequestDTO walletRequestDTO){
        return modelMapper.map(walletRequestDTO, Wallet.class);
    }

    public List<WalletResponseDTO> walletListToWalletResponseDTOListMapper(List<Wallet> wallets){
        return wallets.stream()
                .map(wallet -> modelMapper.map(wallet, WalletResponseDTO.class))
                .collect(Collectors.toList());
    }

    public WalletResponseDTO walletToWalletResponseMapper(Wallet wallet){
        return modelMapper.map(wallet, WalletResponseDTO.class);
    }
}
