package com.peche.maintenance_api.service.facade;

import com.peche.maintenance_api.model.Manutencao;
import com.peche.maintenance_api.repository.ManutencaoRepository;
import com.peche.maintenance_api.service.strategy.CalculoOrcamentoService;
import org.springframework.stereotype.Service;

@Service
public class ManutencaoFacade {

    private final ManutencaoRepository repository;
    private final CalculoOrcamentoService calculoOrcamentoService;

    public ManutencaoFacade(
            ManutencaoRepository repository,
            CalculoOrcamentoService calculoOrcamentoService) {

        this.repository = repository;
        this.calculoOrcamentoService = calculoOrcamentoService;
    }

    public Manutencao criar(Manutencao manutencao) {

        double valor = calculoOrcamentoService.calcular(manutencao);

        manutencao.setValor(valor);

        return repository.save(manutencao);
    }
}