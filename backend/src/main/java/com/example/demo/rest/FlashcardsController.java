package com.example.demo.rest;

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
public class FlashcardsController {

    private final MyDAO myDAO;

    //Constructor Injection: this is telling the spring framework to wire up your
    //dependencies for the flashcardsDAO.

    @Autowired
    public FlashcardsController(@Qualifier("flashcardsIMPL") MyDAO myDAO) {
        this.myDAO = myDAO;
    }

    //This is a GET request that will read a list of all the flashcards.
    //http://localhost:8080/retrieveAllFlashcards
    @GetMapping("/retrieveAllFlashcards")
    public List<Object> findAll() {
        return myDAO.fetchAll();
    }

    //This is a POST request to add a new flashcard.
    //http://localhost:8080/addFlashcard
    @PostMapping("/addFlashcard")
    public Flashcards addFlashcard(@RequestBody Flashcards theCard) {
        //also just in case they pass an id in JSON .... set id to o
        //this is to force a save of new item .... instead of update
        System.out.println("");
        theCard.setId(0);

        //This will call the flashcardsDqoImpl.save method to save a new employee
        //through the flashcardsDAO interface SPRING
        myDAO.save(theCard);
        return theCard;
    }

    //This is a PUT request to update an existing employee.
    //http://localhost:8080/updateFlashcard
    @PutMapping("/updateFlashcard")
    public Flashcards updateFlashcard(@RequestBody Flashcards updateFlashcard) {
        //No theEmployee.setId(0); this will execute an update instead of a create
        myDAO.save(updateFlashcard);
        return updateFlashcard;
    }

    //This is a DELETE request to delete an existing employee.
    //http://localhost:8080/deleteFlashcard/1
    @DeleteMapping("/deleteFlashcard/{flashcardId}")
    public String deleteFlashcard(@PathVariable int flashcardId) {
        //Creating a tempFlashcards to check to see if an employee exists
        Flashcards tempFlashcards = (Flashcards) myDAO.fetchById(flashcardId);

        //This will throw an exception if the employee is null
        if (tempFlashcards == null) {
            throw new RuntimeException("Employee is not found : " + flashcardId);
        }

        //This will execute the deleteByID.
        myDAO.deleteById(flashcardId);
        return "Deleted flashcard id : " + flashcardId;
    }
}
