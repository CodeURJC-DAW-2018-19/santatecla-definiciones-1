package com.grupo5.definiciones;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.grupo5.definiciones.controllers.ChapterController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SantateclaDefinicionesApplicationTests {

	@Autowired
	private ChapterController chapterController;
	
	@Test
	public void contextLoads() {
		assertThat(chapterController).isNotNull();
		
	}
	
}

