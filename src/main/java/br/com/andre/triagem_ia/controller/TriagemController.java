package br.com.andre.triagem_ia.controller;

import br.com.andre.triagem_ia.dominio.Triagem;
import br.com.andre.triagem_ia.dto.NovaTriagemRequest;
import br.com.andre.triagem_ia.dto.TriagemResponse;
import br.com.andre.triagem_ia.service.TriagemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/triagens")
public class TriagemController {

    private final TriagemService service;

    public TriagemController(TriagemService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TriagemResponse> criar(@Valid @RequestBody NovaTriagemRequest request) {
        Triagem triagem = service.registrar(request.texto());
        return ResponseEntity.status(HttpStatus.CREATED).body(TriagemResponse.de(triagem));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TriagemResponse> buscar(@PathVariable Long id) {
        return service.buscar(id)
                .map(TriagemResponse::de)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}