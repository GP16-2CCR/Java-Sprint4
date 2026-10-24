package br.com.motiva.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.motiva.model.EquipeManutencao;
import br.com.motiva.model.TipoEquipe;
import br.com.motiva.repository.EquipeManutencaoRepository;

@Service
public class EquipeService {

    private final EquipeManutencaoRepository repository;

    public EquipeService(EquipeManutencaoRepository repository) {
        this.repository = repository;
    }

    public List<EquipeManutencao> listar() {
        return repository.findAll();
    }

    public List<EquipeManutencao> listarPorTipo(TipoEquipe tipoEquipe) {
        return repository.findByTipoEquipe(tipoEquipe);
    }

    public EquipeManutencao buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Equipe não encontrada."));
    }

    public EquipeManutencao criar(EquipeManutencao equipe) {
        validar(equipe);
        equipe.setId(null);
        return repository.save(equipe);
    }

    public EquipeManutencao atualizar(Long id, EquipeManutencao dados) {
        EquipeManutencao equipe = buscar(id);
        validar(dados);
        equipe.setNome(dados.getNome().trim());
        equipe.setTipoEquipe(dados.getTipoEquipe());
        equipe.setDisponivel(dados.isDisponivel());
        return repository.save(equipe);
    }

    public void remover(Long id) {
        if (!repository.existsById(id)) {
            throw new NaoEncontradoException("Equipe não encontrada.");
        }
        repository.deleteById(id);
    }

    private void validar(EquipeManutencao equipe) {
        if (equipe.getNome() == null || equipe.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome da equipe é obrigatório.");
        }
        if (equipe.getNome().trim().length() > 100) {
            throw new IllegalArgumentException("O nome da equipe deve ter no máximo 100 caracteres.");
        }
        if (equipe.getTipoEquipe() == null) {
            throw new IllegalArgumentException("O tipo da equipe é obrigatório.");
        }
        equipe.setNome(equipe.getNome().trim());
    }
}
