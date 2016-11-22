package com.mkrt4an.service;

import com.mkrt4an.dao.*;
import com.mkrt4an.entity.DriverEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import com.mkrt4an.entity.RoutePointEntity;

import javax.inject.Inject;
import java.util.List;

//import static com.mkrt4an.utils.EntityManagerHelper.getEntityManager;

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
//        CargoDao cargoDao = new CargoDao(getEntityManager());
        DriverEntity driverEntity = new DriverEntity(firstName, lastName, workedHours, status, cityDao.findCityById(cityId));
        return driverDao.createDriver(driverEntity);
    }

    //Find by id
    public DriverEntity findById(String id) {
//        DriverDao driverDao = new DriverDao(getEntityManager());
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
//        DriverDao driverDao = new DriverDao(getEntityManager());
        return driverDao.findDriverById(id);
    }

    //Delete by id
    public Integer deleteById(String id){
//        DriverDao driverDao = new DriverDao(getEntityManager());
        return driverDao.deleteDriver(driverDao.findDriverById(Integer.parseInt(id)));
    }


    // Find all drivers
    public List<DriverEntity> findAllDrivers() {
//        DriverDao driverDao = new DriverDao(getEntityManager());
        return driverDao.getAllDrivers() ;
    }


    //Get driver info
//    public String getDriverInfo(Integer driverId) {
////        DriverDao driverDao = new DriverDao(getEntityManager());
//
//        DriverEntity driverEntity  = driverDao.findDriverById(driverId);
//
//        StringBuffer codriverList = new StringBuffer();
//
//        List<DriverEntity> driverEntityList = driverEntity.getOrder().getDriverList();
//
//        for (DriverEntity entity : driverEntityList) {
//            if (entity == driverEntity) {continue;}
//            codriverList.append(entity.getId());
//            codriverList.append(", ");
//        }
//
//        codriverList.deleteCharAt(codriverList.length() - 2);
//
//
//        StringBuffer routePointList = new StringBuffer();
//        for (RoutePointEntity entity : driverEntity.getOrder().getRpList()) {
//            routePointList.append(entity.getOrdinal() + " - " + entity.getCity() + ", ");
//        }
//
//        routePointList.deleteCharAt(routePointList.length() - 2);
//
//        return "id: " + driverEntity.getId() + "\n" +
//                "co-id: " + codriverList + "\n" +
//                "Truck regNumber: " + driverEntity.getOrder().getCurrentTruck().getRegNumber() + "\n" +
//                "Order number: " + driverEntity.getOrder().getId() + "\n" +
//                "RoutePointList: " + routePointList;
//    }

    // Get list of avaible drivers for order
//    public List<DriverEntity> getAvaibleDriverForOrder() {
//        return null;
//    }


//    Update
    public Integer update(Integer id, String firstName, String lastName, Integer workedHours, Integer status, Integer cityId){

//        CargoDao cargoDao = new CargoDao(getEntityManager());
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
