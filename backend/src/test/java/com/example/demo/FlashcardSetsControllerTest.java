package com.example.demo;


import com.example.demo.dao.MyDAO;
import com.example.demo.entity.FlashcardSets;
import com.example.demo.entity.Users;
import com.example.demo.rest.FlashcardSetsController;

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


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    FlashcardSetsController sets = spy(new FlashcardSetsController(myDAO));

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
/*        FlashcardSets test = new FlashcardSets();
        Users user = new Users("Dummy2", "pwd", null, null);
        user.setId(2);
        test.setName("Wombo");
        test.setUsers(user);
        doReturn(test).when(sets).addFlashcardSet(test);
        FlashcardSets res = sets.addFlashcardSet(test);
        assertThat(res.getId() == 0);
        assertThat(res.getName().equals("Wombo"));*/
    }


    @Test
    void updateFlashcardSet() throws Exception {
        FlashcardSets test = new FlashcardSets();
        Users user = new Users("Dummy2", "pwd", null, null);
        user.setId(2);
        test.setName("Wombo");
        test.setUsers(user);
        doReturn(test).when(sets).updateFlashcardSet(test);
        FlashcardSets res = sets.updateFlashcardSet(test);
        assertThat(res.getId() == 2);
        assertThat(res.getName().equals("Wombo"));
    }

    @Test
    void deleteEmployee() throws Exception {

        String url = "/deleteFlashcardSet/" + 5;
        mvc.perform(delete(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}