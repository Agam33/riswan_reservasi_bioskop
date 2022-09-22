package com.ra.bioskop;

import com.ra.bioskop.dto.model.film.ScheduleDTO;
import com.ra.bioskop.model.film.FilmGenre;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

@SpringBootTest
class BioskopApplicationTests {

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

	@Test
	void testTime() {
//		LocalDateTime localDateTime = LocalDateTime.now();
	}
}
