package xpadro.spring.rest.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
	private RestTemplate restTemplate = new RestTemplate();

	@Before
	public void setup() {
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(new StringHttpMessageConverter());
		converters.add(new Jaxb2RootElementHttpMessageConverter());
		converters.add(new MappingJacksonHttpMessageConverter());
		restTemplate.setMessageConverters(converters);
	}

	/**
	 * Tests accessing to an existing user
	 * This test should use the MappingJacksonHttpMessageConverter
	 */
	@Test
	public void getUser() {
		String uri = "http://localhost:8081/rest-converters/spring/users/{userId}";
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
		String uri = "http://localhost:8081/rest-converters/spring/usernames/{userId}";
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
		String uri = "http://localhost:8081/rest-converters/spring/cars/{carId}";
		Car car = restTemplate.getForObject(uri, Car.class, 1l);
		assertNotNull(car);
		assertEquals("Ferrari", car.getBrand());
	}
}
