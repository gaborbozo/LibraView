package hu.bozgab.movie.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@EqualsAndHashCode(callSuper = false, exclude = {})
@ToString(callSuper = true, exclude = {})
@Entity
@DiscriminatorValue("MOVIE")
public class Movie extends Cinematic {

}
