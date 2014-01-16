package xpadro.spring.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import xpadro.spring.web.model.Series;
import xpadro.spring.web.service.SeriesService;

/**
 * Handles data using a mongoDB database
 * @author xpadro
 *
 */
@Service
public class SeriesServiceImpl implements SeriesService {
	
	@Autowired
	private MongoOperations mongoOps;
	
	
	@Override
	public Series[] getAllSeries() {
		List<Series> seriesList = mongoOps.findAll(Series.class);
		return seriesList.toArray(new Series[0]);
	}

	@Override
	public Series getSeries(long id) {
		return mongoOps.findById(id, Series.class);
	}

	@Override
	public void insertSeries(Series series) {
		mongoOps.insert(series);
	}

	
	@Override
	public void deleteSeries(long id) {
		Query query = new Query();
		Criteria criteria = new Criteria("_id").is(id);
		query.addCriteria(criteria);
		
		mongoOps.remove(query, Series.class);
	}
}
