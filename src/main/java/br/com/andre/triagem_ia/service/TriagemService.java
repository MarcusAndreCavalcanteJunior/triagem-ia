package br.com.andre.triagem_ia.service;

import br.com.andre.triagem_ia.dominio.ResultadoClassificacao;
import br.com.andre.triagem_ia.dominio.Triagem;
import br.com.andre.triagem_ia.integracao.ClassificadorTriagem;
import br.com.andre.triagem_ia.repository.TriagemRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TriagemService {

    private final ClassificadorTriagem classificador;
    private final TriagemRepository repository;

    public TriagemService(ClassificadorTriagem classificador, TriagemRepository repository) {
        this.classificador = classificador;
        this.repository = repository;
    }

    public Triagem registrar(String texto) {
        ResultadoClassificacao resultado = classificador.classificar(texto);
        return repository.salvar(texto, resultado.categoria(), resultado.prioridade());
    }

    public Optional<Triagem> buscar(Long id) {
        return repository.buscarPorId(id);
    }
}
