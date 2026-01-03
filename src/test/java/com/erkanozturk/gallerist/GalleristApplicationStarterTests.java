package com.erkanozturk.gallerist;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.erkanozturk.starter.GalleristApplicationStarter;

@SpringBootTest
@ContextConfiguration(classes = GalleristApplicationStarter.class)
class GalleristApplicationStarterTests {

	@Test
	void contextLoads() {
	}

}
