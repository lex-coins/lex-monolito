package br.com.lexcoins.service;

import br.com.lexcoins.repository.RoleRepository;
import br.com.lexcoins.repository.UserRepository;
import br.com.lexcoins.enums.RoleName;
import br.com.lexcoins.model.RoleModel;
import br.com.lexcoins.model.UserModel;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UserServiceImp implements UserDetailsService {

    final UserRepository userRepository;
    final RoleRepository roleRepository;


    public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username não foi encontrado" + username));
        return new User(userModel.getUsername(), userModel.getPassword(), true, true, true, true, userModel.getAuthorities());
    }

    public UserModel saveUser(UserModel userModel){
        if(userRepository.findByUsername(userModel.getUsername()).isPresent()){
            new RuntimeException("Usuario já existe");
        }
        List<RoleModel> roles = Arrays.asList(roleRepository.findByRoleName(RoleName.ROLE_USER));
        userModel.setRoles(roles);

        return userRepository.save(userModel);
    }

    public Optional<UserModel> findByUserName(String name){
        return userRepository.findByUsername(name);
    }


}
