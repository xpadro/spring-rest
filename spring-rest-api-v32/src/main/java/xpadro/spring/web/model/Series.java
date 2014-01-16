package xpadro.spring.web.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="series")
public class Series implements Serializable {
	private static final long serialVersionUID = 2646831820313826686L;

	private long id;
	private String name;
	private String country;
	private String genre;
	
	public Series() {}
	
	public Series(long id, String name, String country, String genre) {
		this.id = id;
		this.name = name;
		this.country = country;
		this.genre = genre;
	}

	@XmlElement
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@XmlElement
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	
	
	/*
	private Series(SeriesBuilder builder) {
		id = builder.id;
		name = builder.name;
		country = builder.country;
		genre = builder.genre;
	}
	
	

	public long getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public String getCountry() {
		return country;
	}


	public String getGenre() {
		return genre;
	}


	public static class SeriesBuilder {
		private final long id;
		private final String name;
		
		private String country;
		private String genre;
		
		
		public SeriesBuilder(long id, String name) {
			this.id = id;
			this.name = name;
		}
		
		
		public SeriesBuilder withCountry(String country) {
			this.country = country;
			return this;
		}
		
		public SeriesBuilder withGenre(String genre) {
			this.genre = genre;
			return this;
		}
		
		public Series build() {
			return new Series(this);
			}
	}
	*/
}
