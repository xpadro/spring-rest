package xpadro.spring.rest.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import xpadro.spring.rest.model.User;
import xpadro.spring.rest.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {
	@Autowired
	private MongoOperations mongoOps;
	
	@Override
	public User getUser(long id) {
		return mongoOps.findById(id, User.class);
	}
	
	@Override
	public void insertUser(User user) {
		mongoOps.insert(user);
	}
}
