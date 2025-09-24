package hu.bozgab.libraview.cineregistry.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import hu.bozgab.libraview.cineregistry.domain.Genre;
import hu.bozgab.libraview.cineregistry.mapper.GenreMapper;
import hu.bozgab.libraview.cineregistry.repository.GenreRepository;
import hu.bozgab.libraview.cineregistry.tmdb.TMDBClient;
import hu.bozgab.libraview.cineregistry.tmdb.resource.TMDBLanguage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
@Service
public class CinematicDataProviderService {

    private final GenreRepository genreRepository;

    private final TMDBClient tmdbClient;
    private final GenreMapper genreMapper;

    public Mono<Void> syncGenres() {
        // Parsing into a map to discard duplicates
        List<Genre> genres = new ArrayList<>(Stream.concat(
                        Objects.requireNonNull(tmdbClient.genreMovieList(TMDBLanguage.EN.getCode())
                                        .getGenres())
                                .stream()
                                .map(genreMapper::toGenreEntity),
                        Objects.requireNonNull(tmdbClient.genreTvList(TMDBLanguage.EN.getCode())
                                        .getGenres())
                                .stream()
                                .map(genreMapper::toGenreEntity)
                )
                .collect(Collectors.toMap(Genre::getReferenceId, Function.identity(), (unused, replacement) -> replacement))
                .values());
        return genreRepository.findAll().collectList().flatMap(persistedGenres -> {
                    genres.forEach(genre ->
                            persistedGenres.stream()
                                    .filter(persistedGenre -> persistedGenre.getReferenceId().equals(genre.getReferenceId()))
                                    .findAny().ifPresentOrElse(updateGenre ->
                                            updateGenre.setName(genre.getName()), () -> persistedGenres.add(genre)
                                    ));
                    return genreRepository.saveAll(persistedGenres).then();
                }
        );
    }

}
