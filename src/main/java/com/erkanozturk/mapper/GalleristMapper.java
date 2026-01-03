package com.erkanozturk.mapper;

import org.mapstruct.Mapper;

import com.erkanozturk.dto.DtoGallerist;
import com.erkanozturk.dto.DtoGalleristIU;
import com.erkanozturk.model.Gallerist;

@Mapper(
          uses = AddressMapper.class,
          config = BaseMapperConfig.class
)
public interface GalleristMapper extends BaseMapper<DtoGallerist, Gallerist, DtoGalleristIU> {

}
