package com.example.demo;

import com.example.demo.dao.MyDAO;
import com.example.demo.entity.Users;
import com.example.demo.rest.UsersController;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    void addUser() throws Exception {
        Users user = new Users("testValue", "pwd", " Charles", "Saeyang");
        String userString = gson.toJson(user);
        Users result = userController.addUser(user);
        assertThat(result.getFirstName().equals(user.getFirstName()));
        String url = "/addUser";

        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userString)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateUser() throws Exception {
        Users user = new Users("update", "pwd", " Charles", "Saeyang");
        user.setId(4);
        String userString = gson.toJson(user);
        String url = "/updateUser";

        mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userString)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUser() throws Exception {
        String url = "/deleteUser/" + 1;
        mvc.perform(delete(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}