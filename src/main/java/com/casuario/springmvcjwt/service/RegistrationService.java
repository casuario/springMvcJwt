package com.casuario.springmvcjwt.service;

import com.casuario.springmvcjwt.dto.SignUpDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface RegistrationService {
    Map<String, Object> signUp(SignUpDto dto, Map<String, Object> errors);
}
