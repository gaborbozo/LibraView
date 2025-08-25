package hu.bozgab.libraview.cineregistry.domain;

import hu.bozgab.libraview.cineregistry.generated.model.CinematicType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;


@Getter
@Setter
@Table("CINEMATIC")
public class Series extends Cinematic {

    public Series() {
        super();
        setDiscriminator(CinematicType.SERIES);
    }

}
