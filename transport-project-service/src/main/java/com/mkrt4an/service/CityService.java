package com.mkrt4an.service;

import com.mkrt4an.dao.CityDao;
import com.mkrt4an.entity.CityEntity;
import com.mkrt4an.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

//import static com.mkrt4an.utils.EntityManagerHelper.getEntityManager;

@Service
public class CityService {

    private final CityDao cityDao;

    @Autowired
    public CityService(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    /**
     * Find all orders
     */
    public List<CityEntity> findAll() {
//        CityDao cityDao = new CityDao(getEntityManager());
        return cityDao.getAllCities();
    }

    /**
     * Find by id
     */
    public CityEntity findById(String id) {
//        CityDao cityDao = new CityDao(getEntityManager());
        return cityDao.findCityById(Integer.parseInt(id));
    }

    /**
     * Find by id
     */
    public CityEntity findById(Integer id) {
//        CityDao cityDao = new CityDao(getEntityManager());
        return cityDao.findCityById(id);
    }

    /**
     * Calc distance between two cities
     */
    public Double calcDistance(CityEntity cityA, CityEntity cityB) {
        Double dbl = Math.sqrt(Math.pow((cityA.getX() - cityB.getX()), 2) + Math.pow((cityA.getY() - cityB.getY()), 2));

        return Math.round(dbl * 100) / 100.0d;
    }

    /**
     * Calc distance between all potePoints(city) in order
     */
    public Double calcOrderDistance(OrderEntity orderEntity) {

        Double distance = 0d;
        int i = 0;

        if (orderEntity.getRoutePointList().size() == 0) return null;
        if (orderEntity.getRoutePointList().size() == 1) return null;

        while(orderEntity.getRoutePointList().size() - 1 > i) {
            distance += calcDistance(orderEntity.getRoutePointList().get(i).getCity(), orderEntity.getRoutePointList().get(++i).getCity());
        }

        return Math.round(distance * 100) / 100.0d;

    }

    /**
     * Calc time of all order
     */
    public Double calcOrderTime(OrderEntity orderEntity) {
        final float AVERAGE_SPEED = 60f;

        return Math.round((double) calcOrderDistance(orderEntity) / AVERAGE_SPEED * 100) / 100.0d;
    }
}