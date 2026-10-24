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

import br.com.motiva.model.TipoTerreno;
import br.com.motiva.model.TrechoRodovia;
import br.com.motiva.service.TrechoService;

@RestController
@RequestMapping("/api/trechos")
public class TrechoController {

    private final TrechoService service;

    public TrechoController(TrechoService service) {
        this.service = service;
    }

    @GetMapping
    public List<TrechoRodovia> listar(@RequestParam(required = false) TipoTerreno tipo) {
        if (tipo == null) {
            return service.listar();
        }
        return service.listarPorTipo(tipo);
    }

    @GetMapping("/{id}")
    public TrechoRodovia buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    public ResponseEntity<TrechoRodovia> criar(@RequestBody TrechoRodovia trecho) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(trecho));
    }

    @PutMapping("/{id}")
    public TrechoRodovia atualizar(@PathVariable Long id, @RequestBody TrechoRodovia trecho) {
        return service.atualizar(id, trecho);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
