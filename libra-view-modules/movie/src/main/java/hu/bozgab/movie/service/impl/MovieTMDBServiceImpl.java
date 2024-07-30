package hu.bozgab.movie.service.impl;

import hu.bozgab.movie.client.MovieTMDBJsonPlaceholderService;
import hu.bozgab.movie.service.MovieTMDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MovieTMDBServiceImpl implements MovieTMDBService {

    @Autowired
    MovieTMDBJsonPlaceholderService tmdbJsonPlaceholderService;

    @Override
    public ResponseEntity<String> findMovie(String searchKey) {
        return ResponseEntity.ok(tmdbJsonPlaceholderService.findMovie(
                "...", searchKey, false, "en-US", 1
        ));
    }
}
