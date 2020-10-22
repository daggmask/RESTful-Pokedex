package com.example.pokedex.repositories;

import com.example.pokedex.entities.BasicInfo;
import com.example.pokedex.entities.PokemonList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasicInfoRepository extends MongoRepository<BasicInfo,String> {
    Optional<BasicInfo> findByName(String name);
}
