package br.com.developer.validator.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class ValidatorServiceTest {

    @InjectMocks
    private ValidatorService service;

    @Test
    public void validateTest() {
        assertFalse(service.validate("Ariane@28")); // tamanho < 10
        assertFalse(service.validate("Elisangela49")); // não contém caractere especial
        assertFalse(service.validate("guilherme@45")); // não contém letra maiuscula
        assertFalse(service.validate("DAYANNE#11")); // não contém letra minuscula
        assertFalse(service.validate("guiNeto@45")); // não inicia com maiuscula
        assertFalse(service.validate("Elisangel@")); // não contém 2 números
        assertFalse(service.validate("Elisangel@4")); // não contém 2 números
        assertTrue(service.validate("Ari@dne027")); // OK
        assertTrue(service.validate("K@roline10")); // OK
        assertTrue(service.validate("Iz@10Silva")); // OK
    }
}