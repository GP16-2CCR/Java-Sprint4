package br.com.motiva.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.motiva.model.IntervencaoOperacional;
import br.com.motiva.model.TipoIntervencao;

public interface IntervencaoOperacionalRepository extends JpaRepository<IntervencaoOperacional, Long> {

    List<IntervencaoOperacional> findByTipoIntervencao(TipoIntervencao tipoIntervencao);

    Optional<IntervencaoOperacional> findFirstByTipoIntervencaoOrderByIdAsc(TipoIntervencao tipoIntervencao);
}
