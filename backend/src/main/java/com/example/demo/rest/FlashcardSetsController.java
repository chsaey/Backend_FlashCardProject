package com.example.demo.rest;

import com.example.demo.dao.MyDAO;
import com.example.demo.entity.FlashcardSets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//This is to allow calls from React... NOT IMPORTANT RIGHT NOW
@CrossOrigin(origins = { "http://localhost:3000"})
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
    //http://localhost:8080/FlashcardSets
    @GetMapping("/retrieveAllFlashcardSets")
    public List<Object> findAll() {
        return myDAO.fetchAll();
    }

    //This is a POST request to add a new flashcardSet.
    //http://localhost:8080/addFlashcardSet
    @PostMapping("/addFlashcardSet")
    public FlashcardSets addFlashcardSet(@RequestBody FlashcardSets theFlashcardSet) {
        //also just in case they pass an id in JSON .... set id to o
        //this is to force a save of new item .... instead of update
        theFlashcardSet.setId(0);

        //This will call the employeeDqoImpl.save method to save a new flashcardSet
        //through the employeeDAO interface SPRING
        myDAO.save(theFlashcardSet);
        return theFlashcardSet;
    }

    //This is a PUT request to update an existing flashcardSet.
    //http://localhost:8080/updateFlashcardSet
    @PutMapping("/updateFlashcardSet")
    public FlashcardSets updateFlashcardSet(@RequestBody FlashcardSets updateFlashcardSet) {
        //No theEmployee.setId(0); this will execute an update instead of a create
        myDAO.save(updateFlashcardSet);
        return updateFlashcardSet;
    }

    //This is a DELETE request to delete an existing employee.
    //http://localhost:8080/deleteFlashcardSet/1
    @DeleteMapping("/deleteFlashcardSet/{flashcardSetId}")
    public String deleteEmployee(@PathVariable int flashcardSetId) {
        //Creating a tempFlashcardSet to check to see if an employee exists
        FlashcardSets tempFlashcardSet = (FlashcardSets) myDAO.fetchById(flashcardSetId);

        //This will throw an exception if the flashcardSet is null
        if(tempFlashcardSet == null) {
            throw new RuntimeException("flashcardSet is not found : " + flashcardSetId);
        }

        //This will execute the deleteByID.
        myDAO.deleteById(flashcardSetId);
        return "Deleted flashcardSet id : " + flashcardSetId;
    }
}
