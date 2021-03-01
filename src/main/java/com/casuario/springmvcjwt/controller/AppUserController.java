package com.casuario.springmvcjwt.controller;

import com.casuario.springmvcjwt.dto.SignUpDto;
import com.casuario.springmvcjwt.service.RegistrationServiceImpl;
import com.casuario.springmvcjwt.validator.SignUpValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AppUserController {
    private final RegistrationServiceImpl authService;
    private final SignUpValidator validator;

    @Autowired
    public AppUserController(RegistrationServiceImpl authService, SignUpValidator validator) {
        this.authService = authService;
        this.validator = validator;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto dto) {
        var response = new HashMap<String, Object>();
        Map<String, Object> errors = validator.validator(dto);
        if (errors.size() > 0)
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        errors = authService.signUp(dto, errors);
        if (errors.size() > 0)
            new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
        response.put("mensaje", "usuario creado con exito");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
