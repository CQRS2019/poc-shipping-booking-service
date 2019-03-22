package io.agilehandy.rest;

import io.agilehandy.PocBookingServiceCmdApplication;
import io.agilehandy.web.BookingController;
import io.agilehandy.web.BookingService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = PocBookingServiceCmdApplication.class)

public abstract class BookingRestBase {
@Autowired
private  BookingController bookingController ;



    @Before
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(this.bookingController);
    }


}
