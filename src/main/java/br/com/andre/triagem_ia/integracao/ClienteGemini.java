package br.com.andre.triagem_ia.integracao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.Duration;
import java.util.List;

@Component
public class ClienteGemini {

    private final RestClient restClient;
    private final String modelo;

    public ClienteGemini(@Value("${gemini.url}") String url,
                         @Value("${gemini.api-key}") String chave,
                         @Value("${gemini.modelo}") String modelo) {
        SimpleClientHttpRequestFactory fabrica = new SimpleClientHttpRequestFactory();
        fabrica.setConnectTimeout(Duration.ofSeconds(3));
        fabrica.setReadTimeout(Duration.ofSeconds(10));

        this.restClient = RestClient.builder()
                .requestFactory(fabrica)
                .baseUrl(url)
                .defaultHeader("x-goog-api-key", chave)
                .build();
        this.modelo = modelo;
    }

    public String gerarTexto(String instrucao, String texto) {
        Requisicao corpo = new Requisicao(conteudo(instrucao), List.of(conteudo(texto)));

        Resposta resposta = restClient.post()
                .uri("/models/{modelo}:generateContent", modelo)
                .contentType(MediaType.APPLICATION_JSON)
                .body(corpo)
                .retrieve()
                .body(Resposta.class);

        return resposta.candidates().get(0).content().parts().get(0).text();
    }

    private Conteudo conteudo(String texto) {
        return new Conteudo(List.of(new Parte(texto)));
    }

    record Requisicao(Conteudo systemInstruction, List<Conteudo> contents) {}
    record Conteudo(List<Parte> parts) {}
    record Parte(String text) {}
    record Resposta(List<Candidato> candidates) {}
    record Candidato(Conteudo content) {}
}