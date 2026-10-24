package br.com.motiva.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.motiva.model.IntervencaoOperacional;
import br.com.motiva.model.TipoIntervencao;
import br.com.motiva.service.IntervencaoService;

@RestController
@RequestMapping("/api/intervencoes")
public class IntervencaoController {

    private final IntervencaoService service;

    public IntervencaoController(IntervencaoService service) {
        this.service = service;
    }

    @GetMapping
    public List<IntervencaoOperacional> listar(@RequestParam(required = false) TipoIntervencao tipo) {
        if (tipo == null) {
            return service.listar();
        }
        return service.listarPorTipo(tipo);
    }

    @GetMapping("/{id}")
    public IntervencaoOperacional buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    public ResponseEntity<IntervencaoOperacional> criar(@RequestBody IntervencaoOperacional intervencao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(intervencao));
    }

    @PutMapping("/{id}")
    public IntervencaoOperacional atualizar(@PathVariable Long id, @RequestBody IntervencaoOperacional intervencao) {
        return service.atualizar(id, intervencao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
