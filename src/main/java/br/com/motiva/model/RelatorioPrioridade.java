package br.com.motiva.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "RELATORIO_PRIORIDADE")
public class RelatorioPrioridade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqRelatorio")
    @SequenceGenerator(name = "seqRelatorio", sequenceName = "SEQ_RELATORIO_PRIORIDADE", allocationSize = 1)
    @Column(name = "ID_RELATORIO")
    private Long id;

    @Column(name = "DATA_GERACAO", nullable = false)
    private LocalDateTime dataGeracao;

    @OneToMany(mappedBy = "relatorio", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy("prioridade DESC")
    private List<ItemRelatorio> itens = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(LocalDateTime dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public List<ItemRelatorio> getItens() {
        return itens;
    }

    public void setItens(List<ItemRelatorio> itens) {
        this.itens = itens;
    }
}
