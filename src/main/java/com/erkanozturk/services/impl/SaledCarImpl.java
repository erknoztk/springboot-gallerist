package com.erkanozturk.services.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erkanozturk.dto.CurrencyRateResponse;
import com.erkanozturk.dto.DtoAccount;
import com.erkanozturk.dto.DtoAddress;
import com.erkanozturk.dto.DtoCar;
import com.erkanozturk.dto.DtoCustomer;
import com.erkanozturk.dto.DtoGallerist;
import com.erkanozturk.dto.DtoSaledCar;
import com.erkanozturk.dto.DtoSaledCarIU;
import com.erkanozturk.enums.CarStatusType;
import com.erkanozturk.exception.BaseException;
import com.erkanozturk.exception.ErrorMessage;
import com.erkanozturk.exception.MessageType;
import com.erkanozturk.model.Car;
import com.erkanozturk.model.Customer;
import com.erkanozturk.model.SaledCar;
import com.erkanozturk.repository.CarRepository;
import com.erkanozturk.repository.CustomerRepository;
import com.erkanozturk.repository.GalleristRepository;
import com.erkanozturk.repository.SaledCarRepository;
import com.erkanozturk.services.ICurrecyRateService;
import com.erkanozturk.services.ISaledCarService;

@Service
public class SaledCarImpl  implements ISaledCarService{

          @Autowired
          private SaledCarRepository saledCarRepository;

          @Autowired
          private CustomerRepository customerRepository;

          @Autowired
          private CarRepository carRepository;

          @Autowired
          private GalleristRepository galleristRepository;

          @Autowired
          private ICurrecyRateService currecyRateService;


          private BigDecimal remaningCustomerAmount(Customer customer, Car car){

                    BigDecimal customerUSDAmount = convertCustomerAmountToUSD(customer);
                    BigDecimal remaningCustomerUSDAmount = customerUSDAmount.subtract(car.getPrice());

                    CurrencyRateResponse currencyRateResponse = currecyRateService.getCurrencyRate("31-12-2025", "31-12-2025");
                    BigDecimal usd =  new BigDecimal(currencyRateResponse.getItems().get(0).getUsd());

                    return remaningCustomerUSDAmount.multiply(usd);
                    
          }

          public boolean checkCarStatus(Long carId){

                   Optional<Car> optCar =  carRepository.findById(carId);
                   if(optCar.isPresent() && optCar.get().getCarStatusType().name().equals(CarStatusType.SALABLE.name())){

                              return true;
                   }
                   return false;

          }


          public BigDecimal convertCustomerAmountToUSD(Customer customer){

                    // güncel usd değeri
                    CurrencyRateResponse  currencyRateResponse = currecyRateService.getCurrencyRate("31-12-2025", "31-12-2025");
                    BigDecimal usd = new BigDecimal(currencyRateResponse.getItems().get(0).getUsd());

                    BigDecimal customerUSDAmount = customer.getAccount().getAmount().divide(usd, 2, RoundingMode.HALF_UP); 
                    return customerUSDAmount;
          }

          public boolean checkAmount(DtoSaledCarIU dtoSaledCarIU){

                    Optional<Customer> optCustomer = customerRepository.findById(dtoSaledCarIU.getCustomerId());
                    if(optCustomer.isEmpty()){

                              throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCustomerId().toString()));
                    }

                    Optional<Car> optCar = carRepository.findById(dtoSaledCarIU.getCarId());
                    if(optCar.isEmpty()){
                              
                              throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCarId().toString()));
                    }

                    // fiyat kontrol  EKLE TL USD kontrol ona göre işlem
                    BigDecimal customerUSDAmount= convertCustomerAmountToUSD(optCustomer.get());
                    // 37.000             35.000 == 0 > 1 < -1
                    if(customerUSDAmount.compareTo(optCar.get().getPrice())==0 || customerUSDAmount.compareTo(optCar.get().getPrice())>0){

                              return true;
                    }
                    return false;
          }

          private SaledCar creatSaledCar(DtoSaledCarIU dtoSaledCarIU){

                    SaledCar saledCar = new SaledCar();
                    saledCar.setCreateTime(new Date());

                    saledCar.setCustomer(customerRepository.findById(dtoSaledCarIU.getCustomerId()).orElse(null));
                    saledCar.setGallerist(galleristRepository.findById(dtoSaledCarIU.getGalleristId()).orElse(null));
                    saledCar.setCar(carRepository.findById(dtoSaledCarIU.getCarId()).orElse(null));

                    return saledCar;
          }

          @Override
          public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU) {


                    
                    if(!checkAmount(dtoSaledCarIU)){

                              throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH, ""));
                    }
                    if(!checkCarStatus(dtoSaledCarIU.getCarId())){
                              throw new BaseException(new ErrorMessage(MessageType.CAR_STATUS_IS_ALREADY_SALED, dtoSaledCarIU.getCarId().toString()));

                    }

                    SaledCar savedSaledCar = saledCarRepository.save(creatSaledCar(dtoSaledCarIU));

                    Car car =  savedSaledCar.getCar();
                    car.setCarStatusType(CarStatusType.SALED);
                    carRepository.save(car);

                    Customer customer = savedSaledCar.getCustomer();
                    customer.getAccount().setAmount(remaningCustomerAmount(customer, car));
                    customerRepository.save(customer);
          
                    return toDTO(savedSaledCar);
          }

          public DtoSaledCar toDTO(SaledCar saledCar){

                    DtoSaledCar  dtoSaledCar = new DtoSaledCar();
                    DtoCustomer dtoCustomer = new DtoCustomer();
                    DtoGallerist dtoGallerist = new DtoGallerist();
                    DtoCar dtoCar = new DtoCar();

                    DtoAddress dtoCustomerAddress = new DtoAddress();
                    DtoAddress dtoGalleristAddress = new DtoAddress();
                    DtoAccount dtoAccount = new DtoAccount();
                    
                    BeanUtils.copyProperties(saledCar, dtoSaledCar);
                    BeanUtils.copyProperties(saledCar.getCustomer(), dtoCustomer);
                    BeanUtils.copyProperties(saledCar.getCustomer().getAddress(), dtoCustomerAddress);
                    BeanUtils.copyProperties(saledCar.getCustomer().getAccount(), dtoAccount);
                    dtoCustomer.setAddress(dtoCustomerAddress);
                    dtoCustomer.setAccount(dtoAccount);
                    BeanUtils.copyProperties(saledCar.getGallerist(), dtoGallerist);
                    BeanUtils.copyProperties(saledCar.getGallerist().getAddress(), dtoGalleristAddress);
                    dtoGallerist.setAddress(dtoGalleristAddress);
                    BeanUtils.copyProperties(saledCar.getCar(), dtoCar);

                    dtoSaledCar.setCustomer(dtoCustomer);
                    dtoSaledCar.setGallerist(dtoGallerist);
                    dtoSaledCar.setCar(dtoCar);
                    return dtoSaledCar;

          }

}
