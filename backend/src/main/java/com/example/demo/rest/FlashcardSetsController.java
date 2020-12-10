package com.example.demo.rest;

import com.example.demo.dao.FlashcardSetsIMPL;
import com.example.demo.dao.FlashcardsIMPL;
import com.example.demo.dao.MyDAO;
import com.example.demo.entity.FlashcardSets;
import com.example.demo.entity.Flashcards;
import com.example.demo.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//This is to allow calls from React... NOT IMPORTANT RIGHT NOW
@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class FlashcardSetsController {
    private final MyDAO myDAO;


    //Constructor Injection: this is telling the spring framework to wire up your
    //dependencies for the flashcardsetsDAO.
    @Autowired
    public FlashcardSetsController(@Qualifier("flashcardSetsIMPL") MyDAO myDAO) {
        this.myDAO = myDAO;
    }

    //This is a GET request that will read a list of all the flashcardSets.
    //http://localhost:8080/retrieveAllFlashcardSets
    @GetMapping("/retrieveAllFlashcardSets")
    public List<Object> findAll() {
        return myDAO.fetchAll();
    }

    //This is a POST request to add a new flashcardSet.
    //http://localhost:8080/addFlashcardSet
    @PostMapping("/addFlashcardSet")
    public FlashcardSets addFlashcardSet(@RequestBody FlashcardSets flashcardSets) {

        myDAO.save(flashcardSets);
        return flashcardSets;
    }

    //This is a PUT request to update an existing flashcardSet.
    //http://localhost:8080/updateFlashcardSet
    @PutMapping("/updateFlashcardSet")
    public FlashcardSets updateFlashcardSet(@RequestBody FlashcardSets updateFlashcardSet) {
        FlashcardSets result = (FlashcardSets) myDAO.fetchById(updateFlashcardSet.getId());
        result.setName(updateFlashcardSet.getName());
        //No theEmployee.setId(0); this will execute an update instead of a create
        myDAO.save(result);
        return result;
    }

    //This is a DELETE request to delete an existing employee.
    //http://localhost:8080/deleteFlashcardSet/1
    @DeleteMapping("/deleteFlashcardSet/{flashcardSetId}")
    public String deleteEmployee(@PathVariable int flashcardSetId) {
        //Creating a tempFlashcardSet to check to see if an employee exists
        FlashcardSets tempFlashcardSet = (FlashcardSets) myDAO.fetchById(flashcardSetId);

        //This will throw an exception if the flashcardSet is null
        if (tempFlashcardSet == null) {
            throw new RuntimeException("flashcardSet is not found : " + flashcardSetId);
        }

        //This will execute the deleteByID.
        myDAO.deleteById(flashcardSetId);
        return "Deleted flashcardSet id : " + flashcardSetId;
    }
}
