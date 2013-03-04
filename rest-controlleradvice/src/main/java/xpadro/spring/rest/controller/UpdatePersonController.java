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
 * Modifies an existing person
 * @author xpadro
 *
 */
@Controller
public class UpdatePersonController {
private static Logger logger = Logger.getLogger("main");
	
	@Autowired
	private PersonRepository personRepository;

	/**
	 * UPDATE operation. Modifies an existing person
	 * @param person
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/persons", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	public void updatePerson(@Valid @RequestBody Person person, HttpServletRequest request, HttpServletResponse response) {
		personRepository.updatePerson(person);
		logger.info("Person updated: "+person.getId());
		response.setHeader("Location", request.getRequestURL().append("/").append(person.getId()).toString());
	}
}
