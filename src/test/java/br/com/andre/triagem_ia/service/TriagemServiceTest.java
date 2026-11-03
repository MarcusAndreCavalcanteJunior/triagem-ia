package br.com.andre.triagem_ia.service;

import br.com.andre.triagem_ia.dominio.CategoriaTriagem;
import br.com.andre.triagem_ia.dominio.PrioridadeTriagem;
import br.com.andre.triagem_ia.dominio.ResultadoClassificacao;
import br.com.andre.triagem_ia.dominio.Triagem;
import br.com.andre.triagem_ia.integracao.ClassificadorTriagem;
import br.com.andre.triagem_ia.repository.TriagemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TriagemServiceTest {

    @Mock
    private ClassificadorTriagem classificador;

    @Mock
    private TriagemRepository repository;

    @InjectMocks
    private TriagemService service;

    @Test
    void registraTriagemComCategoriaEPrioridadeDoClassificador() {
        String texto = "meu pedido atrasou";
        ResultadoClassificacao resultado =
                new ResultadoClassificacao(CategoriaTriagem.ATRASO, PrioridadeTriagem.MEDIA);
        Triagem salva = new Triagem(1L, texto, CategoriaTriagem.ATRASO,
                PrioridadeTriagem.MEDIA, LocalDateTime.now());

        when(classificador.classificar(texto)).thenReturn(resultado);
        when(repository.salvar(texto, CategoriaTriagem.ATRASO, PrioridadeTriagem.MEDIA)).thenReturn(salva);

        Triagem retornada = service.registrar(texto);

        assertEquals(CategoriaTriagem.ATRASO, retornada.getCategoria());
        verify(repository).salvar(texto, CategoriaTriagem.ATRASO, PrioridadeTriagem.MEDIA);
    }
}
