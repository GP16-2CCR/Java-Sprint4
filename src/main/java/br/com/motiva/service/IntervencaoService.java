package br.com.motiva.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.motiva.model.IntervencaoOperacional;
import br.com.motiva.model.TipoIntervencao;
import br.com.motiva.repository.IntervencaoOperacionalRepository;

@Service
public class IntervencaoService {

    private final IntervencaoOperacionalRepository repository;

    public IntervencaoService(IntervencaoOperacionalRepository repository) {
        this.repository = repository;
    }

    public List<IntervencaoOperacional> listar() {
        return repository.findAll();
    }

    public List<IntervencaoOperacional> listarPorTipo(TipoIntervencao tipoIntervencao) {
        return repository.findByTipoIntervencao(tipoIntervencao);
    }

    public IntervencaoOperacional buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Intervenção não encontrada."));
    }

    public IntervencaoOperacional criar(IntervencaoOperacional intervencao) {
        validar(intervencao);
        intervencao.setId(null);
        return repository.save(intervencao);
    }

    public IntervencaoOperacional atualizar(Long id, IntervencaoOperacional dados) {
        IntervencaoOperacional intervencao = buscar(id);
        validar(dados);
        intervencao.setTipoIntervencao(dados.getTipoIntervencao());
        intervencao.setNomeServico(dados.getNomeServico().trim());
        return repository.save(intervencao);
    }

    public void remover(Long id) {
        if (!repository.existsById(id)) {
            throw new NaoEncontradoException("Intervenção não encontrada.");
        }
        repository.deleteById(id);
    }

    private void validar(IntervencaoOperacional intervencao) {
        if (intervencao.getTipoIntervencao() == null) {
            throw new IllegalArgumentException("O tipo da intervenção é obrigatório.");
        }
        if (intervencao.getNomeServico() == null || intervencao.getNomeServico().isBlank()) {
            throw new IllegalArgumentException("O nome do serviço é obrigatório.");
        }
        if (intervencao.getNomeServico().trim().length() > 100) {
            throw new IllegalArgumentException("O nome do serviço deve ter no máximo 100 caracteres.");
        }
        intervencao.setNomeServico(intervencao.getNomeServico().trim());
    }
}
