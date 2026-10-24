package br.com.motiva.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.motiva.model.TipoTerreno;
import br.com.motiva.model.TrechoRodovia;
import br.com.motiva.repository.TrechoRodoviaRepository;

@Service
public class TrechoService {

    private final TrechoRodoviaRepository repository;

    public TrechoService(TrechoRodoviaRepository repository) {
        this.repository = repository;
    }

    public List<TrechoRodovia> listar() {
        return repository.findAll();
    }

    public List<TrechoRodovia> listarPorTipo(TipoTerreno tipoTerreno) {
        return repository.findByTipoTerreno(tipoTerreno);
    }

    public TrechoRodovia buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Trecho não encontrado."));
    }

    public TrechoRodovia criar(TrechoRodovia trecho) {
        validar(trecho);
        if (repository.findByKm(trecho.getKm()).isPresent()) {
            throw new IllegalArgumentException("Já existe trecho cadastrado no KM " + trecho.getKm());
        }
        trecho.setId(null);
        trecho.setTipoCrescimento(trecho.getTipoTerreno());
        return repository.save(trecho);
    }

    public TrechoRodovia atualizar(Long id, TrechoRodovia dados) {
        TrechoRodovia trecho = buscar(id);
        validar(dados);
        repository.findByKm(dados.getKm()).ifPresent(existente -> {
            if (!existente.getId().equals(id)) {
                throw new IllegalArgumentException("Já existe trecho cadastrado no KM " + dados.getKm());
            }
        });
        trecho.setKm(dados.getKm());
        trecho.setTipoTerreno(dados.getTipoTerreno());
        trecho.setTipoCrescimento(dados.getTipoTerreno());
        trecho.setAlturaVegetacaoCm(dados.getAlturaVegetacaoCm());
        trecho.setEquipadoIot(dados.isEquipadoIot());
        trecho.setPossuiInvasoras(dados.isPossuiInvasoras());
        trecho.setDiasSemManutencao(dados.getDiasSemManutencao());
        trecho.setNivelPrioridade(dados.getNivelPrioridade());
        return repository.save(trecho);
    }

    public void remover(Long id) {
        if (!repository.existsById(id)) {
            throw new NaoEncontradoException("Trecho não encontrado.");
        }
        repository.deleteById(id);
    }

    private void validar(TrechoRodovia trecho) {
        if (trecho.getTipoTerreno() == null) {
            throw new IllegalArgumentException("O tipo de terreno é obrigatório.");
        }
        if (trecho.getAlturaVegetacaoCm() < 0) {
            throw new IllegalArgumentException("O nível de vegetação não pode ser negativo.");
        }
        if (trecho.getDiasSemManutencao() < 0) {
            throw new IllegalArgumentException("Os dias sem manutenção não podem ser negativos.");
        }
    }
}
