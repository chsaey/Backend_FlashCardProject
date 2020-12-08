package com.example.demo.entity;

import javax.persistence.*;

@Entity //This will let Java know that this is an entity that we are going to map to a database table.
@Table(name = "flashcardset") //This is for the actual name of the database table we are mapping to the class.
public class FlashcardSets {

    @Id //This will map the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //This will auto increment your primary key
    @Column(name = "id") //This is mapping the primary key to the id column in the table.
    private int id;

    @ManyToOne
    @JoinColumn(name="userID")
    private Users users;

    @Column(name = "name")
    private String name;

    public FlashcardSets() {}

    public FlashcardSets(Users users, String name) {
        this.users = users;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
