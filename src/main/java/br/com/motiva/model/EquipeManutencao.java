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
@Table(name = "EQUIPE_MANUTENCAO")
public class EquipeManutencao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqEquipe")
    @SequenceGenerator(name = "seqEquipe", sequenceName = "SEQ_EQUIPE_MANUTENCAO", allocationSize = 1)
    @Column(name = "ID_EQUIPE")
    private Long id;

    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_EQUIPE", nullable = false, length = 30)
    private TipoEquipe tipoEquipe;

    @Convert(converter = BooleanoNumericoConverter.class)
    @Column(name = "DISPONIVEL", nullable = false)
    private boolean disponivel = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoEquipe getTipoEquipe() {
        return tipoEquipe;
    }

    public void setTipoEquipe(TipoEquipe tipoEquipe) {
        this.tipoEquipe = tipoEquipe;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
