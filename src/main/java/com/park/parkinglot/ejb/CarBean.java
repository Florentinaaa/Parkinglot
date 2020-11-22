/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.park.parkinglot.ejb;

import com.park.parkinglot.common.CarDetails;
import com.park.parkinglot.entity.Car;
import com.park.parkinglot.entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Florri
 */
@Stateless
public class CarBean {
    
    private static final Logger LOG = Logger.getLogger(CarBean.class.getName());
    @PersistenceContext
    private EntityManager em ;
    private List<CarDetails> copyCarsToDetails;
    
    public List<CarDetails> getAllCars() {
      LOG.info("getAllCars");
         try {
      Query query = em.createQuery("SELECT c FROM Car c");
      List<Car> cars = (List<Car>) query.getResultList();
       return copyCarsToDetails;
       } catch (Exception ex) {
            throw new EJBException(ex);
}
}
private List<CarDetails> copyCarsToDetails(List<Car> cars) {
    List<CarDetails>detailsList = new ArrayList<>();
    for(Car car : cars) {
        CarDetails carDetails = new CarDetails(car.getId(),
                car.getLicensePlate(),
                car.getParkingSpot(),
                car.getUser().getUsername());
        detailsList.add(carDetails);
    }
    return detailsList;
}

    public void createCar(String licensePlate, String parkingSpot, Integer userId) {
        LOG.info("createCar");
    Car car=new Car();
    car.setLicensePlate(licensePlate);
    car.setParkingSpot(parkingSpot);

    User user=em.find(User.class,userId);
    user.getCars().add(car);
    car.setUser(user);
    em.persist(car);
    }

    public void deleteCarsByIds(List<Integer> carIds) {
        LOG.info("deleteCarsByIds");
       for(Integer id:carIds){
           Car car=em.find(Car.class, id);
           em.remove(car);
    }
    }

    public void updateCar(int carId, String licensePlate, String parkingSpot, int userId) {
        LOG.info("updateCar");
        Car car=em.find(Car.class, carId);
        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);

        User oldUser=car.getUser();
        oldUser.getCars().remove(car);

        User user=em.find(User.class, userId);
        user.getCars().add(car);
        car.setUser(user);
    }

    public CarDetails findById(Integer carId){
    Car car=em.find(Car.class,carId);
    return new CarDetails(car.getId(),car.getLicensePlate(),car.getParkingSpot(),car.getUser().getUsername());
}
 
}
