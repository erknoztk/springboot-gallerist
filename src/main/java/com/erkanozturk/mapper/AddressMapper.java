package com.erkanozturk.mapper;

import org.mapstruct.Mapper;

import com.erkanozturk.dto.DtoAddress;
import com.erkanozturk.dto.DtoAddressIU;
import com.erkanozturk.model.Address;

@Mapper(
          config = BaseMapperConfig.class
)
public interface AddressMapper extends BaseMapper<DtoAddress, Address, DtoAddressIU> {

}
