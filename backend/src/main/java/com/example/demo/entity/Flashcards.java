package com.example.demo.entity;

import javax.persistence.*;


//Employee Entity
@Entity //This will let Java know that this is an entity that we are going to map to a database table.
@Table(name = "flashcard") //This is for the actual name of the database table we are mapping to the class.
public class Flashcards {

    @Id //This will map the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //This will auto increment your primary key
    @Column(name = "id") //This is mapping the primary key to the id column in the table.
    private int id;

    @ManyToOne
    @JoinColumn(name="setID")
    private FlashcardSets flashcardSets;

    @Column(name = "question")
    private String question;

    @Column(name = "answer")
    private String answer;

    public Flashcards() {}

    public Flashcards(FlashcardSets flashcardSets, String question, String answer) {
        this.flashcardSets = flashcardSets;
        this.question = question;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FlashcardSets getFlashcardSets() {
        return flashcardSets;
    }

    public void setFlashcardSets(FlashcardSets flashcardSets) {
        this.flashcardSets = flashcardSets;
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
                ", flashcardSets=" + flashcardSets +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
