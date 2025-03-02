package hu.bozgab.cinematic.dto;

import hu.bozgab.cinematic.dto.enums.CinematicType;
import hu.bozgab.shared.client.dto.IdRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false, exclude = {})
@ToString(callSuper = true, exclude = {})
public class CinematicRequest extends IdRequest {

    private CinematicType cinematic;

}
