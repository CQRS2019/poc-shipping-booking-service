package io.agilehandy.web;


import io.agilehandy.bookings.Booking;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookingController.class)
@Slf4j
public class BookingControllerTestsWithoutApplication {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookingService service;
    private String bookingJson;
    @Test
    public void should_return_booking_number() throws Exception {
        String bookingNumber = "26754c05-30d3-4e7e-8333-920849eb51b6";
        Booking booking=new Booking();
        booking.setId(UUID.fromString(bookingNumber));

        given(this.service.getBookingById(bookingNumber)).willReturn(booking);
        MvcResult result = mockMvc.perform(post("/" ).content("{}").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        bookingNumber=result.getResponse().getContentAsString();

    }
    @Before
    public void setUp() {
        bookingJson="{\n" +
                "  \"cargoRequests\": [\n" +
                "    {\n" +
                "      \"requiredSize\": \"SIZE_20\",\n" +
                "      \"nature\": \"DANGEROUS_CARGO\",\n" +
                "      \"origin\": {\n" +
                "        \"opZone\": \"zone-1\",\n" +
                "        \"facility\": \"facility-1\"\n" +
                "      },\n" +
                "      \"destination\": {\n" +
                "        \"opZone\": \"zone-4\",\n" +
                "        \"facility\": \"facility-4\"\n" +
                "      },\n" +
                "      \"cutOffDate\": \"2019-08-01 10:30:00\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"requiredSize\": \"SIZE_40\",\n" +
                "      \"nature\": \"GENERAL_CARGO\",\n" +
                "      \"origin\": {\n" +
                "        \"opZone\": \"zone-5\",\n" +
                "        \"facility\": \"facility-5\"\n" +
                "      },\n" +
                "      \"destination\": {\n" +
                "        \"opZone\": \"zone-7\",\n" +
                "        \"facility\": \"facility-7\"\n" +
                "      },\n" +
                "      \"cutOffDate\": \"2019-08-10 12:00:00\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }
}