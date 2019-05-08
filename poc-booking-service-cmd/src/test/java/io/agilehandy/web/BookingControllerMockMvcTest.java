package io.agilehandy.web;

import io.agilehandy.PocBookingServiceCmdApplication;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = PocBookingServiceCmdApplication.class)

class BookingControllerMockMvcTest {
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
    void should_return_a_booking_when_input_a_valid_booking_number() throws Exception {
        String bookingNumber = "26754c05-30d3-4e7e-8333-920849eb51b6";
        mockMvc.perform(get("/command/bookings/" )).andDo(print()).
                andExpect(jsonPath("$.id").value(bookingNumber));

    }

    @Test
    void pathBookingStatus() {
    }
}