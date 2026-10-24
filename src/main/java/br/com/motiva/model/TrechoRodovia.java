package br.com.motiva.model;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "TRECHO_RODOVIA")
public class TrechoRodovia {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqTrecho")
    @SequenceGenerator(name = "seqTrecho", sequenceName = "SEQ_TRECHO_RODOVIA", allocationSize = 1)
    @Column(name = "ID_TRECHO")
    private Long id;

    @Column(name = "KM", nullable = false)
    private double km;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_TERRENO", nullable = false, length = 30)
    private TipoTerreno tipoTerreno;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_CRESCIMENTO", nullable = false, length = 30)
    private TipoTerreno tipoCrescimento;

    @Column(name = "ALTURA_VEGETACAO_CM", nullable = false)
    private double alturaVegetacaoCm;

    @Convert(converter = BooleanoNumericoConverter.class)
    @Column(name = "EQUIPADO_IOT", nullable = false)
    private boolean equipadoIot;

    @Convert(converter = BooleanoNumericoConverter.class)
    @Column(name = "POSSUI_INVASORAS", nullable = false)
    private boolean possuiInvasoras;

    @Column(name = "DIAS_SEM_MANUTENCAO", nullable = false)
    private int diasSemManutencao;

    @Column(name = "NIVEL_PRIORIDADE", nullable = false)
    private int nivelPrioridade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public TipoTerreno getTipoTerreno() {
        return tipoTerreno;
    }

    public void setTipoTerreno(TipoTerreno tipoTerreno) {
        this.tipoTerreno = tipoTerreno;
    }

    public TipoTerreno getTipoCrescimento() {
        return tipoCrescimento;
    }

    public void setTipoCrescimento(TipoTerreno tipoCrescimento) {
        this.tipoCrescimento = tipoCrescimento;
    }

    public double getAlturaVegetacaoCm() {
        return alturaVegetacaoCm;
    }

    public void setAlturaVegetacaoCm(double alturaVegetacaoCm) {
        this.alturaVegetacaoCm = alturaVegetacaoCm;
    }

    public boolean isEquipadoIot() {
        return equipadoIot;
    }

    public void setEquipadoIot(boolean equipadoIot) {
        this.equipadoIot = equipadoIot;
    }

    public boolean isPossuiInvasoras() {
        return possuiInvasoras;
    }

    public void setPossuiInvasoras(boolean possuiInvasoras) {
        this.possuiInvasoras = possuiInvasoras;
    }

    public int getDiasSemManutencao() {
        return diasSemManutencao;
    }

    public void setDiasSemManutencao(int diasSemManutencao) {
        this.diasSemManutencao = diasSemManutencao;
    }

    public int getNivelPrioridade() {
        return nivelPrioridade;
    }

    public void setNivelPrioridade(int nivelPrioridade) {
        this.nivelPrioridade = nivelPrioridade;
    }
}
