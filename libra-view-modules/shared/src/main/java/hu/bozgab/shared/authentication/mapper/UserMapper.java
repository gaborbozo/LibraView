package hu.bozgab.shared.authentication.mapper;

import hu.bozgab.shared.authentication.domain.LibraUser;
import hu.bozgab.shared.authentication.dto.LibraUserDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public abstract LibraUserDTO mapToUserDTO(LibraUser libraUser);

}
