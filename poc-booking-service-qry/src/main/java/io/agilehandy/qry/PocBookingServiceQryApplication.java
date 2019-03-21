package io.agilehandy.qry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication

public class PocBookingServiceQryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocBookingServiceQryApplication.class, args);
	}

}
