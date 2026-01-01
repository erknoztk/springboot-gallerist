package com.erkanozturk.services.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erkanozturk.dto.DtoAddress;
import com.erkanozturk.dto.DtoAddressIU;
import com.erkanozturk.model.Address;
import com.erkanozturk.repository.AddressRepository;
import com.erkanozturk.services.IAddressService;

@Service
public class AddressService implements IAddressService{

          @Autowired
          private AddressRepository addressRepository;

          private Address creatAddress(DtoAddressIU dtoAddressIU){
                    
                    Address address = new Address();
                    address.setCreateTime(new Date());

                    BeanUtils.copyProperties(dtoAddressIU, address);

                    return address;
          }

          @Override
          public DtoAddress saveAddress(DtoAddressIU dtoAddressIU) {
                    
                    DtoAddress dtoAddress = new DtoAddress();
                    Address savedAddress = addressRepository.save(creatAddress(dtoAddressIU));

                    BeanUtils.copyProperties(savedAddress, dtoAddress);
                    return dtoAddress;
          }

          
}
