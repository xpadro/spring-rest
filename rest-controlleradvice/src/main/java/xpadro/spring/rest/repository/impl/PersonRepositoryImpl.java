package xpadro.spring.rest.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import xpadro.spring.rest.exception.PersonNotFoundException;
import xpadro.spring.rest.model.Person;
import xpadro.spring.rest.repository.PersonRepository;

@Repository
public class PersonRepositoryImpl implements PersonRepository {
	private Map<Long, Person> persons;
	
	public PersonRepositoryImpl() {
		persons = new HashMap<Long, Person>();
		createDummyPersons();
	}

	@Override
	public Person getPerson(long id) {
		Person person = persons.get(id);
		if (person == null) {
			throw new PersonNotFoundException("No person found with id "+id);
		}
		
		return person;
	}
	
	@Override
	public void addPerson(Person person) {
		persons.put(person.getId(), person);
	}

	private void createDummyPersons() {
		Person person = new Person(1, "Xavi", "Padro", "Barcelona");
		persons.put(1l, person);
	}

	@Override
	public void updatePerson(Person person) {
		if (persons.get(person.getId()) == null) {
			throw new PersonNotFoundException("No person found with id "+person.getId());
		}
		persons.put(person.getId(), person);
	}
}
