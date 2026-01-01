package com.erkanozturk.services.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erkanozturk.dto.DtoAddress;
import com.erkanozturk.dto.DtoCar;
import com.erkanozturk.dto.DtoGallerist;
import com.erkanozturk.dto.DtoGalleristCar;
import com.erkanozturk.dto.DtoGalleristCarIU;
import com.erkanozturk.exception.BaseException;
import com.erkanozturk.exception.ErrorMessage;
import com.erkanozturk.exception.MessageType;
import com.erkanozturk.model.Car;
import com.erkanozturk.model.Gallerist;
import com.erkanozturk.model.GalleristCar;
import com.erkanozturk.repository.CarRepository;
import com.erkanozturk.repository.GalleristCarRepository;
import com.erkanozturk.repository.GalleristRepository;
import com.erkanozturk.services.IGalleristCarService;

@Service
public class GalleristCarServiceImpl implements IGalleristCarService{

          @Autowired
          private GalleristCarRepository galleristCarRepository;

          @Autowired
          private GalleristRepository galleristRepository;

          @Autowired
          private CarRepository carRepository;

          private GalleristCar creatGalleristCar (DtoGalleristCarIU dtoGalleristCarIU){

                    Optional<Gallerist> optionalGallerist = galleristRepository.findById(dtoGalleristCarIU.getGalleristId());
                    if(optionalGallerist.isEmpty()){
                              
                              throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getGalleristId().toString()));
                    }
                    Optional<Car> optionalCar = carRepository.findById(dtoGalleristCarIU.getCarId());
                    if(optionalCar.isEmpty()){
                              
                              throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getCarId().toString()));
                    }

                    GalleristCar galleristCar = new GalleristCar();
                    galleristCar.setCreateTime(new Date());
                    BeanUtils.copyProperties(dtoGalleristCarIU, galleristCar);

                    galleristCar.setGallerist(optionalGallerist.get());
                    galleristCar.setCar(optionalCar.get());
                    return galleristCar;
          }


          @Override
          public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
                   
                    DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
                    DtoGallerist dtoGallerist = new DtoGallerist();
                    DtoCar dtoCar = new DtoCar();

                    DtoAddress dtoAddress = new DtoAddress();

                    GalleristCar savedGalleristCar = galleristCarRepository.save(creatGalleristCar(dtoGalleristCarIU));

                    BeanUtils.copyProperties(savedGalleristCar, dtoGalleristCar);
                    BeanUtils.copyProperties(savedGalleristCar.getGallerist(), dtoGallerist);
                    // gallerist içinde address object null değerinde onu çözmek lazım
                    BeanUtils.copyProperties(savedGalleristCar.getGallerist().getAddress(), dtoAddress);
                    BeanUtils.copyProperties(savedGalleristCar.getCar(), dtoCar);

                    dtoGallerist.setAddress(dtoAddress);

                    dtoGalleristCar.setGallerist(dtoGallerist);
                    dtoGalleristCar.setCar(dtoCar);
                    
                    
                    return dtoGalleristCar;
          }

}
