package com.mkrt4an.service;

import com.mkrt4an.dao.CityDao;
import com.mkrt4an.dao.DriverDao;
import com.mkrt4an.entity.DriverEntity;
import com.mkrt4an.exception.ServiceValidationException;
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


    /**
     * Check if driver has empty fields that should not be empty.
     * @param driverEntity {@link DriverEntity}
     * @return Doesn't return anything -- throws exception if failed.
     * @throws ServiceValidationException
     */
    private void validateDriverForEmptyFields(DriverEntity driverEntity) throws ServiceValidationException {
        if (driverEntity.getFirstName() == null || driverEntity.getFirstName().isEmpty()) {
            throw new ServiceValidationException("FirstName is not set or empty.");

        } else if (driverEntity.getLastName().isEmpty()) {
            throw new ServiceValidationException("LastName size is not set or empty.");

        } else if (driverEntity.getWorkedHours() == null || driverEntity.getWorkedHours() < 0) {
            throw new ServiceValidationException("WorkedHours is not set or can't be 0 or negative.");

        } else if (driverEntity.getStatus() < 0 || driverEntity.getStatus() > 2) {
            throw new ServiceValidationException("Status is not less 0 or greater 2.");

        } else if (driverEntity.getCurrentCity() == null) {
            throw new ServiceValidationException("City is not set.");
        }
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
