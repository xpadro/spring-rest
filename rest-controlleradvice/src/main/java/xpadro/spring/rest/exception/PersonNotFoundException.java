package xpadro.spring.rest.exception;

/**
 * Exception to be thrown when trying to get a non existent person
 * @author xpadro
 *
 */
public class PersonNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -3845574518872003019L;

	public PersonNotFoundException() {
		super();
	}
	
	public PersonNotFoundException(String message) {
		super(message);
	}
}
