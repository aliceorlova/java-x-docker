package com.example.videogameclient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideogameRepository extends CrudRepository<VideogameEntity, Integer> {
}
