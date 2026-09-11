package com.peche.maintenance_api.service.strategy;

import com.peche.maintenance_api.model.Manutencao;
import org.springframework.stereotype.Component;

@Component
public class CalculoValorBaixaStrategy implements CalculoValorStrategy {

    @Override
    public boolean suporta(String prioridade) {
        return "BAIXA".equalsIgnoreCase(prioridade);
    }

    @Override
    public double calcular(Manutencao manutencao) {
        return 50.0;
    }
}
