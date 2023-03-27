package br.com.arbify.arbifyweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.arbify.arbifyweb.dto.UserDTO;
import br.com.arbify.arbifyweb.model.User;
import br.com.arbify.arbifyweb.repository.UserRepository;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    public void salvarUsuario(UserDTO userDTO) {
        User user = userMapping(userDTO);
        
        userRepository.save(user);
    }

    public User userMapping(UserDTO userDTO) {
        User user = new User();

        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        return user;
    }

}
