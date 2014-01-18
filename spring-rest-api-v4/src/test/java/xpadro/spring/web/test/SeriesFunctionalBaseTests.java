package xpadro.spring.web.test;

import static org.junit.Assert.fail;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xpadro.spring.web.model.Series;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
	"classpath:xpadro/spring/web/configuration/root-context.xml",
	"classpath:xpadro/spring/web/configuration/app-context.xml"})
public class SeriesFunctionalBaseTests {
	@Autowired
	private MongoOperations mongoOps;
	
	protected void initializeDatabase() {
		try {
			mongoOps.dropCollection("series");
			
			mongoOps.insert(new Series(1, "The walking dead", "USA", "Thriller"));
			mongoOps.insert(new Series(2, "Homeland", "USA", "Drama"));
		} catch (DataAccessResourceFailureException e) {
			fail("MongoDB instance is not running");
		}
	}
}
