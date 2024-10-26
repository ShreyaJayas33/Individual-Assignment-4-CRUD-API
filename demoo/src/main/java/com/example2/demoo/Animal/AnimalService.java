package com.example2.demoo.Animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public List<Animal> searchAnimalsByName(String name) {
        return animalRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    public Optional<Animal> getAnimalById(int id) {
        return animalRepository.findById(id);
    }

    public List<Animal> getAnimalsBySpecies(String species) {
        return animalRepository.findBySpecies(species);
    }

    public Animal addAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    public Optional<Animal> updateAnimal(int id, Animal animalDetails) {
        return animalRepository.findById(id).map(animal -> {
            animal.setName(animalDetails.getName());
            animal.setScientificName(animalDetails.getScientificName());
            animal.setSpecies(animalDetails.getSpecies());
            animal.setHabitat(animalDetails.getHabitat());
            animal.setDescription(animalDetails.getDescription());
            return animalRepository.save(animal);
        });
    }

    public void deleteAnimal(int id) {
        animalRepository.deleteById(id);
    }
}
