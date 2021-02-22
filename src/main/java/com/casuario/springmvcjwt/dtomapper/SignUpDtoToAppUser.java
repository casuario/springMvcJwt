package com.casuario.springmvcjwt.dtomapper;
import com.casuario.springmvcjwt.dto.SignUpDto;
import com.casuario.springmvcjwt.entity.AppUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpDtoToAppUser {

    BCryptPasswordEncoder bCryptPasswordEncoder;

    public SignUpDtoToAppUser(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public AppUser mapper(SignUpDto dto) {
        String encodedPassword = null;
        if(dto.password()!=null)
            encodedPassword = bCryptPasswordEncoder.encode(dto.password());
        return new AppUser(dto.username(), encodedPassword, dto.email());
    }

}
