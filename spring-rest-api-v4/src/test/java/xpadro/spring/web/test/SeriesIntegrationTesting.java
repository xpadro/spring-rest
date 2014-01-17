package xpadro.spring.web.test;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.endsWith;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.http.MediaType;

import xpadro.spring.web.model.Series;
import xpadro.spring.web.service.SeriesService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={
	"classpath:xpadro/spring/web/test/configuration/test-root-context.xml",
	"classpath:xpadro/spring/web/configuration/app-context.xml"})
public class SeriesIntegrationTesting {
	private static final String BASE_URI = "/series";
	
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@Autowired
	private SeriesService seriesService;
	
	
	@Before
    public void setUp() {
		reset(seriesService);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		when(seriesService.getAllSeries()).thenReturn(new Series[]{
				new Series(1, "The walking dead", "USA", "Thriller"), 
				new Series(2, "Homeland", "USA", "Drama")});
		
		when(seriesService.getSeries(1L)).thenReturn(new Series(1, "Fringe", "USA", "Thriller"));
	}
	
	
	@Test
	public void getAllSeries() throws Exception {
		mockMvc.perform(get(BASE_URI)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].name", is("The walking dead")))
				.andExpect(jsonPath("$[0].country", is("USA")))
				.andExpect(jsonPath("$[0].genre", is("Thriller")))
				.andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].name", is("Homeland")))
				.andExpect(jsonPath("$[1].country", is("USA")))
				.andExpect(jsonPath("$[1].genre", is("Drama")));
		
		verify(seriesService, times(1)).getAllSeries();
        verifyZeroInteractions(seriesService);
	}
	
	@Test
	public void getJsonSeries() throws Exception {
		mockMvc.perform(get(BASE_URI + "/{seriesId}", 1L)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Fringe")))
                .andExpect(jsonPath("$.country", is("USA")))
                .andExpect(jsonPath("$.genre", is("Thriller")));
		
		verify(seriesService, times(1)).getSeries(1L);
        verifyZeroInteractions(seriesService);
	}
	
	@Test
	public void getXmlSeries() throws Exception {
		mockMvc.perform(get(BASE_URI + "/{seriesId}", 1L)
				.accept(MediaType.APPLICATION_XML))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_XML))
				.andExpect(xpath("/series/id").string("1"))
				.andExpect(xpath("/series/name").string("Fringe"))
				.andExpect(xpath("/series/country").string("USA"))
				.andExpect(xpath("/series/genre").string("Thriller"));
		
		
		verify(seriesService, times(1)).getSeries(1L);
        verifyZeroInteractions(seriesService);
	}
	
	@Test
	public void getNotFoundSeries() throws Exception {
		mockMvc.perform(get(BASE_URI + "/{seriesId}", 5L)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
		
		verify(seriesService, times(1)).getSeries(5L);
        verifyZeroInteractions(seriesService);
	}
	
	@Test
	public void insertNewSeries() throws Exception {
		mockMvc.perform(post(BASE_URI)
				.content("{ \"id\": 3, \"name\": \"mySeries\", \"country\": \"England\", \"genre\": \"sci-fi\" } }")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", endsWith("/series/3")));
		
		verify(seriesService, times(1)).insertSeries(any(Series.class));
        verifyZeroInteractions(seriesService);
	}
}
