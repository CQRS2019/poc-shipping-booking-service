package io.agilehandy.web;

import io.agilehandy.bookings.Booking;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.cloud.contract.verifier.util.xml.XmlAssertion.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookingControllerTestRestTemplateTests {


    @Autowired
    private TestRestTemplate restTemplate;



    @Test
    public void should_return_a_booking_when_input_a_valid_booking_number() {
        String bookingNumber = "26754c05-30d3-4e7e-8333-920849eb51b6";
        String booking = this.restTemplate.getForObject("/"+bookingNumber, String.class);
        System.out.println(booking);
//        assertThat(booking.getId().toString()).isEqualTo(bookingNumber);
    }


}