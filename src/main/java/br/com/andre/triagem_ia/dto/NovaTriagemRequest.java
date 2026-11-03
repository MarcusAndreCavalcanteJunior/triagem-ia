package br.com.andre.triagem_ia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NovaTriagemRequest(
        @NotBlank(message = "texto e obrigatorio")
        @Size(max = 1000, message = "texto deve ter no maximo 1000 caracteres")
        String texto) {
}