package br.com.motiva.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.motiva.model.IntervencaoOperacional;
import br.com.motiva.model.ItemRelatorio;
import br.com.motiva.model.RelatorioPrioridade;
import br.com.motiva.model.TipoIntervencao;
import br.com.motiva.model.TipoTerreno;
import br.com.motiva.model.TrechoRodovia;
import br.com.motiva.repository.IntervencaoOperacionalRepository;
import br.com.motiva.repository.RelatorioPrioridadeRepository;
import br.com.motiva.repository.TrechoRodoviaRepository;

@Service
public class RelatorioService {

    private static final double LIMITE_ROCADA_CM = 40.0;
    private static final double LIMITE_PULVERIZACAO_CM = 25.0;
    private static final double PESO_ALTURA = 0.5;
    private static final double PESO_DIAS = 0.3;
    private static final double PESO_CRESCIMENTO = 0.2;

    private final RelatorioPrioridadeRepository relatorioRepository;
    private final TrechoRodoviaRepository trechoRepository;
    private final IntervencaoOperacionalRepository intervencaoRepository;

    public RelatorioService(
            RelatorioPrioridadeRepository relatorioRepository,
            TrechoRodoviaRepository trechoRepository,
            IntervencaoOperacionalRepository intervencaoRepository
    ) {
        this.relatorioRepository = relatorioRepository;
        this.trechoRepository = trechoRepository;
        this.intervencaoRepository = intervencaoRepository;
    }

    public List<RelatorioPrioridade> listar() {
        return relatorioRepository.findAllByOrderByDataGeracaoDesc();
    }

    public List<RelatorioPrioridade> listarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        if (inicio.isAfter(fim)) {
            throw new IllegalArgumentException("A data inicial não pode ser maior que a data final.");
        }
        return relatorioRepository.findByDataGeracaoBetweenOrderByDataGeracaoDesc(inicio, fim);
    }

    @Transactional
    public RelatorioPrioridade gerar() {
        RelatorioPrioridade relatorio = new RelatorioPrioridade();
        relatorio.setDataGeracao(LocalDateTime.now());

        for (TrechoRodovia trecho : trechoRepository.findAll()) {
            TipoIntervencao tipo = classificar(trecho);
            if (tipo == null) {
                continue;
            }
            IntervencaoOperacional intervencao = intervencaoRepository
                    .findFirstByTipoIntervencaoOrderByIdAsc(tipo)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Não há intervenção cadastrada do tipo " + tipo + "."
                    ));

            ItemRelatorio item = new ItemRelatorio();
            item.setRelatorio(relatorio);
            item.setTrecho(trecho);
            item.setIntervencao(intervencao);
            item.setPrioridade(calcularPrioridade(trecho));
            relatorio.getItens().add(item);
        }

        relatorio.getItens().sort(Comparator.comparingInt(ItemRelatorio::getPrioridade).reversed());
        return relatorioRepository.save(relatorio);
    }

    private TipoIntervencao classificar(TrechoRodovia trecho) {
        double altura = trecho.getAlturaVegetacaoCm();
        if (altura >= LIMITE_ROCADA_CM && !trecho.isPossuiInvasoras()) {
            return TipoIntervencao.ROCADA_MECANIZADA;
        }
        if (trecho.isPossuiInvasoras() || (altura >= LIMITE_PULVERIZACAO_CM && altura < LIMITE_ROCADA_CM)) {
            return TipoIntervencao.PULVERIZACAO;
        }
        return null;
    }

    private int calcularPrioridade(TrechoRodovia trecho) {
        double taxa = trecho.getTipoTerreno() == TipoTerreno.UMIDO ? 2.5 : 0.8;
        double scoreAltura = Math.min(trecho.getAlturaVegetacaoCm() / 60.0, 1.0) * 100;
        double scoreDias = Math.min(trecho.getDiasSemManutencao() / 30.0, 1.0) * 100;
        double scoreCrescimento = Math.min(taxa / 3.0, 1.0) * 100;

        int prioridade = (int) Math.round(
                scoreAltura * PESO_ALTURA + scoreDias * PESO_DIAS + scoreCrescimento * PESO_CRESCIMENTO
        );
        if (trecho.isPossuiInvasoras()) {
            prioridade = Math.min(prioridade + 15, 100);
        }
        return prioridade;
    }
}
