package hu.bozgab.movie.service;

import org.springframework.http.ResponseEntity;

public interface MovieTMDBService {

    ResponseEntity<String> findMovie(String searchKey);

}
