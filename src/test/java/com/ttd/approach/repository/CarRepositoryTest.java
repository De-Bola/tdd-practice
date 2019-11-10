package com.ttd.approach.repository;

import com.ttd.approach.domain.Car;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private CarRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void getCarTestReturnsCarDetails() throws Exception {

        Car savedCar = new Car("prius", "hybrid");
        entityManager.persist(savedCar);
        entityManager.flush();

        Car car = repository.findByName(savedCar.getName());
        Assertions.assertThat(car.getName()).isEqualTo(savedCar.getName());
    }

    @Test
    public void getCarTestReturnsCarDetails1() throws Exception {

        Car savedCar = entityManager.persistFlushFind(new Car("prius", "hybrid"));

        Car car = repository.findByName("prius");
        Assertions.assertThat(car.getName()).isEqualTo(savedCar.getName());
        Assertions.assertThat(car.getType()).isEqualTo(savedCar.getType());
    }
}