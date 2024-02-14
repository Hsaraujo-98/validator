package br.com.developer.validator.dto;

import com.google.gson.Gson;

public record RequestDTO(String password) {
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}