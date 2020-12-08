package com.example.demo.dao;

import com.example.demo.entity.Flashcards;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

public class FlashcardsIMPL implements  MyDAO{

    //Define field for entity manager
    /*The EntityManager API is used to create and remove persistent entity instances,
        to find entities by their primary key, and to query over entities. */
    private final EntityManager entityManager;

    //Set up constructor injection
    @Autowired
    public FlashcardsIMPL(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional //Defines the scope of a single database transaction.
    public List<Object> fetchAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Object> myQuery = currentSession.createQuery("from Cards");
        return myQuery.getResultList();
    }

    @Override
    @Transactional //Defines the scope of a single database transaction.
    public Object fetchById(int theId) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(Flashcards.class, theId);
    }

    @Override
    @Transactional //Defines the scope of a single database transaction.
    public void save(Object theCard) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(theCard);
    }

    @Override
    @Transactional //Defines the scope of a single database transaction.
    public void deleteById(int theId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Flashcards myCard = currentSession.get(Flashcards.class, theId);
        currentSession.delete(myCard);
    }
}