package com.example.demo;


import com.example.demo.dao.MyDAO;
import com.example.demo.entity.FlashcardSets;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class FlashcardSetsControllerTest {


    @Autowired
    private MockMvc mvc;

    @Mock
    MyDAO myDAO;

    @InjectMocks
    FlashcardSets sets;

    Gson gson = new Gson();

    @Test
    void findAll() throws Exception {
        String url = "/retrieveAllFlashcardSets";
        mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void addFlashcardSet() throws Exception {
        FlashcardSets test = new FlashcardSets();
        Users user = new Users("Dummy2", "pwd", null, null);
        user.setId(2);
        test.setName("TestSet");
        test.setUsers(user);
        String str = gson.toJson(test);
        System.out.println(str);

        String url = "/addFlashcardSet";

        mvc.perform(post(url)
                .content(str)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateFlashcardSet() {
    }

    @Test
    void deleteEmployee() {
    }
}