package br.com.andre.triagem_ia.dto;

import java.util.List;

public record ErroResponse(String mensagem, List<String> detalhes) {
}
