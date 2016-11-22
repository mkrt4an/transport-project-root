package com.mkrt4an.service;

import com.mkrt4an.dao.*;
import com.mkrt4an.entity.TruckEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//import static com.mkrt4an.utils.EntityManagerHelper.getEntityManager;

/**
 * Created by 123 on 02.10.2016.
 */

@Service
public class TruckService {

    private final OrderDao orderDao;
    private final RoutePointDao routePointDao;
    private final TruckDao truckDao;
    private final CityDao cityDao;
    private final CargoDao cargoDao;
    private final DriverDao driverDao;

    @Autowired
    public TruckService(CargoDao cargoDao, DriverDao driverDao, OrderDao orderDao, RoutePointDao routePointDao, TruckDao truckDao, CityDao cityDao) {
        this.cargoDao = cargoDao;
        this.driverDao = driverDao;
        this.orderDao = orderDao;
        this.routePointDao = routePointDao;
        this.truckDao = truckDao;
        this.cityDao = cityDao;
    }


    //Add new
    public Integer addNew(Integer dutySize, Integer capasity, Integer status, String regNumber, Integer cityId) {
//        CargoDao cargoDao = new CargoDao(getEntityManager());
        TruckEntity truckEntity = new TruckEntity(dutySize, capasity, status, regNumber, cityDao.findCityById(cityId));
        truckDao.createTruck(truckEntity);
        return truckEntity.getId();
    }


    //Find by id
    public TruckEntity findById(String id) {
//        TruckDao truckDao = new TruckDao(getEntityManager());
        return truckDao.findTruckById(Integer.parseInt(id));
    }

    //Find by id
    public TruckEntity findById(Integer id) {
//        TruckDao truckDao = new TruckDao(getEntityManager());
        return truckDao.findTruckById(id);
    }


    // Find all trucks
    public List<TruckEntity> findAllTrucks (){
//        TruckDao truckDao = new TruckDao(getEntityManager());
        return truckDao.getAllTrucks();
    }

    public void deleteById(Integer Id) {
//        TruckDao truckDao = new TruckDao(getEntityManager());
//        TruckEntity truckEntity = truckDao.findTruckById(Id);
        truckDao.deleteTruck(truckDao.findTruckById(Id));
    }

    public void deleteById(String Id) {
//        TruckDao truckDao = new TruckDao(getEntityManager());
//        TruckEntity truckEntity = ;
        truckDao.deleteTruck(truckDao.findTruckById(Integer.parseInt(Id)));
    }

    //Update
    public Integer update(Integer id, Integer dutySize, Integer capasity, Integer status, String regNumber, Integer cityId){

        TruckEntity truckEntity = truckDao.findTruckById(id);

        truckEntity.setDutySize(dutySize);
        truckEntity.setCapacity(capasity);
        truckEntity.setStatus(status);
        truckEntity.setRegNumber(regNumber);
        truckEntity.setCurrentCity(cityDao.findCityById(cityId));

        return truckDao.updateTruck(truckEntity);
    }
}
