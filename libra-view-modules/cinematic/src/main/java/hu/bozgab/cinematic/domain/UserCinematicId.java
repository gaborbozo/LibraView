package hu.bozgab.cinematic.domain;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserCinematicId implements Serializable {

    private Long userId;

    private Long cinematicId;

}


