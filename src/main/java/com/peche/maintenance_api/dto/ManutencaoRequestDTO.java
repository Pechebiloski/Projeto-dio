package com.peche.maintenance_api.dto;

import jakarta.validation.constraints.NotBlank;

public class ManutencaoRequestDTO {

    @NotBlank(message = "Cliente é obrigatório")
    private String cliente;

    @NotBlank(message = "Equipamento é obrigatório")
    private String equipamento;

    @NotBlank(message = "Problema é obrigatório")
    private String problema;

    @NotBlank(message = "Prioridade é obrigatória")
    private String prioridade;

    @NotBlank(message = "Status é obrigatório")
    private String status;

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}