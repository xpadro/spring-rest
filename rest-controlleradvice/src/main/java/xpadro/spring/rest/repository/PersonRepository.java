package xpadro.spring.rest.repository;

import xpadro.spring.rest.exception.PersonNotFoundException;
import xpadro.spring.rest.model.Person;

/**
 * Manages data from persons
 * @author xpadro
 *
 */
public interface PersonRepository {
	
	/**
	 * Returns the person identified by the id
	 * @param id
	 * @return the person
	 * @throws PersonNotFoundException if no person is found with specified id
	 */
	public Person getPerson(long id);
	
	/**
	 * Adds a new person
	 * @param person
	 */
	public void addPerson(Person person);
	
	/**
	 * Modifies an existing person
	 * @param person
	 * @throws PersonNotFoundException if no person is found with specified id
	 */
	public void updatePerson(Person person);
}
