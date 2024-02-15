package br.com.developer.validator.controller;

import br.com.developer.validator.dto.RequestDTO;
import br.com.developer.validator.dto.ResponseDTO;
import br.com.developer.validator.service.ValidatorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<RequestDTO> jacksonRequest;

    @Autowired
    private JacksonTester<ResponseDTO> jacksonResponse;

    @Mock
    private ValidatorService service;

    @Test
    public void validPasswordTest() throws Exception {
        var password = "K@roline10";
        when(service.validate(any())).thenReturn(true);
        var response = mockMvc
                .perform(post("/v1/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jacksonRequest.write(new RequestDTO(password)).getJson())
                )
                .andReturn().getResponse();
        var responseExpected = new ResponseDTO(true, "Valid password");
        var responseJson = jacksonResponse.write(responseExpected).getJson();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(responseJson);
    }

    @Test
    public void invalidPasswordTest() throws Exception {
        var password = "Karoline10";
        when(service.validate(any())).thenReturn(false);
        var response = mockMvc
                .perform(post("/v1/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jacksonRequest.write(new RequestDTO(password)).getJson())
                )
                .andReturn().getResponse();
        var responseExpected = new ResponseDTO(false, "Invalid password");
        var responseJson = jacksonResponse.write(responseExpected).getJson();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(responseJson);
    }
}