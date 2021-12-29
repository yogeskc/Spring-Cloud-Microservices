package io.javabrains.moviecatalogservice;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/catalog")

public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;
	
//	@Autowired
//	private WebClient.Builder webClientBuilder; 
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

		// get all rated movie ID
		UserRating ratings = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/" + userId,
				UserRating.class);

//		Movie movie = webClientBuilder.build()
//		.get()
//		.uri("http://localhost:8082/movies/"+rating.getMovieId())
//		.retrieve()
//		.bodyToMono(Movie.class)
//		.block();
		
		return ratings.getUserRating().stream().map(rating -> {
			// For each moveID call movie info service and get details
			// url,class of payload
			Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
			// Put them all together
			return new CatalogItem(movie.getName(), "Desc", rating.getRating());
		})

				.collect(Collectors.toList());

		// RestTemplate restTemplate = new RestTemplate();



	}
}
