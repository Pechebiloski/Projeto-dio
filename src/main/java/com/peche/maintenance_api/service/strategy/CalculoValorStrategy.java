package com.peche.maintenance_api.service.strategy;

import com.peche.maintenance_api.model.Manutencao;

public interface CalculoValorStrategy {

    boolean suporta(String prioridade);

    double calcular(Manutencao manutencao);
}
