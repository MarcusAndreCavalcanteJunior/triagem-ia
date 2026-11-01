package br.com.andre.triagem_ia.dto;

import br.com.andre.triagem_ia.dominio.CategoriaTriagem;
import br.com.andre.triagem_ia.dominio.PrioridadeTriagem;
import br.com.andre.triagem_ia.dominio.Triagem;

import java.time.LocalDateTime;

public record TriagemResponse(
        Long id,
        String texto,
        CategoriaTriagem categoria,
        PrioridadeTriagem prioridade,
        LocalDateTime criadoEm) {

    public static TriagemResponse de(Triagem triagem) {
        return new TriagemResponse(
                triagem.getId(),
                triagem.getTexto(),
                triagem.getCategoria(),
                triagem.getPrioridade(),
                triagem.getCriadoEm());
    }
}
