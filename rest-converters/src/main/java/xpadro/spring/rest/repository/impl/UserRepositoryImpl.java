package xpadro.spring.rest.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import xpadro.spring.rest.model.User;
import xpadro.spring.rest.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {
	private Map<Long, User> users;
	
	public UserRepositoryImpl() {
		users = new HashMap<Long, User>();
		createDummyUsers();
	}

	@Override
	public User getUser(long id) {
		return users.get(id);
	}

	private void createDummyUsers() {
		User user = new User(1, "Xavi", "Padro");
		users.put(1l, user);
	}
}
