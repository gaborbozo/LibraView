package hu.bozgab.libra_view.authentication.mapper;

import hu.bozgab.libra_view.authentication.domain.LibraUser;
import hu.bozgab.libra_view.authentication.dto.LibraUserDTO;

import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public abstract LibraUserDTO mapToUserDTO(LibraUser libraUser);

}
