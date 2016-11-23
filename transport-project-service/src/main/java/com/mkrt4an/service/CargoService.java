package com.mkrt4an.service;

import com.mkrt4an.dao.CargoDao;
import com.mkrt4an.entity.CargoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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

    //Find by id
    public CargoEntity findById(String id) {
        return  cargoDao.findCargoById(Integer.parseInt(id));
    }

    //Find by id
    public CargoEntity findById(Integer id) {
        return  cargoDao.findCargoById(id);
    }

    //Find all
    public List<CargoEntity> findAll() {
        return cargoDao.getAllCargo();
    }

    //Delete by id
    public Integer deleteById(String id){
        CargoEntity cargoEntity = cargoDao.findCargoById(Integer.parseInt(id));
        return cargoDao.deleteCargo(cargoEntity);
    }

    //Update
    public Integer update(Integer id, String name, String weight, String status){

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
