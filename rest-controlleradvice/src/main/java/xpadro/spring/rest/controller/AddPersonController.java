package xpadro.spring.rest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import xpadro.spring.rest.model.Person;
import xpadro.spring.rest.repository.PersonRepository;

/**
 * Adds a new person
 * @author xpadro
 *
 */
@Controller
public class AddPersonController {
private static Logger logger = Logger.getLogger("main");
	
	@Autowired
	private PersonRepository personRepository;
	
	/**
	 * CREATE operation. Adds a new person
	 * @param person
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/persons", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void addPerson(@Valid @RequestBody Person person, HttpServletRequest request, HttpServletResponse response) {
		personRepository.addPerson(person);
		logger.info("Person added: "+person.getId());
		response.setHeader("Location", request.getRequestURL().append("/").append(person.getId()).toString());
	}
}
