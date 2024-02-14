package br.com.developer.validator.dto;

import com.google.gson.Gson;

public record ResponseDTO(boolean valid, String message) {
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}