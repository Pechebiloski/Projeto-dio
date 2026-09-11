package com.peche.maintenance_api.controller;

import com.peche.maintenance_api.model.Manutencao;
import com.peche.maintenance_api.service.ManutencaoService;
import com.peche.maintenance_api.service.facade.ManutencaoFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manutencoes")
public class ManutencaoController {

    private final ManutencaoService service;
    private final ManutencaoFacade facade;

    public ManutencaoController(
            ManutencaoService service,
            ManutencaoFacade facade) {

        this.service = service;
        this.facade = facade;
    }

    @GetMapping
    public List<Manutencao> listarTodas() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manutencao> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Manutencao> criar(@RequestBody Manutencao manutencao) {
        return ResponseEntity.ok(facade.criar(manutencao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable String id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
public ResponseEntity<Manutencao> atualizar(
        @PathVariable String id,
        @RequestBody Manutencao manutencao) {

    Manutencao existente = service.buscarPorId(id);

    existente.setCliente(manutencao.getCliente());
    existente.setEquipamento(manutencao.getEquipamento());
    existente.setProblema(manutencao.getProblema());
    existente.setPrioridade(manutencao.getPrioridade());
    existente.setStatus(manutencao.getStatus());

    return ResponseEntity.ok(facade.criar(existente));
}
}