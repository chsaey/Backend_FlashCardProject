package com.example.demo;

import com.example.demo.dao.MyDAO;
import com.example.demo.entity.FlashcardSets;

import com.example.demo.entity.Flashcards;
import com.example.demo.entity.Users;
import com.example.demo.rest.FlashcardSetsController;
import com.example.demo.rest.FlashcardsController;
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
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class FlashcardsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    MyDAO myDAO;

    @InjectMocks
    FlashcardsController flashcardsController = spy(new FlashcardsController(myDAO));

    Gson gson = new Gson();

    @Test
    void findAll() throws Exception {
        String url = "/retrieveAllFlashcards";
        mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void addFlashcard() throws Exception {
        FlashcardSets flashcardSets = new FlashcardSets();
        flashcardSets.setName("Wombo");

        Users user = new Users("Dummy2", "pwd", null, null);
        user.setId(2);

        flashcardSets.setUsers(user);

        Flashcards flashcards = new Flashcards();
        flashcards.setQuestion("Hello?");
        flashcards.setAnswer("Goodbye?");
        flashcards.setFlashcardSets(flashcardSets);

        doReturn(flashcards).when(flashcardsController).addFlashcard(flashcards);
        Flashcards res = flashcardsController.addFlashcard(flashcards);

        assertThat(res.getId() == 0);
        assertThat(res.getQuestion().equals("Hello?"));
        assertThat(res.getAnswer().equals("Goodbye?"));

    }

    @Test
    void updateFlashcard() {

        FlashcardSets flashcardSets = new FlashcardSets();
        flashcardSets.setName("Wombo");

        Users user = new Users("Dummy2", "pwd", null, null);
        user.setId(2);

        flashcardSets.setUsers(user);

        Flashcards flashcards = new Flashcards();
        flashcards.setQuestion("Hello?");
        flashcards.setAnswer("Goodbye?");
        flashcards.setFlashcardSets(flashcardSets);

        doReturn(flashcards).when(flashcardsController).updateFlashcard(flashcards);
        Flashcards res = flashcardsController.updateFlashcard(flashcards);

        assertThat(res.getId() == 2);
        assertThat(res.getQuestion().equals("Hello?"));
        assertThat(res.getAnswer().equals("Goodbye?"));


    }

    @Test
    void deleteFlashcard() throws Exception {
        String url = "/deleteFlashcard/" + 5;
        mvc.perform(delete(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}