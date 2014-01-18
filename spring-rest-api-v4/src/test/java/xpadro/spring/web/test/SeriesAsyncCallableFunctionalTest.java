package xpadro.spring.web.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.concurrent.ExecutionException;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;

import xpadro.spring.web.model.Series;

public class SeriesAsyncCallableFunctionalTest  extends SeriesFunctionalBaseTests {
	private static final String BASE_URI = "http://localhost:8080/spring-rest-api-v4/spring/series";
	private static Logger logger = LoggerFactory.getLogger(SeriesAsyncCallableFunctionalTest.class);
	
	private AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();
	
	@Before
	public void setup() {
		initializeDatabase();
	}
	
	
	@Test
	public void getAllSeriesAsyncCallable() throws InterruptedException, ExecutionException {
		logger.info("Calling async callable /series");
		ListenableFuture<ResponseEntity<Series[]>> futureEntity = asyncRestTemplate.getForEntity(BASE_URI, Series[].class);
		futureEntity.addCallback(new ListenableFutureCallback<ResponseEntity<Series[]>>() {
		    @Override
		    public void onSuccess(ResponseEntity<Series[]> entity) {
		    	logger.info("Response received (async callable)");
		    	Series[] series = entity.getBody();
				validateList(series);
		    }

		    @Override
		    public void onFailure(Throwable t) {
		        fail();
		    }
		});
		logger.info("Doing other async callable stuff ...");
		Thread.sleep(6000); //waits for the service to send the response
	}
	
	private void validateList(Series[] series) {
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
