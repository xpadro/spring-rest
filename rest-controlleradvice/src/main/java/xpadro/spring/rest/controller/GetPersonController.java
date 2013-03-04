package xpadro.spring.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import xpadro.spring.rest.model.Person;
import xpadro.spring.rest.repository.PersonRepository;

/**
 * Returns an existing person
 * @author xpadro
 *
 */
@Controller
public class GetPersonController {
	@Autowired
	private PersonRepository personRepository;
	
	/**
	 * READ operation. Returns an existing person
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/persons/{personId}", method=RequestMethod.GET)
	public @ResponseBody Person getPerson(@PathVariable("personId") long id) {
		return personRepository.getPerson(id);
	}
}
