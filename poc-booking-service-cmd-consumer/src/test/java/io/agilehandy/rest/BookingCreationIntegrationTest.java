package io.agilehandy.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.agilehandy.bookings.PocBookingServiceCmdCosnumerApplication;
import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import static org.springframework.cloud.contract.verifier.util.xml.XmlAssertion.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = PocBookingServiceCmdCosnumerApplication.class)
@AutoConfigureJsonTesters
@DirtiesContext
//@AutoConfigureMockMvc
@AutoConfigureStubRunner(ids = {"io.agilehandy:poc-booking-service-cmd:+:stubs:8085"}, stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class BookingCreationIntegrationTest {
    @Autowired
    TestRestTemplate restTemplate;
    @Before
    public void setup() {
        ObjectMapper objectMappper = new ObjectMapper();
        // Possibly configure the mapper
        JacksonTester.initFields(this, objectMappper);
    }

//    @Autowired
//    private MockMvc mockMvc;
    private final String createBookingJson="{\n" +
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

//   @Test
//    public void testMethod() throws Exception {
//        this.mockMvc.perform(MockMvcRequestBuilders.post("/command/bookings")
//                .header("Content-Type", "application/json").content(createBookingJson)).andExpect(status().isOk())
//                .andExpect(content().string("69855ab4-4370-4c44-848e-5b13fb1dfbcb"));
//    }
@Test
    public void shouldReturnOneUUID() throws Exception {

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("Content-Type", "application/json");
    ResponseEntity<String> response = restTemplate.exchange("http://localhost:8085/command/bookings", HttpMethod.POST,
            new HttpEntity<>(createBookingJson, httpHeaders),
            String.class);


}


}
