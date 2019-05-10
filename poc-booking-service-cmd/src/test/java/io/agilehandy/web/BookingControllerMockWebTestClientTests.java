package io.agilehandy.web;


import io.agilehandy.bookings.Booking;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.UUID;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,properties = "spring.main.web-application-type=reactive")
@AutoConfigureWebTestClient
public class BookingControllerMockWebTestClientTests {

    @Autowired
    private WebTestClient webClient;
    @MockBean
    private BookingService service;
    @Test
    public void should_return_a_booking_when_input_a_valid_booking_number() {
        String bookingNumber = "26754c05-30d3-4e7e-8333-920849eb51b6";
        Booking booking=new Booking();
        booking.setId(UUID.fromString(bookingNumber));

        given(this.service.getBookingById(bookingNumber)).willReturn(booking);

        EntityExchangeResult<byte[]> result = this.webClient.get().uri("/" + bookingNumber).exchange().expectStatus().isOk()
                .expectBody().jsonPath("$.id").isEqualTo(bookingNumber).returnResult();
        System.out.println(new String(result.getResponseBody()));
    }

}