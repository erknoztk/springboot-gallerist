package com.erkanozturk.mapper;

import org.mapstruct.Mapper;

import com.erkanozturk.dto.DtoCar;
import com.erkanozturk.dto.DtoCarIU;
import com.erkanozturk.model.Car;

@Mapper(
          config = BaseMapperConfig.class
)
public interface CarMapper extends BaseMapper<DtoCar, Car, DtoCarIU > {

}
