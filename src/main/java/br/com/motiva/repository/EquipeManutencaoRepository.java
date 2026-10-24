package br.com.motiva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.motiva.model.EquipeManutencao;
import br.com.motiva.model.TipoEquipe;

public interface EquipeManutencaoRepository extends JpaRepository<EquipeManutencao, Long> {

    List<EquipeManutencao> findByTipoEquipe(TipoEquipe tipoEquipe);
}
