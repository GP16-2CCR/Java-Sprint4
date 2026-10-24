package br.com.motiva.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.motiva.model.TipoTerreno;
import br.com.motiva.model.TrechoRodovia;

public interface TrechoRodoviaRepository extends JpaRepository<TrechoRodovia, Long> {

    List<TrechoRodovia> findByTipoTerreno(TipoTerreno tipoTerreno);

    Optional<TrechoRodovia> findByKm(double km);
}
