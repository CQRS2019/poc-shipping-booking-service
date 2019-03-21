package io.agilehandy.rest;

import io.agilehandy.bookings.PocBookingServiceCmdCosnumerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,classes = PocBookingServiceCmdCosnumerApplication.class)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
//remove::start[]
// example of usage with fixed port
@DirtiesContext
@AutoConfigureStubRunner(ids = {"io.agilehandy:http-server:+:stubs:8085"}, stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class BookingCreationTest {
    @Autowired
    MockMvc mockMvc;


    //remove::start[]
    @StubRunnerPort("beer-api-producer")
    int producerPort;



    //remove::end[]
    //tag::tests[]
    @Test
    public void should_give_me_a_beer_when_im_old_enough() throws Exception {
        //remove::start[]

        //remove::end[]
    }


}
