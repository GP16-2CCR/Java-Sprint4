package br.com.motiva.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "INTERVENCAO_OPERACIONAL")
public class IntervencaoOperacional {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqIntervencao")
    @SequenceGenerator(name = "seqIntervencao", sequenceName = "SEQ_INTERVENCAO_OPERACIONAL", allocationSize = 1)
    @Column(name = "ID_INTERVENCAO")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_INTERVENCAO", nullable = false, length = 30)
    private TipoIntervencao tipoIntervencao;

    @Column(name = "NOME_SERVICO", nullable = false, length = 100)
    private String nomeServico;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoIntervencao getTipoIntervencao() {
        return tipoIntervencao;
    }

    public void setTipoIntervencao(TipoIntervencao tipoIntervencao) {
        this.tipoIntervencao = tipoIntervencao;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }
}
