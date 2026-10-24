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

import br.com.motiva.model.EquipeManutencao;
import br.com.motiva.model.TipoEquipe;
import br.com.motiva.service.EquipeService;

@RestController
@RequestMapping("/api/equipes")
public class EquipeController {

    private final EquipeService service;

    public EquipeController(EquipeService service) {
        this.service = service;
    }

    @GetMapping
    public List<EquipeManutencao> listar(@RequestParam(required = false) TipoEquipe tipo) {
        if (tipo == null) {
            return service.listar();
        }
        return service.listarPorTipo(tipo);
    }

    @GetMapping("/{id}")
    public EquipeManutencao buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    public ResponseEntity<EquipeManutencao> criar(@RequestBody EquipeManutencao equipe) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(equipe));
    }

    @PutMapping("/{id}")
    public EquipeManutencao atualizar(@PathVariable Long id, @RequestBody EquipeManutencao equipe) {
        return service.atualizar(id, equipe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
