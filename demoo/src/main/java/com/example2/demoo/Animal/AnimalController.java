package com.example2.demoo.Animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    // Display all animals
    @GetMapping("/all")
    public String getAllAnimals(Model model) {
        List<Animal> animals = animalService.getAllAnimals();
        model.addAttribute("animalList", animals);
        model.addAttribute("title", "All Animals");
        return "animal-list";
    }

    // Display details of a single animal by ID
    @GetMapping("/{id}")
    public String getAnimalById(@PathVariable int id, Model model) {
        Optional<Animal> animal = animalService.getAnimalById(id);
        if (animal.isPresent()) {
            model.addAttribute("animal", animal.get());
            model.addAttribute("title", "Animal Details");
            return "animal-details";
        } else {
            model.addAttribute("errorMessage", "Animal not found");
            return "error-page";
        }
    }

    // Show form for creating a new animal
    @GetMapping("/createForm")
    public String showCreateForm(Model model) {
        model.addAttribute("animal", new Animal());
        return "animal-create"; // Return the form for creating an animal
    }

    // POST endpoint for creating a new animal
    @PostMapping("/new")
    public String addAnimal(@ModelAttribute Animal animal) {
        animalService.addAnimal(animal);
        return "redirect:/animals/all"; // Redirect to the animal list after creation
    }

    // Show form for updating an existing animal
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable int id, Model model) {
        Optional<Animal> animal = animalService.getAnimalById(id);
        if (animal.isPresent()) {
            model.addAttribute("animal", animal.get());
            return "animal-update"; // Return the form for updating an animal
        } else {
            model.addAttribute("errorMessage", "Animal not found");
            return "error-page";
        }
    }

    // POST endpoint for updating an existing animal
    @PostMapping("/update")
    public String updateAnimal(@ModelAttribute Animal animal) {
        animalService.updateAnimal(animal.getAnimalId(), animal);
        return "redirect:/animals/" + animal.getAnimalId(); // Redirect to the details page of the updated animal
    }

    // Delete an animal
    @GetMapping("/delete/{id}")
    public String deleteAnimal(@PathVariable int id) {
        animalService.deleteAnimal(id);
        return "redirect:/animals/all"; // Redirect to the list view after deletion
    }

    // Search for animals by species
    @GetMapping("/species")
    public String getAnimalsBySpecies(@RequestParam String species, Model model) {
        List<Animal> animals = animalService.getAnimalsBySpecies(species);
        model.addAttribute("animalList", animals);
        model.addAttribute("title", "Animals of Species: " + species);
        return "animal-list";
    }

    // Search for animals by name (substring)
    @GetMapping("/search")
    public String searchAnimalsByName(@RequestParam String name, Model model) {
        List<Animal> animals = animalService.searchAnimalsByName(name);
        model.addAttribute("animalList", animals);
        model.addAttribute("title", "Search Results for '" + name + "'");
        return "animal-list";
    }
}
