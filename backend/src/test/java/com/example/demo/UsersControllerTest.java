package com.example.demo;

import com.example.demo.dao.MyDAO;
import com.example.demo.entity.Users;
import com.example.demo.rest.UsersController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UsersControllerTest {

    @InjectMocks
    UsersController userController;

    @Mock
    MyDAO myDAO;

    @Test
    void findAll() {
        Users user = new Users( "chsaey", "pwd"," Charles","Saeyang");
        Users user2 = new Users( "dfel08", "pwd"," Dan","Fellows");
        Users user3 = new Users( "dodanghiep12", "pwd"," Hiep","Do");
        List<Object> users = new ArrayList<>(Arrays.asList(user,user2,user3));
        when(userController.findAll()).thenReturn(users);
        List<Object> result = userController.findAll();
        assertThat(result.size() == 3);

        Users expectCharles = ((Users) result.get(0));
        Users expectDan = (Users) result.get(1);
        Users expectHiep = (Users) result.get(2);

        assertThat(expectCharles.getFirstName().equals(user.getFirstName()));
        assertThat(expectDan.getFirstName().equals(user2.getFirstName()));
        assertThat(expectHiep.getFirstName().equals(user3.getFirstName()));

    }

    @Test
    void addUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}