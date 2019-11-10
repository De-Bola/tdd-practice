package com.ttd.approach.service;

import com.ttd.approach.Exceptions.CarNotFoundException;
import com.ttd.approach.domain.Car;
import com.ttd.approach.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public CarService(CarRepository repository) {
        this.carRepository = repository;
    }

    @Cacheable("cars")
    public Car getCarDetails(String name) {
        Car car = carRepository.findByName(name);
        if(car == null) {throw new CarNotFoundException();}
        return car;
    }
}
