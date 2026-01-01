package com.erkanozturk.services.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erkanozturk.dto.DtoCar;
import com.erkanozturk.dto.DtoCarIU;
import com.erkanozturk.model.Car;
import com.erkanozturk.repository.CarRepository;
import com.erkanozturk.services.ICarService;

@Service
public class CarService implements ICarService{


          @Autowired
          private CarRepository carRepository;

          private Car createCar(DtoCarIU dtoCarIU){

                    Car car = new Car();
                    car.setCreateTime(new Date());
                    BeanUtils.copyProperties(dtoCarIU, car);

                    return car;
          }

          @Override
          public DtoCar saveCar(DtoCarIU dtoCarIU) {
                    
                    DtoCar dtoCar = new DtoCar();
                    Car savedCar = carRepository.save(createCar(dtoCarIU));

                    BeanUtils.copyProperties(savedCar, dtoCar);

                    return dtoCar;
          }

}
