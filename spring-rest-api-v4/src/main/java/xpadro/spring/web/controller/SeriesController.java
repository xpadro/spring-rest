package xpadro.spring.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import xpadro.spring.web.model.Series;
import xpadro.spring.web.service.SeriesService;

@Controller
@RequestMapping(value="/series")
public class SeriesController {
	
	private SeriesService seriesService;
	
	@Autowired
	public SeriesController(SeriesService seriesService) {
		this.seriesService = seriesService;
	}
	
	
	/**
	 * Returns all registered series
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public Series[] getAllSeries() {
		return seriesService.getAllSeries();
	}
	
	/**
	 * Returns the requested series by its id.
	 * @param id
	 * @return The requested series
	 */
	@RequestMapping(value="/{seriesId}", method=RequestMethod.GET)
	public ResponseEntity<Series> getSeries(@PathVariable("seriesId") long id) {
		Series series = seriesService.getSeries(id);
		
		if (series == null) {
			return new ResponseEntity<Series>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Series>(series, HttpStatus.OK);
	}
	
	/**
	 * Inserts a new series to the DB
	 * @param series
	 * @param request
	 * @param response
	 */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void insertSeries(@RequestBody Series series, HttpServletRequest request, HttpServletResponse response) {
		seriesService.insertSeries(series);
		response.setHeader("Location", request.getRequestURL().append("/").append(series.getId()).toString());
	}
	
	/**
	 * Deletes the specified series
	 * @param id
	 */
	@RequestMapping(value="/{seriesId}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteSeries(@PathVariable("seriesId") long id) {
		seriesService.deleteSeries(id);
	}
}
