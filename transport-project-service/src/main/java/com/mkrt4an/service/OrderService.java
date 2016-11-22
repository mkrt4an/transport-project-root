package com.mkrt4an.service;

import com.mkrt4an.dao.*;
import com.mkrt4an.dto.CargoDTO;
import com.mkrt4an.dto.OrderDTO;
import com.mkrt4an.dto.RoutePointDTO;
import com.mkrt4an.entity.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
//@Transactional // TODO: 22.11.2016  add separate annotation to methods
public class OrderService {

    private static final Logger log = Logger.getLogger(OrderService.class);

    private final OrderDao orderDao;
    private final RoutePointDao routePointDao;
    private final TruckDao truckDao;
    private final CityDao cityDao;
    private final CargoDao cargoDao;
    private final DriverDao driverDao;
    private final RoutePointService routePointService;

    @Autowired
    public OrderService(CargoDao cargoDao, RoutePointDao routePointDao, OrderDao orderDao, TruckDao truckDao, CityDao cityDao, RoutePointService routePointService, DriverDao driverDao) {
        this.cargoDao = cargoDao;
        this.routePointDao = routePointDao;
        this.orderDao = orderDao;
        this.truckDao = truckDao;
        this.cityDao = cityDao;
        this.routePointService = routePointService;
        this.driverDao = driverDao;
    }

    // Exceptions
//    public class NoSuitableDrversException extends Exception {
//        NoSuitableDriversException() {
//        }
//    }

//    public class NoSuitableTruckException extends Exception {
//        NoSuitableTruckException() {
//        }
//    }

    /**
     * Find by id.
     *
     * @param id entity id
     * @return the found entity instance
     */
    public OrderEntity findById(String id) {
//        OrderDao orderDao = new OrderDao(getEntityManager());
        return orderDao.findOrderById(Integer.parseInt(id));
    }

    /**
     * Find by id.
     *
     * @param id entity id
     * @return the found entity instance
     */
    public OrderEntity findById(Integer id) {
//        OrderDao orderDao = new OrderDao(getEntityManager());
        return orderDao.findOrderById(id);
    }

    // Update order
    @Transactional
    public void update(OrderEntity orderEntity) {
//        OrderDao orderDao = new OrderDao(getEntityManager());
        orderDao.updateOrder(orderEntity);
    }

    //Find all orders
    public List<OrderEntity> findAll() {
//        OrderDao orderDao = new OrderDao(getEntityManager());
        return orderDao.getAllOrders();
    }

    // Get truck suitable for this order
    public TruckEntity getSuitableTruck(OrderEntity orderEntity)
//            throws NoSuitableTruckException

    {
//        RoutePointService routePointService = new RoutePointService(); // TODO: 22.11.2016

//        TruckDao truckDao = new TruckDao(getEntityManager());

        List<TruckEntity> suitableTrucks = new ArrayList<TruckEntity>();

        List<TruckEntity> allTrucks = truckDao.getAllTrucks();

        for (TruckEntity truckEntity : allTrucks) {
            if (
                    (routePointService.findMaxWeightOnRoute(orderEntity) < truckEntity.getCapacity()) &&
                    truckEntity.getStatus() == 1 &&
                    truckEntity.getOrders() == null) {
                suitableTrucks.add(truckEntity);
            }
        }

        return suitableTrucks.isEmpty() ? null : suitableTrucks.get(0);
    }


    //Get list of truck avaible for this order
    public List<TruckEntity> getSuitableTruckList(OrderEntity orderEntity)
//            throws NoSuitableTruckException
    {
//        RoutePointService routePointService = new RoutePointService();

//        TruckDao truckDao = new TruckDao(getEntityManager());

        List<TruckEntity> suitableTrucks = new ArrayList<TruckEntity>();

        List<TruckEntity> allTrucks = truckDao.getAllTrucks();

        for (TruckEntity truckEntity : allTrucks) {
            if ((routePointService.findMaxWeightOnRoute(orderEntity) < truckEntity.getCapacity()) &&
                    truckEntity.getStatus() == 1
                    //&&
                   // truckEntity.getOrders() == null
            ) {
                suitableTrucks.add(truckEntity);
            }
        }

        System.out.println();
        return suitableTrucks;
//        return truckDao.getAllTrucks();
    }


    // Get driver suitable for this order
    public DriverEntity getSuitableDriver(OrderEntity orderEntity)
//            throws NoSuitableDriversException
    {

//        DriverDao driverDao = new DriverDao(getEntityManager());

        List<DriverEntity> suitableDrivetList = new ArrayList<DriverEntity>();

        List<DriverEntity> driverEntityList = driverDao.getAllDrivers();

        for (DriverEntity driverEntity : driverEntityList) {
            if (driverEntity.getOrder() == null &&
                    driverEntity.getCurrentCity() == orderEntity.getCurrentTruck().getCurrentCity()) {
                suitableDrivetList.add(driverEntity);
            }
        }

        return suitableDrivetList.isEmpty() ? null : suitableDrivetList.get(0);
    }


    // Get driver list suitable for this order
    public List<DriverEntity> getSuitableDriverList(OrderEntity orderEntity)
//            throws NoSuitableDrversException

    {
//        CityService cityService = new CityService(); TODO: 22.11.2016

//        DriverDao driverDao = new DriverDao(getEntityManager());

        List<DriverEntity> suitableDrivetList = new ArrayList<DriverEntity>();

        List<DriverEntity> driverEntityList = driverDao.getAllDrivers();

        for (DriverEntity driverEntity : driverEntityList) {
            if (   true /// TODO: 16.11.2016  
                    //(cityService.calcOrderTime(orderEntity) + driverEntity.getWorkedHours()) <= 176 &&
                    //driverEntity.getOrder() == null &&
                    //orderEntity.getCurrentTruck().getDutySize() > suitableDrivetList.size() &&
                    //driverEntity.getCurrentCity() == orderEntity.getCurrentTruck().getCurrentCity()
                    ) {
                suitableDrivetList.add(driverEntity);
//                driverEntity.setCurrentTruck(orderEntity.getCurrentTruck()); //TODO
            }
        }

        return suitableDrivetList;
    }


//    // Add new order by uid, cargoEntity and CityEntity
//    public Integer addOrder(Integer uid, Integer cargoEntityId, Integer fromCityId, Integer toCityId) {
//
//        // List of routrPoint which we transfer to orderEntity constructor as argument
//        List<RoutePointEntity> routePointEntityList = new ArrayList<RoutePointEntity>();
//
//        // Get DAO
//        OrderDao orderDao = new OrderDao(getEntityManager());
//        RoutePointDao routePointDao = new RoutePointDao(getEntityManager());
//        CityDao cityDao = new CityDao(getEntityManager());
//        CargoDao cargoDao = new CargoDao(getEntityManager());
//
//        // Get entities
//        CargoEntity cargoEntity = cargoDao.findCargoById(cargoEntityId);
//        CityEntity fromCityEntity = cityDao.findCityById(fromCityId);
//        CityEntity toCityEntity = cityDao.findCityById(toCityId);
//
//        // City, from which we are taking
//        RoutePointEntity routePointEntity = new RoutePointEntity(fromCityEntity, cargoEntity, 0);
//        Integer i = routePointDao.createRoutePoint(routePointEntity);
//        routePointEntityList.add(routePointDao.findRoutePointById(i));
//
//        // City, in which we are taking
//        routePointEntity = new RoutePointEntity(toCityEntity, cargoEntity, 1);
//        i = routePointDao.createRoutePoint(routePointEntity);
//        routePointEntityList.add(routePointDao.findRoutePointById(i));
//
//        //Create order entity
//        OrderEntity orderEntity = new OrderEntity(uid, 0, routePointEntityList);
//
//        //set order id to routePoints
//        orderEntity.assignRoutePointList(routePointEntityList);
//
//        // return new order id
//        return orderDao.createOrder(orderEntity);
//    }
    /**
     * Add new blank order.
     *
     * @return order id
     */
    public void deleteOrder(OrderEntity orderEntity) {
        orderDao.deleteOrder(orderEntity);
    }
    /**
     * Add new blank order.
     *
     * @return order id
     */
    public Integer deleteById(Integer id) {
        OrderEntity orderEntity = orderDao.findOrderById(id);
        return orderDao.deleteOrder(orderEntity);
    }
    /**
     * Delete order by id
     * @param id order id
     */
    public Integer deleteById(String id) {
        OrderEntity orderEntity = orderDao.findOrderById(Integer.parseInt(id));
        return orderDao.deleteOrder(orderEntity);
    }



    /**
     * Add new blank order.
     *
     * @return order id
     */
    public Integer addOrderBlank() {
        //Create order entity
        OrderEntity orderEntity = new OrderEntity();
        // return new order id
        return orderDao.createOrder(orderEntity);
    }


    /**
     * Add new order from DTO model.
     *
     * @param orderDTO
     * @return order id
     */
    @Transactional
    public Integer addOrderDTO(OrderDTO orderDTO) {
        //Create order entity
        OrderEntity orderEntity = new OrderEntity();
        log.debug("Create orderEntity " + orderEntity);

        // return new order id
        //Integer orderId = orderDao.createOrder(orderEntity);

        // set UID in order
        Integer uid = Integer.parseInt(orderDTO.getUid());
        orderEntity.setUid(uid);
        log.debug("set uid to orderEntity " + orderEntity);

        // set status in order
        Integer status = Integer.parseInt(orderDTO.getStatus());
        orderEntity.setStatus(status);
        log.debug("set status to orderEntity " + orderEntity);

        Map<Integer, CargoEntity> cargoEntityMap = new HashMap<>();
        //List<CargoEntity> cargoEntityList = new ArrayList<>();

        for(CargoDTO cargoDTO : orderDTO.getCargoList()) {
            CargoEntity cargoEntity = new CargoEntity();
            cargoEntity.setName(cargoDTO.getName());
            cargoEntity.setWeight(cargoDTO.getWeight());
            cargoEntityMap.put(cargoDTO.getNumber(), cargoEntity);
        }

        System.out.println(cargoEntityMap);
        log.debug("Cargo map " + cargoEntityMap);

        // set rpList in order
        List<RoutePointEntity> routePointList = new ArrayList<>();

        // go throw rp array
        for(RoutePointDTO routePointDTO : orderDTO.getRpList()) {

            List<CargoEntity> loadList = new ArrayList<>();
            List<CargoEntity> deliverList = new ArrayList<>();
            RoutePointEntity routePointEntity = new RoutePointEntity();

            System.out.println(routePointDTO);

            for(Integer cargoId : routePointDTO.getLoadList()) {
                loadList.add(cargoEntityMap.get(cargoId));
            }

            for(Integer cargoId : routePointDTO.getDeliverList()) {
                deliverList.add(cargoEntityMap.get(cargoId));
            }

            // set city in Rp
            Integer cityId = Integer.parseInt(routePointDTO.getCity());
            routePointEntity.setCity(cityDao.findCityById(cityId));
            log.debug("routePointEntity setCity: " + routePointEntity);

            // set ordinal in RP
            Integer ordinal = routePointDTO.getOrdinal();
            routePointEntity.setOrdinal(ordinal);
            log.debug("routePointEntity setOrdinal: " + routePointEntity);

            // Set cargo lists in their Rp
            routePointEntity.setCargoToLoadList(loadList);
            routePointEntity.setCargoToDeliverList(deliverList);
            log.debug("routePointEntity setCargoToLoadList: " + routePointEntity);
            log.debug("routePointEntity setCargoToDeliverList: " + routePointEntity);

            routePointEntity.assignCargoToLoadList(loadList);
            routePointEntity.assignCargoToDeliverList(deliverList);

            System.out.println(routePointEntity);
            System.out.println("-------------------------------------");
            System.out.println(cargoEntityMap);

            routePointList.add(routePointEntity);
        }

//      // set RpList in order
        orderEntity.setRoutePointList(routePointList);
        log.debug("orderEntity setRoutePointList: " + orderEntity);

        System.out.println("-------------------------------------");
        System.out.println(orderEntity);
        //orderEntity.assignRoutePointList(routePointService.getOrderRoutePointList(orderEntity.getId()));
        orderEntity.assignRoutePointList(orderEntity.getRoutePointList());

        // Make persist
        // Persist cargo
        for(CargoEntity cargoEntity : cargoEntityMap.values()) {
            cargoDao.createCargo(cargoEntity);
            log.debug("persist cargoEntity: " + cargoEntity);
        }

        // persist RP
        for(RoutePointEntity routePointEntity : routePointList) {
            routePointDao.createRoutePoint(routePointEntity);
            log.debug("persist routePointEntity: " + routePointEntity);
        }

        // persist order
        Integer orderId = orderDao.createOrder(orderEntity);
        log.debug("persist orderEntity: " + orderEntity);

        System.out.println("-------------------------------------");
        System.out.println(orderEntity);

        return orderId;
    }
}
