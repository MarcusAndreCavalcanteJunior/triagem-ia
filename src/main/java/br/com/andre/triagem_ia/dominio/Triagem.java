package br.com.andre.triagem_ia.dominio;

import java.time.LocalDateTime;

public class Triagem {

    private final Long id;
    private final String texto;
    private final CategoriaTriagem categoria;
    private final PrioridadeTriagem prioridade;
    private final LocalDateTime criadoEm;

    public Triagem (Long id, String texto, CategoriaTriagem categoria,
                    PrioridadeTriagem prioridade, LocalDateTime criadoEm) {
        this.id = id;
        this.texto = texto;
        this.categoria = categoria;
        this.prioridade = prioridade;
        this.criadoEm = criadoEm;
    }

    public Long getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public CategoriaTriagem getCategoria() {
        return categoria;
    }

    public PrioridadeTriagem getPrioridade() {
        return prioridade;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }
}
