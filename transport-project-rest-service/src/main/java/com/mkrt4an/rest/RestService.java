package com.mkrt4an.rest;

import com.mkrt4an.entity.CargoEntity;
import com.mkrt4an.entity.DriverEntity;
import com.mkrt4an.entity.OrderEntity;
import com.mkrt4an.exception.TransportProjectException;
import com.mkrt4an.service.CargoService;
import com.mkrt4an.service.DriverService;
import com.mkrt4an.service.OrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestService {

    private static final Logger log = Logger.getLogger(RestService.class);

    private final DriverService driverService;
    private final CargoService cargoService;
    private final OrderService orderService;

    @Autowired
    RestService(DriverService driverService, CargoService cargoService, OrderService orderService) {
        this.driverService = driverService;
        this.cargoService = cargoService;
        this.orderService = orderService;
    }

    // Drivers section ========================
    @RequestMapping("/rest/driver/{id}")
    public DriverEntity getDriverById(@PathVariable("id") Integer id) {
        DriverEntity driverEntity = driverService.findById(id);
        log.debug(driverEntity);
        return driverEntity;
    }

    @RequestMapping("/rest/driver/")
    public List<DriverEntity> getAllDrivers() {
        List<DriverEntity> driverEntityList = driverService.findAllDrivers();
        log.debug(driverEntityList);
        return driverEntityList;
    }

    @RequestMapping(value = "/rest/driver/{id}", method = RequestMethod.PUT)
    public ResponseEntity<DriverEntity> updateDriver(@PathVariable("id") Integer id,
                                                     @RequestParam("status") Integer status) throws TransportProjectException{

        log.info("id = " + id + '\n' + "status = " + status);

        DriverEntity driverEntity = driverService.findById(id);
        log.info(driverEntity);

        if (driverEntity == null) {
            log.warn("Driver with id " + id + " not found");
            return new ResponseEntity<DriverEntity>(HttpStatus.NOT_FOUND);
        }

        driverEntity.setStatus(status);
        driverService.update(driverEntity);
        log.info(driverEntity);

        return new ResponseEntity<DriverEntity>(driverEntity, HttpStatus.OK);
    }

    // Order section ===========================
    @RequestMapping("/rest/order/{id}")
    public OrderEntity getOrderById(@PathVariable("id") Integer id) {
        OrderEntity orderEntity = orderService.findById(id);
        System.out.println(orderEntity);
        return orderEntity;
    }

    @RequestMapping("/rest/order/")
    public List<OrderEntity> getAllOrder() {
        return orderService.findAll();
    }


    @RequestMapping(value = "/rest/order/{id}", method = RequestMethod.PUT)
    public ResponseEntity<OrderEntity> updateOrder(@PathVariable("id") Integer id,
                                                    @RequestParam("status") Integer status) throws TransportProjectException {

        System.out.println("id = " + id + '\n' + "status = " + status);

        OrderEntity orderEntity = orderService.findById(id);
        System.out.println(orderEntity);

        if (orderEntity == null) {
            System.out.println("Cargo with id " + id + " not found");
            return new ResponseEntity<OrderEntity>(HttpStatus.NOT_FOUND);
        }

        orderEntity.setStatus(status);
        orderService.update(orderEntity);
        System.out.println(orderEntity);

        return new ResponseEntity<OrderEntity>(orderEntity, HttpStatus.OK);
    }

    // Cargo section ============================
    @RequestMapping("/rest/cargo/")
    public List<CargoEntity> getAllCargo() {
        return cargoService.findAll();
    }

    @RequestMapping("/rest/cargo/{id}")
    public CargoEntity getCargoById(@PathVariable("id") Integer id) {
        CargoEntity cargoEntity = cargoService.findById(id);
        System.out.println(cargoEntity);
        return cargoEntity;
    }

    @RequestMapping(value = "/rest/cargo/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CargoEntity> updateCargo(@PathVariable("id") Integer id,
                                                   @RequestParam("status") Integer status) {

        System.out.println("id = " + id + '\n' + "status = " + status);

        CargoEntity cargoEntity = cargoService.findById(id);

        if (cargoEntity == null) {
            System.out.println("Cargo with id " + id + " not found");
            return new ResponseEntity<CargoEntity>(HttpStatus.NOT_FOUND);
        }

        cargoEntity.setStatus(status);
        cargoService.update(cargoEntity);

        return new ResponseEntity<CargoEntity>(cargoEntity, HttpStatus.OK);
    }

    // login section
    @RequestMapping(value = "/rest/login", method = RequestMethod.GET)
    public ResponseEntity<OrderEntity> login(@RequestParam("firstName") String firstName,
                                             @RequestParam("lastName") String lastName) {

        System.out.println("firstName = " + firstName + '\n' + "lastName = " + lastName);

        OrderEntity orderEntity = null;
        DriverEntity driverEntity = driverService.findByFirstNameAndLastName(firstName, lastName);
        if (driverEntity != null) {
            orderEntity = driverEntity.getOrder() != null ? driverEntity.getOrder() : null;
        }

        System.out.println(orderEntity);

        if (driverEntity == null) {
            System.out.println("Driver with name = " + firstName + "and" + lastName + " not found");
            return new ResponseEntity<OrderEntity>(HttpStatus.NOT_FOUND);
        }

        if (driverEntity.getOrder() == null) {
            System.out.println("Driver have no order not found");
            return new ResponseEntity<OrderEntity>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<OrderEntity>(orderEntity, HttpStatus.OK);


    }

}