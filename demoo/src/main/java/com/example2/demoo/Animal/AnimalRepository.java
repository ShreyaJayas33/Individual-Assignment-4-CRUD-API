package com.example2.demoo.Animal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    List<Animal> findByNameContainingIgnoreCase(String name);
    List<Animal> findBySpecies(String species);
}
