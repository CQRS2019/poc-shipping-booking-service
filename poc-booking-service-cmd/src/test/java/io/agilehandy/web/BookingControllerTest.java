package io.agilehandy.web;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.equalTo;
@RunWith(SpringJUnit4ClassRunner.class)

class BookingControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Before
    void setUp() {
            RestAssured.port = 8082;
            RestAssured.baseURI = "http://localhost"; // replace as appropriate
    }

    @Test
    void createBooking() {
    }

    @Test
    void should_return_a_booking_when_input_a_valid_booking_number() {
        String bookingNumber="26754c05-30d3-4e7e-8333-920849eb51b6";
        get("/command/bookings/"+bookingNumber).then().assertThat().content("id",equalTo(bookingNumber)).
                content("cargoList[0].nature",equalTo("DANGEROUS_CARGO"));
    }


    @Test
    void pathBookingStatus() {
    }
}