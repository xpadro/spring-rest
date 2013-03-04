package xpadro.spring.rest.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import xpadro.spring.rest.model.Person;

/**
 * Validates that a person is from a registered city
 * @author xpadro
 *
 */
public class PersonValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return clazz.equals(Person.class);
	}

	public void validate(Object target, Errors errors) {
		Person person = (Person) target;
		
		ValidationUtils.rejectIfEmpty(errors, "city", "city.empty");
		if (!"Barcelona".equals(person.getCity()) && !"Liverpool".equals(person.getCity())) {
			errors.rejectValue("city", "city.not.valid", "Invalid city: "+person.getCity());
		}
	}
}
