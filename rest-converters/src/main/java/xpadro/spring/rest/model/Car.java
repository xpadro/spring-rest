package xpadro.spring.rest.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="car")
public class Car implements Serializable {
	private static final long serialVersionUID = -4143762939567940616L;
	private long id;
	private String brand;
	private String model;
	
	public Car() { }
	
	public Car(long id, String brand, String model) {
		this.id = id;
		this.brand = brand;
		this.model = model;
	}
	
	@XmlElement
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@XmlElement
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	@XmlElement
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
}