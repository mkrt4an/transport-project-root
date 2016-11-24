package com.mkrt4an.service;

import com.mkrt4an.dao.CityDao;
import com.mkrt4an.dao.TruckDao;
import com.mkrt4an.entity.TruckEntity;
import com.mkrt4an.exception.ServiceValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//import static com.mkrt4an.utils.EntityManagerHelper.getEntityManager;

/**
 * Created by 123 on 02.10.2016.
 */

@Service
public class TruckService {

//    private final OrderDao orderDao;
//    private final RoutePointDao routePointDao;
    private final TruckDao truckDao;
    private final CityDao cityDao;
//    private final CargoDao cargoDao;
//    private final DriverDao driverDao;

    @Autowired
    public TruckService(TruckDao truckDao, CityDao cityDao) {
//        this.cargoDao = cargoDao;
//        this.driverDao = driverDao;
//        this.orderDao = orderDao;
//        this.routePointDao = routePointDao;
        this.truckDao = truckDao;
        this.cityDao = cityDao;
    }

    /**
     * Check if truck has empty fields that should not be empty.
     * @param truckEntity {@link TruckEntity}
     * @return Doesn't return anything -- throws exception if failed.
     * @throws ServiceValidationException
     */
    private void validateTruckForEmptyFields(TruckEntity truckEntity) throws ServiceValidationException {
        if (truckEntity.getRegNumber() == null || truckEntity.getRegNumber().isEmpty()) {
            throw new ServiceValidationException("RegNumber is not set.or empty.");

        } else if (truckEntity.getDutySize() == null) {
            throw new ServiceValidationException("Duty size is not set.");
        } else if (truckEntity.getDutySize() <= 0 || truckEntity.getDutySize() > 10) {
            throw new ServiceValidationException("Duty size can't be 0 or negative or greater 10.");

        } else if (truckEntity.getCapacity() == null) {
            throw new ServiceValidationException("Capacity is not set.");
        } else if (truckEntity.getCapacity() <= 0) {
            throw new ServiceValidationException("Capacity can't be 0 or negative.");

        } else if (truckEntity.getCurrentCity() == null || truckEntity.getCurrentCity().getId() == 0) {
            throw new ServiceValidationException("City is not set.");

        } else if (truckEntity.getStatus() == null || truckEntity.getStatus() < 0 || truckEntity.getStatus() > 2) {
            throw new ServiceValidationException("Truck status is not set or less 0 or greater 2.");
        }
    }


    //Add new
    @Transactional
    public Integer addNew(Integer dutySize, Integer capacity, Integer status, String regNumber, Integer cityId) {
        TruckEntity truckEntity = new TruckEntity(dutySize, capacity, status, regNumber, cityDao.findCityById(cityId));
        return truckDao.createTruck(truckEntity);
    }

    //Find by id
    public TruckEntity findById(String id) {
        return truckDao.findTruckById(Integer.parseInt(id));
    }

    // Find all trucks
    public List<TruckEntity> findAllTrucks (){
        return truckDao.getAllTrucks();
    }

    public Integer deleteById(String Id) {
        return truckDao.deleteTruck(truckDao.findTruckById(Integer.parseInt(Id)));
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
