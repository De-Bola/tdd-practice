package com.ttd.approach.service;

import com.ttd.approach.Exceptions.CarNotFoundException;
import com.ttd.approach.domain.Car;
import com.ttd.approach.repository.CarRepository;
import com.ttd.approach.service.CarService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

    private CarService carService;

    @Mock
    private CarRepository carRepository;

    @Before
    public void setUp() throws Exception{
        carService = new CarService(carRepository);
    }

    @Test
    public void getCarDetailsShouldReturnCarInfo() {

        given(carRepository.findByName("prius")).willReturn(new Car("prius","hybrid"));

        Car car = carService.getCarDetails("prius");

        Assertions.assertThat(car.getName()).isEqualTo("prius");
        Assertions.assertThat(car.getType()).isEqualTo("hybrid");
    }

    @Test(expected = CarNotFoundException.class)
    public void getCarDetailsWhenCarNotFound() throws Exception {
        given(carRepository.findByName("prius")).willReturn(null);

        carService.getCarDetails("prius");
    }
}
