/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package io.agilehandy.web;

import io.agilehandy.bookings.Booking;
import io.agilehandy.pubsub.BookingEventPubSub;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.state.HostInfo;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

//import org.springframework.cloud.stream.binder.kafka.streams.QueryableStoreRegistry;

/**
 * @author Haytham Mohamed
 **/

@Component
@Slf4j
public class BookingRepository {
	private final BookingEventPubSub pubsub;
	private final InteractiveQueryService queryableStoreRegistry;
	//private  interactiveQueryService;
	public BookingRepository(BookingEventPubSub pubsub
			, InteractiveQueryService queryableStoreRegistry) {
		this.pubsub = pubsub;
		this.queryableStoreRegistry = queryableStoreRegistry;
	}


	public void save(Booking booking) {
		booking.getCache().stream().forEach(e -> {
			log.debug(String.format("publishing [bookingId: %s, eventType: %s]"
					, e.getBookingId(), e.getType()));
			pubsub.publish(e);
		});
		booking.clearEventCache();
	}

	public Booking findById(String id) {
		ReadOnlyKeyValueStore<String, Booking> queryStore =
				queryableStoreRegistry.getQueryableStore(BookingEventPubSub.EVENTS_SNAPSHOT
						, QueryableStoreTypes.<String, Booking>keyValueStore());
				//getQueryableStoreType(BookingEventPubSub.EVENTS_SNAPSHOT
				//		, QueryableStoreTypes.<String, Booking>keyValueStore());
		
		HostInfo hostInfo = queryableStoreRegistry.getHostInfo(BookingEventPubSub.EVENTS_SNAPSHOT,
				id, new StringSerializer());

		if (queryableStoreRegistry.getCurrentHostInfo().equals(hostInfo)) {
		
			log.info("========= queryStore:" + queryStore);
			return queryStore.get(id);
		} else {
			String url = String.format("http://%s:%d/%s", hostInfo.host(),
					  hostInfo.port(), "/command/bookings/" + id);
			log.info("========= url:" + url);
			RestTemplate restTemplate = new RestTemplate();
			return restTemplate.getForObject(url, Booking.class);
		}
		
	}

	public List<Booking> findAll() {
		ReadOnlyKeyValueStore<String, Booking> queryStore =
				queryableStoreRegistry.getQueryableStore(BookingEventPubSub.EVENTS_SNAPSHOT
						, QueryableStoreTypes.<String, Booking>keyValueStore());
		KeyValueIterator<String, Booking> all = queryStore.all();
		List<Booking> bookings = new ArrayList<>();
		while(all.hasNext()) {
			bookings.add(all.next().value);
		}
		return bookings;
	}
}
