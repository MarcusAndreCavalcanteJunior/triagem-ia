package br.com.andre.triagem_ia.integracao;

import br.com.andre.triagem_ia.dominio.CategoriaTriagem;
import br.com.andre.triagem_ia.dominio.PrioridadeTriagem;
import br.com.andre.triagem_ia.dominio.ResultadoClassificacao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class ClassificadorGeminiTest {

    private final ClienteGemini cliente = mock(ClienteGemini.class);
    private final ClassificadorPorPalavras planoB = mock(ClassificadorPorPalavras.class);
    private final ClassificadorGemini classificador = new ClassificadorGemini(cliente, planoB);

    @Test
    void usaRespostaDoGeminiQuandoValida() {
        when(cliente.gerarTexto(anyString(), anyString())).thenReturn("ATRASO;ALTA");

        ResultadoClassificacao resultado = classificador.classificar("pedido atrasou");

        assertEquals(new ResultadoClassificacao(CategoriaTriagem.ATRASO, PrioridadeTriagem.ALTA), resultado);
        verifyNoInteractions(planoB);
    }

    @Test
    void usaPlanoBQuandoGeminiFalha() {
        ResultadoClassificacao doPlanoB = new ResultadoClassificacao(CategoriaTriagem.OUTROS, PrioridadeTriagem.BAIXA);
        when(cliente.gerarTexto(anyString(), anyString())).thenThrow(new RuntimeException("sem conexao"));
        when(planoB.classificar("pedido atrasou")).thenReturn(doPlanoB);

        ResultadoClassificacao resultado = classificador.classificar("pedido atrasou");

        assertSame(doPlanoB, resultado);
    }

    @Test
    void usaPlanoBQuandoRespostaDoGeminiForInvalida() {
        ResultadoClassificacao doPlanoB = new ResultadoClassificacao(CategoriaTriagem.OUTROS, PrioridadeTriagem.BAIXA);
        when(cliente.gerarTexto(anyString(), anyString())).thenReturn("texto fora do formato");
        when(planoB.classificar("pedido atrasou")).thenReturn(doPlanoB);

        ResultadoClassificacao resultado = classificador.classificar("pedido atrasou");

        assertSame(doPlanoB, resultado);
    }
}
