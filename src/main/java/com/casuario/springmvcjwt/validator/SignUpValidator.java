package com.casuario.springmvcjwt.validator;

import com.casuario.springmvcjwt.dto.SignUpDto;
import com.casuario.springmvcjwt.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SignUpValidator {

    AppUserRepository userRepository;

    @Autowired
    public SignUpValidator(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Map<String, Object> validator(SignUpDto dto) {
        var errors = new HashMap<String, Object>();

        if (userRepository.findByUsername(dto.username()) != null) {
            errors.put("UsernameExistsError", "Ya existe un usuario con ese username");
        }
        if (userRepository.findByEmail(dto.email()) != null) {
            errors.put("EmailExistsError", "Ya existe un usuario con ese email");
        }
        if (dto.username() == null || dto.username().length() == 0) {
            errors.put("EmptyUsernameError", "Es necesario rellenar el nombre de usuario");
        }
        if (dto.password() == null || dto.password().length() == 0) {
            errors.put("EmptyPasswordError", "Es necesario rellenar el password");
        }
        if (dto.password() == null || dto.passwordRepeated() == null || !dto.password().equals(dto.passwordRepeated())) {
            errors.put("DifferentPasswordsError", "Las contraseñas no coinciden");
        }
        String mailRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if (dto.email() == null || !dto.email().matches(mailRegex)) {
            errors.put("EmailFormingError", "Debe proporcionar un email bien formado");
        }
        return errors;
    }

}
