package com.mkrt4an.service;

import com.mkrt4an.dao.*;
import com.mkrt4an.dto.CargoDTO;
import com.mkrt4an.dto.OrderDTO;
import com.mkrt4an.dto.RoutePointDTO;
import com.mkrt4an.entity.*;
import com.mkrt4an.exception.DaoException;
import com.mkrt4an.exception.ServiceValidationException;
import com.mkrt4an.exception.TransportProjectException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


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
        return orderDao.findOrderById(id);
    }

    // Update order
    @Transactional
    public void update(OrderEntity orderEntity) throws TransportProjectException{
        orderEntity.setStartDate(new Date());
        if(!orderEntity.getDriverList().isEmpty()){orderEntity.setStatus(0);} // working
        orderDao.updateOrder(orderEntity);
    }

    //Find all orders
    public List<OrderEntity> findAll() {
        return orderDao.getAllOrders();
    }


    //Get list of truck avaible for this order
    public List<TruckEntity> getSuitableTruckList(OrderEntity orderEntity) throws TransportProjectException {

        List<TruckEntity> suitableTrucks = new ArrayList<TruckEntity>();
        List<TruckEntity> allTrucks = truckDao.getAllTrucks();

        for (TruckEntity truckEntity : allTrucks) {
            if ((routePointService.findMaxWeightOnRoute(orderEntity) < truckEntity.getCapacity()) &&
                    truckEntity.getStatus() == 1 // ok
                    &&
                    truckEntity.getOrders().isEmpty() //have no orders
            ) {
                suitableTrucks.add(truckEntity);
            }
        }

        if(suitableTrucks.isEmpty()) {
            throw new ServiceValidationException(" No suitable truck found for this order. Try later");}
        return suitableTrucks;
    }


    // Get driver list suitable for this order
    public List<DriverEntity> getSuitableDriverList(OrderEntity orderEntity) throws TransportProjectException {
//        CityService cityService = new CityService(); TODO: 22.11.2016

        List<DriverEntity> suitableDrivetList = new ArrayList<DriverEntity>();
        List<DriverEntity> driverEntityList = driverDao.getAllDrivers();

        for (DriverEntity driverEntity : driverEntityList) {
            if (
                    true /// TODO: 16.11.2016
                    //(cityService.calcOrderTime(orderEntity) + driverEntity.getWorkedHours()) <= 176 &&
                    //driverEntity.getOrder() == null &&
                    //orderEntity.getCurrentTruck().getDutySize() > suitableDrivetList.size() &&
//                    driverEntity.getCurrentCity().equals(orderEntity.getCurrentTruck().getCurrentCity())
                    ) {
                suitableDrivetList.add(driverEntity);
//                driverEntity.setCurrentTruck(orderEntity.getCurrentTruck()); //TODO
            }
        }

        if(suitableDrivetList.isEmpty()) {
            throw new ServiceValidationException(" No suitable drivers found for this order.");}
        return suitableDrivetList;
    }

    /**
     * Check if order has empty fields that should not be empty.
     * @param orderEntity {@link OrderEntity}
     * @return Doesn't return anything -- throws exception if failed.
     * @throws ServiceValidationException
     */
    private void validateOrderForRpList(OrderEntity orderEntity) throws ServiceValidationException {

        if (orderEntity.getRoutePointList() == null || orderEntity.getRoutePointList().isEmpty()) {
            throw new ServiceValidationException("Route point list is not set or empty.");
        }

        List<RoutePointEntity> routePointEntityList = orderEntity.getRoutePointList();

        int previousCityId = 0;
        List<CargoEntity> cargoLoadList = new ArrayList<>();
        List<CargoEntity> cargoDeliverList = new ArrayList<>();
        for(RoutePointEntity rp : routePointEntityList) {
            if (rp.getCity() == null) {
                throw new ServiceValidationException("City is not set in Rp.");
            }
            if (rp.getCity().getId() == previousCityId) {
                throw new ServiceValidationException("City is the same as in previous Rp. Two same cities in row.");
            }
            previousCityId = rp.getCity().getId();

            if (rp.getCargoToLoadList() == null) {
                throw new ServiceValidationException("Cargo load list is not set.");
            }

            if (rp.getCargoToDeliverList() == null) {
                throw new ServiceValidationException("Cargo delivery list is not set.");
            }

            for(CargoEntity cargoEntity : rp.getCargoToLoadList()){
                if (cargoEntity == null) {throw new ServiceValidationException("Cargo is not set in load list.");}
                cargoLoadList.add(cargoEntity);
            }
            for(CargoEntity cargoEntity : rp.getCargoToDeliverList()){
                if (cargoEntity == null) {throw new ServiceValidationException("Cargo is not set in deliver list.");}
                cargoDeliverList.add(cargoEntity);
            }
        }

        if(!cargoLoadList.containsAll(cargoDeliverList)) {
            throw new ServiceValidationException("Not all cargo will be delivered.");
        }
    }

    /**
     * Check if order has empty fields that should not be empty.
     * @param orderEntity {@link OrderEntity}
     * @return Doesn't return anything -- throws exception if failed.
     * @throws ServiceValidationException
     */
    public void validateOrderForDriverList(OrderEntity orderEntity) throws ServiceValidationException {

        if (orderEntity.getDriverList() == null || orderEntity.getDriverList().isEmpty()) {
            throw new ServiceValidationException("Driver list is not set or empty.");
        }

        if(orderEntity.getDriverList().size() == 1) {return;}

        List<DriverEntity> driverEntityList = orderEntity.getDriverList();

        DriverEntity previousDriverEntity = null;
        for(DriverEntity driverEntity : driverEntityList) {
            if (driverEntity.equals(previousDriverEntity)) {
                throw new ServiceValidationException("Driver list contain same driver. From driverEntity.equals(previousDriverEntity)");
            }
            previousDriverEntity = driverEntity;

            if (Collections.frequency(driverEntityList, driverEntity) >= 2) {
                throw new ServiceValidationException("Driver list contain same driver. From Collections.frequency");
            }
        }
    }

    /**
     * Delete order by id
     * @param id order id
     */
    @Transactional
    public Integer deleteById(String id) {
        OrderEntity orderEntity = orderDao.findOrderById(Integer.parseInt(id));
        return orderDao.deleteOrder(orderEntity);
    }

    /**
     * Add new order from DTO model.
     *
     * @param orderDTO
     * @return order id
     */
    @Transactional
    public Integer addOrderDTO(OrderDTO orderDTO) throws TransportProjectException {

        try {
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
            status = 2; // draft // TODO: 24.11.2016
            orderEntity.setStatus(status);
            log.debug("set status to orderEntity " + orderEntity);

            Map<Integer, CargoEntity> cargoEntityMap = new HashMap<>();
            //List<CargoEntity> cargoEntityList = new ArrayList<>();

            for (CargoDTO cargoDTO : orderDTO.getCargoList()) {
                CargoEntity cargoEntity = new CargoEntity();
                cargoEntity.setName(cargoDTO.getName());
                cargoEntity.setWeight(cargoDTO.getWeight());
                cargoEntityMap.put(cargoDTO.getNumber(), cargoEntity);
            }

//        System.out.println(cargoEntityMap);
            log.debug("Cargo map " + cargoEntityMap);

            // set rpList in order
            List<RoutePointEntity> routePointList = new ArrayList<>();

            // go throw rp array
            for (RoutePointDTO routePointDTO : orderDTO.getRpList()) {

                List<CargoEntity> loadList = new ArrayList<>();
                List<CargoEntity> deliverList = new ArrayList<>();
                RoutePointEntity routePointEntity = new RoutePointEntity();

//            System.out.println(routePointDTO);

                for (Integer cargoId : routePointDTO.getLoadList()) {
                    loadList.add(cargoEntityMap.get(cargoId));
                }

                for (Integer cargoId : routePointDTO.getDeliverList()) {
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

//            System.out.println(routePointEntity);
//            System.out.println("-------------------------------------");
//            System.out.println(cargoEntityMap);

                routePointList.add(routePointEntity);
            }

//      // set RpList in order
            orderEntity.setRoutePointList(routePointList);
            log.debug("orderEntity setRoutePointList: " + orderEntity);

//        System.out.println("-------------------------------------");
//        System.out.println(orderEntity);
            //orderEntity.assignRoutePointList(routePointService.getOrderRoutePointList(orderEntity.getId()));
            orderEntity.assignRoutePointList(orderEntity.getRoutePointList());

            // Make persist
            // Persist cargo
            for (CargoEntity cargoEntity : cargoEntityMap.values()) {
                cargoDao.createCargo(cargoEntity);
                log.debug("persist cargoEntity: " + cargoEntity);
            }

            // persist RP
            for (RoutePointEntity routePointEntity : routePointList) {
                routePointDao.createRoutePoint(routePointEntity);
                log.debug("persist routePointEntity: " + routePointEntity);
            }

            validateOrderForRpList(orderEntity);

            // persist order
            Integer orderId = orderDao.createOrder(orderEntity);
            log.debug("persist orderEntity: " + orderEntity);

//        System.out.println("-------------------------------------");
//        System.out.println(orderEntity);

            return orderId;

        } catch (DaoException e) {
            log.warn("addOrderDTO service exception.", e);
            throw new TransportProjectException(e);
        }
    }
}

