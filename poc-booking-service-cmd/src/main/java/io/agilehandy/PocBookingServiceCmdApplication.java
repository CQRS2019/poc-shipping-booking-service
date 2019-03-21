package io.agilehandy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Haytham Mohamed
 **/

@SpringBootApplication
@EnableDiscoveryClient
public class PocBookingServiceCmdApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocBookingServiceCmdApplication.class, args);
	}

}
