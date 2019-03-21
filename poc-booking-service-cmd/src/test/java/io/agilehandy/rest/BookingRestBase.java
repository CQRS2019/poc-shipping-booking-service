package io.agilehandy.rest;

import io.agilehandy.pubsub.BookingEventPubSub;
import io.agilehandy.web.BookingController;
import io.agilehandy.web.BookingRepository;
import io.agilehandy.web.BookingService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;

//remove::end[]

public abstract class BookingRestBase {
//    private BookingRepository repository= new BookingRepository();
//    //
////    //remove::start[]
//    BookingController bookingController = new BookingController(new BookingService(repository));
////
//    @Before
//    public void setup() {
//        RestAssuredMockMvc.standaloneSetup(this.bookingController);
//    }


}
