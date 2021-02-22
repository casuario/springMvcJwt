package com.casuario.springmvcjwt.service;

import com.casuario.springmvcjwt.dto.SignUpDto;
import com.casuario.springmvcjwt.dtomapper.SignUpDtoToAppUser;
import com.casuario.springmvcjwt.entity.AppUser;
import com.casuario.springmvcjwt.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final SignUpDtoToAppUser signUpMapper;
    private final AppUserRepository userRepository;

    @Autowired
    public RegistrationServiceImpl(SignUpDtoToAppUser signUpMapper, AppUserRepository userRepository) {
        this.signUpMapper = signUpMapper;
        this.userRepository = userRepository;
    }

    @Override
    public Map<String, Object> signUp(SignUpDto dto, Map<String, Object> errors) {
        AppUser user = signUpMapper.mapper(dto);
        try {
            userRepository.save(user);
        } catch (DataAccessException e) {
            errors.put("mensaje", "error al realizar el insert en la bbdd");
            errors.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
        }
        return errors;
    }
}
