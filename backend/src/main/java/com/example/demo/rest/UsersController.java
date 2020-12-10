package com.example.demo.rest;

import com.example.demo.dao.MyDAO;
import com.example.demo.dao.UsersIMPL;
import com.example.demo.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//This is to allow calls from React... NOT IMPORTANT RIGHT NOW
@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
public class UsersController {
    private final MyDAO myDAO;

    //Constructor Injection: this is telling the spring framework to wire up your
    //dependencies for the usersDAO.
    @Autowired
    public UsersController(@Qualifier("usersIMPL") MyDAO myDAO) {
        this.myDAO = myDAO;
    }

    //This is a GET request that will read a list of all the users.
    //http://localhost:8080/retrieveAllUsers
    @GetMapping("/retrieveAllUsers")
    public List<Object> findAll() {
        return myDAO.fetchAll();
    }

    //Get a user by their ID
    @GetMapping("/findUserById/{Id}")
    public Object findById(@PathVariable int Id){
        return myDAO.fetchById(Id);
    }

    //Get a user by their ID
    @GetMapping("/findUserByLogin/{username}/{password}")
    public Object findByLogin(@PathVariable String username, @PathVariable String password){
        return myDAO.fetchByLogin(username,password);
    }


    //This is a POST request to add a new user.
    //http://localhost:8080/addUser
    @PostMapping("/addUser")
    public Users addUser(@RequestBody Users theUser) {
        //also just in case they pass an id in JSON .... set id to o
        //this is to force a save of new item .... instead of update
        theUser.setId(0);
        //This will call the usersDqoImpl.save method to save a new employee
        //through the usersDAO interface SPRING
        myDAO.save(theUser);
        return theUser;
    }

    //This is a PUT request to update an existing user.
    //http://localhost:8080/updateUser
    @PutMapping("/updateUser")
    public Users updateUser(@RequestBody Users updateUser) {
        //No theEmployee.setId(0); this will execute an update instead of a create
        myDAO.save(updateUser);
        return updateUser;
    }

    //This is a DELETE request to delete an existing user.
    //http://localhost:8080/deleteUser/1
    @DeleteMapping("/deleteUser/{userId}")
    public String deleteUser(@PathVariable int userId) {
        //Creating a tempUser to check to see if an employee exists
        Users tempUser = (Users) myDAO.fetchById(userId);

        //This will throw an exception if the employee is null
        if(tempUser == null) {
            return "User doesn't exist";
        }

        //This will execute the deleteByID.
        myDAO.deleteById(userId);
        return "user employee id : " + userId;
    }
}
