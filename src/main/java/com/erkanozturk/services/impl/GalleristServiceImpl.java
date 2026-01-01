package com.erkanozturk.services.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erkanozturk.dto.DtoAddress;
import com.erkanozturk.dto.DtoGallerist;
import com.erkanozturk.dto.DtoGalleristIU;
import com.erkanozturk.exception.BaseException;
import com.erkanozturk.exception.ErrorMessage;
import com.erkanozturk.exception.MessageType;
import com.erkanozturk.model.Address;
import com.erkanozturk.model.Gallerist;
import com.erkanozturk.repository.AddressRepository;
import com.erkanozturk.repository.GalleristRepository;
import com.erkanozturk.services.IGalleristService;

@Service
public class GalleristServiceImpl implements IGalleristService {

          @Autowired
          private GalleristRepository galleristRepository;

          @Autowired
          private AddressRepository addressRepository;

          private Gallerist createGallerist(DtoGalleristIU dtoGalleristIU){

                    Optional<Address> optionalAdress = addressRepository.findById(dtoGalleristIU.getAddressId());
                    if(optionalAdress.isEmpty()){

                              throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristIU.getAddressId().toString()));
                    }

                    Gallerist gallerist = new Gallerist();
                    gallerist.setCreateTime(new Date());

                    BeanUtils.copyProperties(dtoGalleristIU, gallerist); // objectler null geliyor
                    gallerist.setAddress(optionalAdress.get());

                    return gallerist;
          }

          @Override
          public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU) {
                    
                    DtoGallerist dtoGallerist = new DtoGallerist();
                    DtoAddress dtoAddress = new DtoAddress();

                    Gallerist savedGallerist = galleristRepository.save(createGallerist(dtoGalleristIU));
                    BeanUtils.copyProperties(savedGallerist, dtoGallerist); // objectler null geliyor
                    BeanUtils.copyProperties(savedGallerist.getAddress(), dtoAddress);

                    dtoGallerist.setAddress(dtoAddress);

                    return dtoGallerist;
          }

}
