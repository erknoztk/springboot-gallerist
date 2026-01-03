package com.erkanozturk.mapper;

import org.mapstruct.Mapper;

import com.erkanozturk.dto.DtoCustomer;
import com.erkanozturk.dto.DtoCustomerIU;
import com.erkanozturk.model.Customer;

@Mapper(
          uses = AddressMapper.class,
          config = BaseMapperConfig.class
)
public interface CustomerMapper extends BaseMapper<DtoCustomer, Customer, DtoCustomerIU> {

}
