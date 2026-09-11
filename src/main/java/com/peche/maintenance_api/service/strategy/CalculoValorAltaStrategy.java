package com.peche.maintenance_api.service.strategy;

import com.peche.maintenance_api.model.Manutencao;
import org.springframework.stereotype.Component;

@Component
public class CalculoValorAltaStrategy implements CalculoValorStrategy {

    @Override
    public boolean suporta(String prioridade) {
        return "ALTA".equalsIgnoreCase(prioridade);
    }

    @Override
    public double calcular(Manutencao manutencao) {
        return 120.0;
    }
}
