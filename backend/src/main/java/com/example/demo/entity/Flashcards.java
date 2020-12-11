package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;


//Flashcard Entity
@Entity //This will let Java know that this is an entity that we are going to map to a database table.
@Table(name = "flashcard") //This is for the actual name of the database table we are mapping to the class.
// When using lazy loading, we can tell Jackson to ignore helpful garbage hibernate adds to classes
//Fixes the issues of serializing entities to/from the DB
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Flashcards {

    @Id //This will map the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //This will auto increment your primary key
    @Column(name = "id") //This is mapping the primary key to the id column in the table.
    private int id;

    @ManyToOne(fetch = FetchType.LAZY,  optional = false) // Many flashcards can belong to one set
    @OnDelete(action = OnDeleteAction.CASCADE) // One a set is deleted, cards of the set are also deleted
    @JoinColumn(name = "setID", nullable = false) // foreign key, column to join on
    @JsonIgnore// hide field from parser
    private FlashcardSets flashcardsets;

    @JsonIgnore // shouldn't be here, but don't want to make any changes since it's working
    @Column(name = "setID", updatable = false, insertable = false)
    private int setID;

    @Column(name = "question")
    private String question;

    @Column(name = "answer")
    private String answer;

    public Flashcards() {

    }
    public int getSetID() {
        return setID;
    }

    public void setSetID(int setID) {
        this.setID = setID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FlashcardSets getFlashcardSets() {
        return flashcardsets;
    }

    public void setFlashcardSets(FlashcardSets flashcardsets) {
        this.flashcardsets = flashcardsets;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Flashcards{" +
                "id=" + id +
                ", flashcardsets=" + flashcardsets +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
