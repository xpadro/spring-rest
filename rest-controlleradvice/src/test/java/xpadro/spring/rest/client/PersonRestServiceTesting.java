package xpadro.spring.rest.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import xpadro.spring.rest.model.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
	"classpath:xpadro/spring/rest/configuration/root-context.xml",
	"classpath:xpadro/spring/rest/configuration/app-context.xml"})
public class PersonRestServiceTesting {
	private RestTemplate restTemplate = new RestTemplate();


	/**
	 * Tests retrieving an existing person
	 */
	@Test
	public void getExistingPerson() {
		String uri = "http://localhost:8081/rest-controlleradvice/spring/persons/{personId}";
		Person person = restTemplate.getForObject(uri, Person.class, 1l);
		assertNotNull(person);
		assertEquals("Xavi", person.getName());
	}
	
	/**
	 * Tests error handling when trying to retrieve a non existent person
	 */
	@Test
	public void getNonExistingPerson() {
		String uri = "http://localhost:8081/rest-controlleradvice/spring/persons/{personId}";
		try {
			restTemplate.getForObject(uri, Person.class, 5l);
			throw new AssertionError("Should have returned an 404 error code");
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
		}
	}
	
	/**
	 * Tests the addition of a new person
	 */
	@Test
	public void addValidPerson() {
		String uri = "http://localhost:8081/rest-controlleradvice/spring/persons";
		Person person = new Person(2l, "John", "Lennon", "Liverpool");
		URI newPersonLocation = restTemplate.postForLocation(uri, person, 2l);
		
		Person createdPerson = restTemplate.getForObject(newPersonLocation, Person.class);
		assertEquals(person, createdPerson);
		assertNotNull(createdPerson.getId());
		assertEquals("John", person.getName());
	}
	
	/**
	 * Tests error handling when trying to add a person with invalid data
	 */
	@Test
	public void addInvalidPerson() {
		String uri = "http://localhost:8081/rest-controlleradvice/spring/persons";
		Person person = new Person(3l, "James", "Steward", "London");
		try {
			restTemplate.postForLocation(uri, person, 3l);
			throw new AssertionError("Should have returned an 400 error code");
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
		}
	}
	
	/**
	 * Tests updating an existing person
	 */
	@Test
	public void updateValidPerson() {
		String uri = "http://localhost:8081/rest-controlleradvice/spring/persons";
		Person person = new Person(2l, "George", "Harrison", "Liverpool");
		restTemplate.put(uri, person, 2l);
		
		uri = "http://localhost:8081/rest-controlleradvice/spring/persons/{personId}";
		Person createdPerson = restTemplate.getForObject(uri, Person.class, 2l);
		assertEquals(person, createdPerson);
		assertNotNull(createdPerson.getId());
		assertEquals("George", person.getName());
	}
	
	/**
	 * Tests error handling when trying to update an existing person with invalid data
	 */
	@Test
	public void updateInvalidPerson() {
		String uri = "http://localhost:8081/rest-controlleradvice/spring/persons";
		Person person = new Person(2l, "John", "Smith", "New York");
		try {
			restTemplate.put(uri, person, 2l);
			throw new AssertionError("Should have returned an 400 error code");
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
		}
	}
	
	/**
	 * Tests error handling when trying to update a non existing person
	 */
	@Test
	public void updateNonExistingPerson() {
		String uri = "http://localhost:8081/rest-controlleradvice/spring/persons";
		Person person = new Person(5l, "Marc", "Brown", "Barcelona");
		try {
			restTemplate.put(uri, person, 5l);
			throw new AssertionError("Should have returned an 404 error code");
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
		}
	}
}
