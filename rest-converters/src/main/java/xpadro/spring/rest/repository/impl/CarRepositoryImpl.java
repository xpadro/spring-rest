package xpadro.spring.rest.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import xpadro.spring.rest.model.Car;
import xpadro.spring.rest.repository.CarRepository;

@Repository
public class CarRepositoryImpl implements CarRepository {
	@Autowired
	private MongoOperations mongoOps;

	@Override
	public Car getCar(long id) {
		return mongoOps.findById(1, Car.class);
	}
}
