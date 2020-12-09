package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo.rest.FlashcardSetsController;
import com.example.demo.rest.FlashcardsController;
import com.example.demo.rest.UsersController;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//tests to ensure controllers are created
@SpringBootTest
class FlashCardsApplicationTests {

	//controllers are injected before the test methods
	@Autowired
	private FlashcardsController flashCards;
	@Autowired
	private FlashcardSetsController cardSet;
	@Autowired
	private UsersController users;


	@Test
	void contextLoads()  throws Exception{
		assertThat(flashCards).isNotNull();
		assertThat(cardSet).isNotNull();
		assertThat(users).isNotNull();
	}
}
