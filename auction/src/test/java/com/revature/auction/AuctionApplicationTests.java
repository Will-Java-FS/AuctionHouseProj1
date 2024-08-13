package com.revature.auction;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
		"DB_username=postgres",
		"DB_password=pokemon", // you have to change this to your password for postgres db
		"server.port=0" // Use a random free port
})
class AuctionApplicationTests {

	@Test
	void contextLoads() {
		//check if the application will run
	}

}
