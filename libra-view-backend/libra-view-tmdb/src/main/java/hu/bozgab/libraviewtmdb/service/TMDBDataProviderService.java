package hu.bozgab.libraviewtmdb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import hu.bozgab.libraviewtmdb.domain.Genre;
import hu.bozgab.libraviewtmdb.mapper.GenreMapper;
import hu.bozgab.libraviewtmdb.repository.GenreRepository;
import hu.bozgab.libraviewtmdb.tmdb.TMDBClient;
import hu.bozgab.libraviewtmdb.util.TMDBLanguage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
@Service
public class TMDBDataProviderService {

    private final GenreRepository genreRepository;

    private final TMDBClient tmdbClient;
    private final GenreMapper genreMapper;

    public Mono<Void> syncGenres() {
        // Parsing into a map to discard duplicates
        List<Genre> tmdbGenres = new ArrayList<>(Stream.concat(
                        Objects.requireNonNull(tmdbClient.genreMovieList(TMDBLanguage.EN.getCode())
                                        .getGenres())
                                .stream()
                                .map(genreMapper::toGenreEntity),
                        Objects.requireNonNull(tmdbClient.genreTvList(TMDBLanguage.EN.getCode())
                                        .getGenres())
                                .stream()
                                .map(genreMapper::toGenreEntity)
                )
                .collect(Collectors.toMap(Genre::getTmdbId, Function.identity(), (unused, replacement) -> replacement))
                .values());
        return genreRepository.findAll().collectList().flatMap(persistedGenres -> {
                    tmdbGenres.forEach(tmdbGenre -> persistedGenres
                            .stream()
                            .filter(persistedGenre -> persistedGenre.getTmdbId().equals(tmdbGenre.getTmdbId()))
                            .findAny().ifPresentOrElse(genre ->
                                    genre.setName(tmdbGenre.getName()), () -> persistedGenres.add(tmdbGenre)
                            ));
                    return genreRepository.saveAll(persistedGenres).then();
                }
        );
    }

}
