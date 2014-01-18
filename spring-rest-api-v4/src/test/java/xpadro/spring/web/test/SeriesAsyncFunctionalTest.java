package xpadro.spring.web.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.AsyncRestTemplate;

import xpadro.spring.web.model.Series;

public class SeriesAsyncFunctionalTest extends SeriesFunctionalBaseTests {
	private static final String BASE_URI = "http://localhost:8080/spring-rest-api-v4/spring/series";
	private static Logger logger = LoggerFactory.getLogger(SeriesAsyncFunctionalTest.class);
	
	private AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();
	
	@Before
	public void setup() {
		initializeDatabase();
	}
	
	
	@Test
	public void getAllSeriesAsync() throws InterruptedException, ExecutionException {
		logger.info("Calling async /series");
		Future<ResponseEntity<Series[]>> futureEntity = asyncRestTemplate.getForEntity(BASE_URI, Series[].class);
		logger.info("Doing other async stuff...");
		
		logger.info("Blocking to receive response...");
		ResponseEntity<Series[]> entity = futureEntity.get();
		logger.info("Response received");
		Series[] series = entity.getBody();
		
		assertNotNull(series);
		assertEquals(2, series.length);
		assertEquals(1L, series[0].getId());
		assertEquals("The walking dead", series[0].getName());
		assertEquals("USA", series[0].getCountry());
		assertEquals("Thriller", series[0].getGenre());
		assertEquals(2L, series[1].getId());
		assertEquals("Homeland", series[1].getName());
		assertEquals("USA", series[1].getCountry());
		assertEquals("Drama", series[1].getGenre());
	}
}
