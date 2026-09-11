package com.peche.maintenance_api.service.strategy;

import com.peche.maintenance_api.model.Manutencao;
import org.springframework.stereotype.Component;

@Component
public class CalculoValorMediaStrategy implements CalculoValorStrategy {

    @Override
    public boolean suporta(String prioridade) {
        return "MEDIA".equalsIgnoreCase(prioridade);
    }

    @Override
    public double calcular(Manutencao manutencao) {
        return 80.0;
    }
}