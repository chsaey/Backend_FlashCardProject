package com.example.demo;

import com.example.demo.dao.MyDAO;
import com.example.demo.entity.Users;
import com.example.demo.rest.UsersController;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.any;
import static org.mockito.Mockito.spy;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UsersControllerTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    MyDAO myDAO;

    @InjectMocks
    UsersController userController;

    Gson gson = new Gson();

    @Test
    void findAll() {


        Users user = new Users("chsaey", "pwd", " Charles", "Saeyang");
        Users user2 = new Users("dfel08", "pwd", " Dan", "Fellows");
        Users user3 = new Users("dodanghiep12", "pwd", " Hiep", "Do");
        List<Object> users = new ArrayList<>(Arrays.asList(user, user2, user3));
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
        Users user = new Users("chsaey", "pwd", " Charles", "Saeyang");
        Users result = userController.addUser(user);
        System.out.println(result.getFirstName());
        assertThat(result.getFirstName().equals(user.getFirstName()));
    }

    @Test
    void addUserMock() {
        Users user = new Users("chsaey", "pwd", " Charles", "Saeyang");
        String userString = gson.toJson(user);
        try {
            mvc.perform(post("/addUser")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(userString))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}