package br.com.tech4me.tech4singer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.tech4me.tech4singer.model.Cantor;

public interface CantorRepository  extends MongoRepository<Cantor, String> {
    
}
