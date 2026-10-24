package br.com.motiva.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.motiva.model.RelatorioPrioridade;
import br.com.motiva.service.RelatorioService;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    private final RelatorioService service;

    public RelatorioController(RelatorioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RelatorioPrioridade> gerar() {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.gerar());
    }

    @GetMapping
    public List<RelatorioPrioridade> listar() {
        return service.listar();
    }

    @GetMapping("/periodo")
    public List<RelatorioPrioridade> listarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim
    ) {
        return service.listarPorPeriodo(inicio, fim);
    }
}
