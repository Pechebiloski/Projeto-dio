package com.peche.maintenance_api.repository;

import com.peche.maintenance_api.model.Manutencao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ManutencaoRepository extends MongoRepository<Manutencao, String> {
}
