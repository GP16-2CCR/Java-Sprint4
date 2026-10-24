package br.com.motiva.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "ITEM_RELATORIO")
public class ItemRelatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqItem")
    @SequenceGenerator(name = "seqItem", sequenceName = "ISEQ$$_4206621", allocationSize = 1)
    @Column(name = "ID_ITEM")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_RELATORIO", nullable = false)
    private RelatorioPrioridade relatorio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_TRECHO", nullable = false)
    private TrechoRodovia trecho;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_INTERVENCAO", nullable = false)
    private IntervencaoOperacional intervencao;

    @Column(name = "PRIORIDADE", nullable = false)
    private int prioridade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RelatorioPrioridade getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(RelatorioPrioridade relatorio) {
        this.relatorio = relatorio;
    }

    public TrechoRodovia getTrecho() {
        return trecho;
    }

    public void setTrecho(TrechoRodovia trecho) {
        this.trecho = trecho;
    }

    public IntervencaoOperacional getIntervencao() {
        return intervencao;
    }

    public void setIntervencao(IntervencaoOperacional intervencao) {
        this.intervencao = intervencao;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }
}
