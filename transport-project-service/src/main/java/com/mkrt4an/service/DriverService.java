package com.mkrt4an.service;

import com.mkrt4an.dao.CityDao;
import com.mkrt4an.dao.DriverDao;
import com.mkrt4an.entity.DriverEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class DriverService {

    private final DriverDao driverDao;
    private final CityDao cityDao;

    @Autowired
    public DriverService(DriverDao driverDao, CityDao cityDao) {
        this.driverDao = driverDao;
        this.cityDao = cityDao;
    }

    //Add new
    public Integer addNew(String firstName, String lastName, Integer workedHours, Integer status, Integer cityId) {
        DriverEntity driverEntity = new DriverEntity(firstName, lastName, workedHours, status, cityDao.findCityById(cityId));
        return driverDao.createDriver(driverEntity);
    }

    //Find by id
    public DriverEntity findById(String id) {
        return driverDao.findDriverById(Integer.parseInt(id));
    }

    //Find by firstName and lastName
    public DriverEntity findByFirstNameAndLastName(String firstName, String lastName) {
        DriverEntity driverEntity;
        for(DriverEntity entity : driverDao.getAllDrivers()) {
            if (entity.getFirstName().equals(firstName) && entity.getLastName().equals(lastName)) {
                driverEntity = entity;
                return driverEntity;
            }
        }
        return null;
    }

    //Find by id
    public DriverEntity findById(Integer id) {
        return driverDao.findDriverById(id);
    }

    //Delete by id
    public Integer deleteById(String id){
        return driverDao.deleteDriver(driverDao.findDriverById(Integer.parseInt(id)));
    }


    // Find all drivers
    public List<DriverEntity> findAllDrivers() {
        return driverDao.getAllDrivers() ;
    }


    //    Update
    public Integer update(Integer id,
                          String firstName, String lastName, Integer workedHours, Integer status, Integer cityId) {

        DriverEntity driverEntity;
        driverEntity = driverDao.findDriverById(id);

        driverEntity.setFirstName(firstName);
        driverEntity.setLastName(lastName);
        driverEntity.setWorkedHours(workedHours);
        driverEntity.setStatus(status);
        driverEntity.setCurrentCity(cityDao.findCityById(cityId));

        return driverDao.updateDriver(driverEntity);
    }

    public Integer update(DriverEntity driverEntity){
        return driverDao.updateDriver(driverEntity);
    }
}
