package hu.bozgab.libraview.cineregistry.domain;

import hu.bozgab.libraview.cineregistry.generated.model.CinematicType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Getter
@Setter
@Table("USER_CINEMATIC_REFERENCE")
public class UserCinematicReference {

    @Column("USER_ID")
    private Long userId;

    @Column("CINEMATIC_REFERENCE_ID")
    private Long cinematicReferenceId;

    @Column("DISCRIMINATOR")
    private CinematicType discriminator;

}
