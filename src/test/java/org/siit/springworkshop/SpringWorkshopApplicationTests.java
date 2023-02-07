package org.siit.springworkshop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class SpringWorkshopApplicationTests {

	@Value("${my.name}")
	private String name;
	@Test
	void contextLoads() {
		System.out.println(name);
	}

}
