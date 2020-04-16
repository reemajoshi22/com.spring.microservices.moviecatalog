package com.spring.microservices.moviecatalog.com.spring.microservices.moviecatalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    /**
     * learning web client basic
     */



    @Autowired // @Autowired is a consumer and @bean is producer, so by bean we are telling to
            // spring create a bean and save it somewhere
    RestTemplate restTemplate;// this is done to avoid the template being created
    // everytime when we are running get template // @autowired says
    // if anybody has a bean anywhere the return here.

   /* @Autowired
    private WebClient.Builder webBuilder;
*/

    // get all rated movie IDs

    // for each movie ID, call movie Info Service and get details

    // put them all together


    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        //RestTemplate restTemplate=new RestTemplate();

       /* List<Rating> ratings = Arrays.asList(new Rating("1234", 4),
                new Rating("5678", 5));*/

        UserRating ratings=restTemplate.getForObject("http://localhost:8083/ratingsdata/users/"+userId,
                UserRating.class);
        return ratings.getUserRating().stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
            // example of using web client
            /*Movie movie=webBuilder.build()
                    .get()
                    .uri("http://localhost:8082/movies/" + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)// reactive way of saying getting object back, mono is kind of promise
                    .block();*/
            return new CatalogItem(movie.getName(), "Desc", rating.getRating());
        })
                .collect(Collectors.toList());

        // return Collections.singletonList(new CatalogItem("Transformers", "Test", 4));
    }
}
