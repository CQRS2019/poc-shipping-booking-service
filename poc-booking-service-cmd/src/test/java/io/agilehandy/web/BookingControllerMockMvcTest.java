package io.agilehandy.web;

import io.agilehandy.PocBookingServiceCmdApplication;
import io.restassured.RestAssured;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = PocBookingServiceCmdApplication.class)
@AutoConfigureMockMvc
public class BookingControllerMockMvcTest {
    @Autowired
    private MockMvc mockMvc;

    private String bookingJson;
    private String bookingNumber;

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



    @Test
   public void should_return_booking_number() throws Exception {
        MvcResult result = mockMvc.perform(post("/" ).content(bookingJson).contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        bookingNumber=result.getResponse().getContentAsString();

    }


    @Test
    public void should_return_a_booking_json() throws Exception {
        bookingNumber="26754c05-30d3-4e7e-8333-920849eb51b6";
        mockMvc.perform(get("/"+bookingNumber )).andDo(print()).
                andExpect(jsonPath("$.id").value(bookingNumber));

    }

    @Test
    public void pathBookingStatus() {
    }
}