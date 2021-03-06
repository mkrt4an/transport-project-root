package com.mkrt4an.service;

import com.mkrt4an.dao.CityDao;
import com.mkrt4an.dao.DriverDao;
import com.mkrt4an.dao.OrderDao;
import com.mkrt4an.entity.DriverEntity;
import com.mkrt4an.exception.ServiceValidationException;
import com.mkrt4an.exception.TransportProjectException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
@Transactional
public class DriverService {

    private static final Logger log = Logger.getLogger(DriverService.class);

    private final DriverDao driverDao;
    private final CityDao cityDao;
    private final OrderDao orderDao;

    @Autowired
    public DriverService(DriverDao driverDao, CityDao cityDao, OrderDao orderDao) {
        this.driverDao = driverDao;
        this.cityDao = cityDao;
        this.orderDao = orderDao;
    }


    /**
     * Check if driver has empty fields that should not be empty.
     * @param driverEntity {@link DriverEntity}
     * @return Doesn't return anything -- throws exception if failed.
     * @throws ServiceValidationException
     */
    private void validateDriver(DriverEntity driverEntity) throws ServiceValidationException {
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
    public Integer addNew(String firstName, String lastName, Integer workedHours, Integer status, Integer cityId) throws TransportProjectException{
        DriverEntity driverEntity = new DriverEntity(firstName, lastName, workedHours, status, cityDao.findCityById(cityId));
        validateDriver(driverEntity);
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
                          String firstName, String lastName, Integer workedHours, Integer status, Integer cityId) throws TransportProjectException{

        DriverEntity driverEntity;
        driverEntity = driverDao.findDriverById(id);

        driverEntity.setFirstName(firstName);
        driverEntity.setLastName(lastName);
        driverEntity.setWorkedHours(workedHours);
        driverEntity.setStatus(status);
        driverEntity.setCurrentCity(cityDao.findCityById(cityId));

        validateDriver(driverEntity);
        return driverDao.updateDriver(driverEntity);
    }

    public Integer update(DriverEntity driverEntity) throws TransportProjectException {
        validateDriver(driverEntity);
        diffInHours(new Date(), orderDao.findOrderById(driverEntity.getOrder().getId()).getStartDate());
        return driverDao.updateDriver(driverEntity);
    }

    public Integer updateDriverStatusAndWorkHours(DriverEntity driverEntity) throws TransportProjectException {
        validateDriver(driverEntity);
        Float time = diffInHours(new Date(), orderDao.findOrderById(driverEntity.getOrder().getId()).getStartDate());
        Integer i = driverEntity.getWorkedHours();
        driverEntity.setWorkedHours(i + time.intValue());
        log.warn(driverEntity);
        return driverDao.updateDriver(driverEntity);
    }



    /**
     * Calculate difference in hours between two Date objects.
     * Will return negative if dates not in chronological order.
     *
     * @param earlierDate
     * @param laterDate
     * @return time in hours
     */
    public static float diffInHours(Date earlierDate, Date laterDate) {
        long resultMills = laterDate.getTime() - earlierDate.getTime();
//        float resultHours = (float) resultMills / 1000 / 60 / 60;
        float resultHours = (float) resultMills / 1000 / 60; // TODO: 24.11.2016
        return resultHours;
    }
}
