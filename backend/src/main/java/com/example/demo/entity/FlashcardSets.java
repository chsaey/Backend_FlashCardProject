package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;


@Entity //This will let Java know that this is an entity that we are going to map to a database table.
@Table(name = "flashcardset") //This is for the actual name of the database table we are mapping to the class.
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FlashcardSets {

    @Id //This will map the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //This will auto increment your primary key
    @Column(name = "id") //This is mapping the primary key to the id column in the table.
    private int id;

    @Column(name = "name")
    private String name;

    //Many to one -> A user can have many sets of flash cards
    //Lazy fetchType meaning initialization is deferred as long as possible.
    @ManyToOne(fetch = FetchType.LAZY,  optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)    // If a user is deleted, all sets belonging to them are also deleted.
    @JoinColumn(name = "userID", nullable = false) // creates the column on which they join on, the foreign key. can't be null
    @JsonIgnore // ignore field when serializing
    private Users users;

    // supposedly allows us to input an integer rather then a user entity when creating a card set entity
    @Column(name = "userID", updatable = false, insertable = false)
    private int userID;

    //Mapping to flashcards. One set can have many flashcards
    @OneToMany(mappedBy = "setID")
    private Set<Flashcards> cards;


    public FlashcardSets() {
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "FlashcardSets{" +
                "id=" + id +
                ", users=" + users +
                ", name='" + name + '\'' +
                '}';
    }
}
