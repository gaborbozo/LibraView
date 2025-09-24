package hu.bozgab.libraview.cineregistry.tmdb.resource;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum TMDBLanguage {
    EN("en"),
    HU("hu");

    private final String code;
}
