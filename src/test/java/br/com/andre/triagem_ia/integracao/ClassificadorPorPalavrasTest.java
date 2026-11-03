package br.com.andre.triagem_ia.integracao;

import br.com.andre.triagem_ia.dominio.CategoriaTriagem;
import br.com.andre.triagem_ia.dominio.PrioridadeTriagem;
import br.com.andre.triagem_ia.dominio.ResultadoClassificacao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClassificadorPorPalavrasTest {

    private final ClassificadorPorPalavras classificador = new ClassificadorPorPalavras();

    @Test
    void classificaTextoComEstornoComoEstornoReembolsoDePrioridadeAlta() {
        ResultadoClassificacao resultado = classificador.classificar("quero meu estorno");

        assertEquals(CategoriaTriagem.ESTORNO_REEMBOLSO, resultado.categoria());
        assertEquals(PrioridadeTriagem.ALTA, resultado.prioridade());
    }

    @Test
    void classificaTextoSemPalavraConhecidaComoOutrosDePrioridadeBaixa() {
        ResultadoClassificacao resultado = classificador.classificar("bom dia");

        assertEquals(CategoriaTriagem.OUTROS, resultado.categoria());
        assertEquals(PrioridadeTriagem.BAIXA, resultado.prioridade());
    }
}
