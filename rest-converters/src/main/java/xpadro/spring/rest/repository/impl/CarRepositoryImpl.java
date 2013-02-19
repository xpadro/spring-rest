package xpadro.spring.rest.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import xpadro.spring.rest.model.Car;
import xpadro.spring.rest.repository.CarRepository;

@Repository
public class CarRepositoryImpl implements CarRepository {
	private Map<Long, Car> cars;
	
	public CarRepositoryImpl() {
		cars = new HashMap<Long, Car>();
		createDummyCars();
	}

	@Override
	public Car getCar(long id) {
		return cars.get(id);
	}

	private void createDummyCars() {
		Car car = new Car(1, "Ferrari", "GTO");
		cars.put(1l, car);
	}
}
