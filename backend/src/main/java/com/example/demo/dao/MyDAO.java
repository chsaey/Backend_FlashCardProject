package com.example.demo.dao;

import java.util.List;

public interface MyDAO {
    List<Object> fetchAll();
    Object fetchById(int theId);
    Object fetchByLogin(String userName, String password);
    void save(Object object);
    void deleteById(int theId);

}
