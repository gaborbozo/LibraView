package hu.bozgab.cinematic.dto.integration.configuration;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false, exclude = {})
@ToString(callSuper = true, exclude = {})
public class TMDBImageDetails {

    private String base_url;

    private String secure_base_url;

    @Builder.Default
    List<String> backdrop_sizes = new ArrayList<>();

    @Builder.Default
    List<String> logo_sizes = new ArrayList<>();

    @Builder.Default
    List<String> poster_sizes = new ArrayList<>();

    @Builder.Default
    List<String> profile_sizes = new ArrayList<>();

    @Builder.Default
    List<String> still_sizes = new ArrayList<>();

}
