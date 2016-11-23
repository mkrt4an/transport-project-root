package com.mkrt4an.service;

import com.mkrt4an.dao.CargoDao;
import com.mkrt4an.dao.CityDao;
import com.mkrt4an.dao.OrderDao;
import com.mkrt4an.dao.RoutePointDao;
import com.mkrt4an.entity.CargoEntity;
import com.mkrt4an.entity.OrderEntity;
import com.mkrt4an.entity.RoutePointEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 123 on 02.10.2016.
 */

@Service
@Transactional
//@Scope(value = "prototype") // TODO: 22.11.2016
public class RoutePointService {

    private final OrderDao orderDao;
    private final RoutePointDao routePointDao;
    private final CityDao cityDao;
    private final CargoDao cargoDao;

    @Autowired
    public RoutePointService(RoutePointDao routePointDao, CityDao cityDao, CargoDao cargoDao, OrderDao orderDao) {
        this.routePointDao = routePointDao;
        this.cityDao = cityDao;
        this.cargoDao = cargoDao;
        this.orderDao = orderDao;
    }

  //  @Autowired
    //private  RoutePointService routePointService;

//    public RoutePointService() {} // TODO: 22.11.2016

    //////////////////////////////////////////////////////////////////// // TODO: 23.11.2016
//    public Integer AddRoutePointBlank(Integer cityId, Integer orderId) {
//
//        RoutePointEntity routePoint = new RoutePointEntity(cityDao.findCityById(cityId), orderDao.findOrderById(orderId));
//
//        return routePointDao.createRoutePoint(routePoint);
//    }


    /**
     * Delete Rp.
     *
     * @return Rp id
     */
    public Integer deleteById(Integer id) {
        RoutePointEntity routePointEntity = routePointDao.findRoutePointById(id);
        return routePointDao.deleteRoutePoint(routePointEntity);
    }

    ////////////////////////////////////////////////////////// // TODO: 23.11.2016
//    public Integer AddRoutePoint(List<CargoEntity> cargoToLoad,
//                                 List<CargoEntity> cargoToDeliver,
//                                 Integer ordinal, CityEntity city, OrderEntity order) {
//
//        RoutePointEntity routePoint = new RoutePointEntity( cargoToLoad, cargoToDeliver, ordinal, city, order);
//
//        routePoint.assignCargoToLoadList(cargoToLoad);
//        routePoint.assignCargoToDeliverList(cargoToDeliver);
//
//         routePointDao.createRoutePoint(routePoint);
//        order.assignRoutePointList(this.getOrderRoutePointList(order.getId()));
//
//        return routePoint.getId();
//    }

// TODO: 23.11.2016
//    public Integer AddCargoToLoadingList(Integer orderId, Integer routePointId,
//                                         String cargoName, Integer cargoWeight) {
//
//        //Creare CargoEntity
//        CargoEntity cargoEntity = new CargoEntity(cargoName, cargoWeight, 0);
//        cargoDao.createCargo(cargoEntity);
//
//        RoutePointEntity routePointEntity = routePointDao.findRoutePointById(routePointId);
//
//        List<CargoEntity> cargoToLoadList;
//
//        if (routePointEntity.getCargoToLoadList() != null) {
//            cargoToLoadList = routePointEntity.getCargoToLoadList();
//            cargoToLoadList.add(cargoEntity);
//        } else {
//            cargoToLoadList = new ArrayList<>();
//            cargoToLoadList.add(cargoEntity);
//        }
//
//        routePointEntity.setCargoToLoadList(cargoToLoadList);
//        routePointEntity.assignCargoToLoadList(cargoToLoadList);
//
//        routePointDao.updateRoutePoint(routePointEntity);
//
//        return cargoEntity.getId();
//    }

// TODO: 23.11.2016
//    public Integer AddCargoToDeliverList(Integer orderId, Integer routePointId,
//                                         Integer cargoId) {
//
//        RoutePointEntity routePointEntity = routePointDao.findRoutePointById(routePointId);
//        CargoEntity cargoEntity = cargoDao.findCargoById(cargoId);
//
//        List<CargoEntity> cargoToDeliverList;
//
//        if (routePointEntity.getCargoToLoadList() != null) {
//            cargoToDeliverList = routePointEntity.getCargoToLoadList();
//            cargoToDeliverList.add(cargoEntity);
//        } else {
//            cargoToDeliverList = new ArrayList<>();
//            cargoToDeliverList.add(cargoEntity);
//        }
//
//        routePointEntity.setCargoToDeliverList(cargoToDeliverList);
//        routePointEntity.assignCargoToDeliverList(cargoToDeliverList);
//
//        routePointDao.updateRoutePoint(routePointEntity);
//
//        return cargoEntity.getId();
//    }
//
//
//    public Integer updateRoutePoint(RoutePointEntity routePoint,
//                                    List<CargoEntity> cargoToLoad,
//                                    List<CargoEntity> cargoToDeliver,
//                                    Integer ordinal,
//                                    CityEntity city,
//                                    OrderEntity order) {
//
//        routePoint.setCargoToLoadList(cargoToLoad);
//        routePoint.setCargoToLoadList(cargoToDeliver);
//        routePoint.setOrdinal(ordinal);
//        routePoint.setCity(city);
//        routePoint.setOrder(order);
//
//        return routePointDao.updateRoutePoint(routePoint);
//    }


    public RoutePointEntity findById(Integer id) {
        return routePointDao.findRoutePointById(id);
    }


    //Find all orders
    public List<RoutePointEntity> findAll() {
        return routePointDao.getAllRoutePoints();
    }

    //Find max weight on route points to find suitable truck that have such capasity
    public Integer findMaxWeightOnRoute(OrderEntity orderEntity) {

        Integer maxWeight = 0;
        //todo
//        List<RoutePointEntity> routePointEntityList = orderEntity.getRpList();
        List<RoutePointEntity> routePointEntityList = this.getOrderRoutePointList(orderEntity.getId());

        for (RoutePointEntity routePointEntity: routePointEntityList) {
            for (CargoEntity cargoEntity : routePointEntity.getCargoToLoadList()) {
                maxWeight = +cargoEntity.getWeight();
            }
            for (CargoEntity cargoEntity : routePointEntity.getCargoToDeliverList()) {
                maxWeight = -cargoEntity.getWeight();
            }
        }
        return maxWeight;
    }

    //Get order RP list by order id
    public List<RoutePointEntity> getOrderRoutePointList(Integer orderId) {

        List<RoutePointEntity> results = new ArrayList<>();
        for(RoutePointEntity routePointEntity : routePointDao.getAllRoutePoints()) {
            if((routePointEntity.getOrder() != null) &&  routePointEntity.getOrder().getId().equals(orderId)) {
                results.add(routePointEntity);
            }
        }

        return results;
    }
}
