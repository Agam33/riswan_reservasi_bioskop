package com.ra.nontonfilm;

import com.ra.nontonfilm.model.film.FilmGenre;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.UUID;

@SpringBootTest
class NontonFilmApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testGenre() {
		var genres = FilmGenre.values();
		System.out.println(FilmGenre.getById(6));
	}

	@Test
	void testUUID() {
		byte[] name = "riswanagam".getBytes();
		UUID uuid = UUID.nameUUIDFromBytes(name);
		String[] splitUUID = uuid.toString().split("-");

		System.out.println("UUID : "+uuid);
		System.out.println("User ID : user-"+splitUUID[4]);

	}
}
