package io.agilehandy.qry.summary;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class LocationSummaryRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LocationSummaryRepository repository;

    @Test
    public void should_return_valid_summary()  {
        LocationSummary summary=new LocationSummary("HKG","HKG01");
        repository.save(summary);
        Optional<LocationSummary> summaryFromDB = this.repository.findByZoneAndFacility("HKG","HKG01");
        assertThat(summaryFromDB.get().getRequestIn().longValue()).isEqualTo(0L);
    }

}