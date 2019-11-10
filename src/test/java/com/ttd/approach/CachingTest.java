package com.ttd.approach;

import com.ttd.approach.domain.Car;
import com.ttd.approach.repository.CarRepository;
import com.ttd.approach.service.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase
public class CachingTest {

    @MockBean
    private CarRepository carRepository;

    @Autowired
    private CarService service;

    @Test
    public void caching() throws Exception {
        given(carRepository.findByName(anyString())).willReturn(new Car("prius", "hybrid"));

        service.getCarDetails("prius");
        service.getCarDetails("prius");

        verify(carRepository, times(1)).findByName("prius");
    }
}
