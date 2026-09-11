package com.peche.maintenance_api.service;

import com.peche.maintenance_api.model.Manutencao;
import com.peche.maintenance_api.repository.ManutencaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManutencaoService {

    private final ManutencaoRepository repository;

    public ManutencaoService(ManutencaoRepository repository) {
        this.repository = repository;
    }

    public List<Manutencao> listarTodas() {
        return repository.findAll();
    }

    public Manutencao buscarPorId(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manutenção não encontrada"));
    }

    public void excluir(String id) {
        repository.deleteById(id);
    }
}