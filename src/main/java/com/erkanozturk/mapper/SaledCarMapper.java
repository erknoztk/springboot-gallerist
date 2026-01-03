package com.erkanozturk.mapper;

import org.mapstruct.Mapper;

import com.erkanozturk.dto.DtoSaledCar;
import com.erkanozturk.dto.DtoSaledCarIU;
import com.erkanozturk.model.SaledCar;

@Mapper(
          uses = {
                    CustomerMapper.class,
                    GalleristMapper.class,
                    CarMapper.class
          },
          config = BaseMapperConfig.class
)
public interface SaledCarMapper extends BaseMapper<DtoSaledCar, SaledCar, DtoSaledCarIU> {

}
