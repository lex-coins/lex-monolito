package br.com.lexcoins.mappers;

import br.com.lexcoins.dto.person.PersonRequestDTO;
import br.com.lexcoins.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserMapper {

    final ModelMapper modelMapper;
    final PasswordEncoder passwordEncoder;

    public UserModel personRequestDtoToUserMapper(PersonRequestDTO personRequestDTO){
        return new UserModel(null, personRequestDTO.getUsername(), passwordEncoder.encode(personRequestDTO.getPassword()), null);
    }
}
