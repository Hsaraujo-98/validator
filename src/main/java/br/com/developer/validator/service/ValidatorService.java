package br.com.developer.validator.service;

import org.springframework.stereotype.Service;

@Service
public class ValidatorService {

    /*
    1. Deve iniciar com letra maiuscula
    2. Deve conter letras minusculas
    3. Deve conter caracteres especiais
    4. Deve conter minimo 2 numeros
    5. Deve conter minimo 10 caracteres
     */
    public boolean validate(String password) {
        var pattern = "^(?=[A-Z])(?=.*[a-z])(?=(?:.*\\d){2})(?=.*\\W).{10,}$";
        return password.matches(pattern);
    }
}