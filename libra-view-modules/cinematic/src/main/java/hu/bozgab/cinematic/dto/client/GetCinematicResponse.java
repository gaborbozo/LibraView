package hu.bozgab.cinematic.dto.client;

import java.util.ArrayList;
import java.util.List;

import hu.bozgab.cinematic.dto.CinematicDTO;
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
public class GetCinematicResponse {

    List<CinematicDTO> cinematics = new ArrayList<>();

}
