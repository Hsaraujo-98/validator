package br.com.developer.validator.controller;

import br.com.developer.validator.dto.RequestDTO;
import br.com.developer.validator.dto.ResponseDTO;
import br.com.developer.validator.service.ValidatorService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@Log4j2
public class Controller {

    @Autowired
    private ValidatorService service;

    @PostMapping("/validate")
    public ResponseEntity<ResponseDTO> passwordValidate(@RequestBody RequestDTO request) {
        log.info("Request: {}", request);
        var passwordValid = service.validate(request.password());
        var message = passwordValid ? "Valid password" : "Invalid password";
        var response = new ResponseDTO(passwordValid, message);
        log.info("Response: {}", response);
        return ResponseEntity.ok(response);
    }
}