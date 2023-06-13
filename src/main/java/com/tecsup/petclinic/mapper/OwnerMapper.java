package com.tecsup.petclinic.mapper;

import com.tecsup.petclinic.domain.OwnerTO;
import com.tecsup.petclinic.domain.PetTO;
import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.entities.Pet;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 *
 * @author Jacko_Tinoco
 */

@Mapper(componentModel = "spring", nullValueMappingStrategy =  NullValueMappingStrategy.RETURN_DEFAULT)
public interface OwnerMapper {

    OwnerMapper INSTANCE = Mappers.getMapper(OwnerMapper.class);

    Owner toOwner(OwnerTO ownerTO);

    OwnerTO toOwnerTO(Owner owner);
    List<OwnerTO> toOwnerTOList(List<Owner> ownerList);

    List<Owner> toOwnerList(List<OwnerTO> ownerTOList);

}
