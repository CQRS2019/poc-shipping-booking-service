package io.agilehandy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.test.binder.TestSupportBinderAutoConfiguration;

/**
 * @author Haytham Mohamed
 **/

@SpringBootApplication
//@SpringBootApplication(exclude = TestSupportBinderAutoConfiguration.class)
public class PocBookingServiceCmdApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocBookingServiceCmdApplication.class, args);
	}

}
