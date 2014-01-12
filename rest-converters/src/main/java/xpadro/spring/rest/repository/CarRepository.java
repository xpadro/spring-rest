package xpadro.spring.rest.repository;

import xpadro.spring.rest.model.Car;


/**
 * Manages data from cars
 * @author xpadro
 *
 */
public interface CarRepository {
	
	/**
	 * Returns the car identified by the id
	 * @param id
	 * @return the car
	 */
	public Car getCar(long id);
}
