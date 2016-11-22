package com.mkrt4an.service;

import com.mkrt4an.dao.CargoDao;
import com.mkrt4an.entity.CargoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

//import static com.mkrt4an.utils.EntityManagerHelper.getEntityManager;

/**
 * Created by 123 on 02.10.2016.
 */

@Service
public class CargoService {

    private final CargoDao cargoDao;

    @Autowired
    public CargoService(CargoDao cargoDao) {
        this.cargoDao = cargoDao;
    }

    //Add new
    public Integer addNew(String name, String weight, String status) {

        CargoEntity cargoEntity = new CargoEntity(name, Integer.parseInt(weight), Integer.parseInt(status));
        return cargoDao.createCargo(cargoEntity);
    }

    //Add new
    public Integer addNew(String name, Integer weight, Integer status) {
//        CargoDao cargoDao = new CargoDao(getEntityManager());
        CargoEntity cargoEntity = new CargoEntity(name, weight, status);

        return cargoDao.createCargo(cargoEntity);
    }

//    //Add new
//    public Integer addNew(String name, Integer weight, Integer status,
//                          Integer loadingRoutePoint,
//                          Integer deliveryRoutePoint) {
//
////        CargoDao cargoDao = new CargoDao(getEntityManager());
//        /////////////////////////////////////
////        RoutePointDao routePointDao = new RoutePoint//Dao(getEntityManager());
//
//        CargoEntity cargoEntity = new CargoEntity(name, weight, status,
//                routePointDao.findRoutePointById(loadingRoutePoint),
//                routePointDao.findRoutePointById(deliveryRoutePoint)
//        );
//
//        cargoDao.createCargo(cargoEntity);
//        return cargoEntity.getId();
//    }

    //Find by id
    public CargoEntity findById(String id) {
//        CargoDao cargoDao = new CargoDao(getEntityManager());
        return  cargoDao.findCargoById(Integer.parseInt(id));
    }

    //Find by id
    public CargoEntity findById(Integer id) {
//        CargoDao cargoDao = new CargoDao(getEntityManager());
        return  cargoDao.findCargoById(id);
    }

    //Find all
    public List<CargoEntity> findAll() {
//        CargoDao cargoDao = new CargoDao(getEntityManager());
        return cargoDao.getAllCargo();
    }

    //Delete by id
    public void deleteById(String id){
//        CargoDao cargoDao = new CargoDao(getEntityManager());
        cargoDao.deleteCargo(cargoDao.findCargoById(Integer.parseInt(id)));
    }

    //Update
    public Integer update(Integer id, String name, String weight, String status){

//        CargoDao cargoDao = new CargoDao(getEntityManager());
        CargoEntity cargoEntity;
        cargoEntity = cargoDao.findCargoById(id);

        cargoEntity.setName(name);
        cargoEntity.setWeight(Integer.parseInt(weight));
        cargoEntity.setStatus(Integer.parseInt(status));

        return cargoDao.updateCargo(cargoEntity);
    }

    //Update
    public Integer update(CargoEntity cargoEntity){
        return cargoDao.updateCargo(cargoEntity);
    }



}
