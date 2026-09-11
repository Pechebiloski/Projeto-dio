package com.peche.maintenance_api.service.strategy;

import com.peche.maintenance_api.model.Manutencao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculoOrcamentoService {

    private final List<CalculoValorStrategy> strategies;

    public CalculoOrcamentoService(List<CalculoValorStrategy> strategies) {
        this.strategies = strategies;
    }

    public double calcular(Manutencao manutencao) {

        return strategies.stream()
                .filter(strategy -> strategy.suporta(manutencao.getPrioridade()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Prioridade não suportada: " + manutencao.getPrioridade()
                ))
                .calcular(manutencao);
    }
}
