package xpadro.spring.rest.repository;

import xpadro.spring.rest.model.User;

/**
 * Manages data from users
 * @author xpadro
 *
 */
public interface UserRepository {
	
	/**
	 * Returns the user identified by the id
	 * @param id
	 * @return the user
	 */
	public User getUser(long id);
	
	/**
	 * Inserts a user into the DB
	 * @param user
	 */
	public void insertUser(User user);
}
