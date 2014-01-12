package xpadro.spring.rest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import xpadro.spring.rest.model.Car;
import xpadro.spring.rest.model.User;
import xpadro.spring.rest.repository.CarRepository;
import xpadro.spring.rest.repository.UserRepository;

@Controller
public class MvcRestController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CarRepository carRepository;

	
	/**
	 * Returns the requested user by its id.
	 * The response will be converted to JSON
	 * @param id
	 * @return The requested user
	 */
	@RequestMapping(value="/users/{userId}", method=RequestMethod.GET)
	public @ResponseBody User getUser(@PathVariable("userId") long id) {
		return userRepository.getUser(id);
	}
	
	
	/**
	 * Returns the name of the requested user.
	 * The response will be converted to String
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/usernames/{userId}", method=RequestMethod.GET)
	public @ResponseBody String getUsername(@PathVariable("userId") long id) {
		StringBuilder result = new StringBuilder();
		User user = userRepository.getUser(id);
		return result.append(user.getName()).append(" ").append(user.getSurname()).toString();
	}
	
	/**
	 * Returns the requested car by its id.
	 * The response will be converted to XML
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/cars/{carId}", method=RequestMethod.GET)
	public @ResponseBody Car getCar(@PathVariable("carId") long id) {
		return carRepository.getCar(id);
	}
	
	/**
	 * Inserts a new user to the DB
	 * @param user
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/users", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void insertUser(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
		userRepository.insertUser(user);
		response.setHeader("Location", request.getRequestURL().append("/").append(user.getId()).toString());
	}
}
