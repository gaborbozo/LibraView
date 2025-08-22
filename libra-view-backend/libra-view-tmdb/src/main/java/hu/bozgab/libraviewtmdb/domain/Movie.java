package hu.bozgab.libraviewtmdb.domain;

import hu.bozgab.LibraViewTMDB.generated.model.CinematicType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;


@Getter
@Setter
@Table("CINEMATIC")
public class Movie extends Cinematic {

    public Movie() {
        super();
        setDiscriminator(CinematicType.MOVIE);
    }

}
