package xpadro.spring.rest.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import xpadro.spring.rest.model.Car;
import xpadro.spring.rest.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
	"classpath:xpadro/spring/rest/configuration/root-context.xml",
	"classpath:xpadro/spring/rest/configuration/app-context.xml"})
public class RestTesting {
	private static final String BASE_URI = "http://localhost:8080/rest-converters/spring/";
	private RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	private MongoOperations mongoOps;

	@Before
	public void setup() {
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(new StringHttpMessageConverter());
		converters.add(new Jaxb2RootElementHttpMessageConverter());
		converters.add(new MappingJacksonHttpMessageConverter());
		restTemplate.setMessageConverters(converters);
		
		initializeDatabase();
	}
	
	private void initializeDatabase() {
		mongoOps.dropCollection("user");
		mongoOps.dropCollection("car");
		
		mongoOps.insert(new User(1, "Xavi", "Padro"));
		mongoOps.insert(new Car(1, "Ferrari", "GTO"));
	}

	
	/**
	 * Tests accessing to an existing user
	 * This test should use the MappingJacksonHttpMessageConverter
	 */
	@Test
	public void getUser() {
		String uri = BASE_URI + "users/{userId}";
		User user = restTemplate.getForObject(uri, User.class, 1l);
		assertNotNull(user);
		assertEquals("Xavi", user.getName());
	}
	
	/**
	 * Tests accessing to an existing user
	 * This test should use the StringHttpMessageConverter
	 */
	@Test
	public void getUserName() {
		String uri = BASE_URI + "usernames/{userId}";
		String username = restTemplate.getForObject(uri, String.class, 1l);
		assertNotNull(username);
		assertEquals("Xavi Padro", username);
	}
	
	/**
	 * Tests accessing to an existing car
	 * This test should use the Jaxb2RootElementHttpMessageConverter
	 */
	@Test
	public void getCar() {
		String uri = BASE_URI + "cars/{carId}";
		Car car = restTemplate.getForObject(uri, Car.class, 1l);
		assertNotNull(car);
		assertEquals("Ferrari", car.getBrand());
	}
	
	/**
	 * Tests a new user insertion
	 */
	@Test
	public void insertUser() {
		String uri = BASE_URI + "users";
		User user = new User(2, "John", "Smith");
		URI newUserLocation = restTemplate.postForLocation(uri, user);
		assertEquals("http://localhost:8080/rest-converters/spring/users/2", newUserLocation.toString());
	}
}
