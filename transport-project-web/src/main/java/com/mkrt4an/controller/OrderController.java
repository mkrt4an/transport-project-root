package com.mkrt4an.controller;

/*
  Created by 123 on 04.10.2016.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mkrt4an.dto.OrderDTO;
import com.mkrt4an.entity.DriverEntity;
import com.mkrt4an.entity.OrderEntity;
import com.mkrt4an.exception.TransportProjectException;
import com.mkrt4an.service.DriverService;
import com.mkrt4an.service.OrderService;
import com.mkrt4an.service.TruckService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController extends HttpServlet {

    private static final Logger log = Logger.getLogger(OrderController.class);

    private final OrderService orderService;
    private final TruckService truckService;
    private final DriverService driverService;

    @Autowired
    public OrderController(TruckService truckService, DriverService driverService,OrderService orderService) {
        this.truckService = truckService;
        this.driverService = driverService;
        this.orderService = orderService;
    }

    @RequestMapping(value = "/order/getAll", method = RequestMethod.GET)
    public String getAllDrivers(Model model) {

        return "GetAllOrders";
    }

    @RequestMapping(value = "/order/add", method = RequestMethod.GET)
    public String addRedirect(Model model) {

        return "AddOrder";
    }

//    @Transactional
    @RequestMapping(value = "/order/add/test", method = RequestMethod.GET)
    public ModelAndView add(Model model, @RequestParam("order") String order ) throws TransportProjectException{

        ObjectMapper objectMapper = new ObjectMapper();
        //Set pretty printing of json
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        OrderDTO orderDTO = new OrderDTO();

        try{
            orderDTO = objectMapper.readValue(order, OrderDTO.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        log.debug(orderDTO);
        Integer orderId = orderService.addOrderDTO(orderDTO);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("SuccessOrder");
        modelAndView.addObject("order", orderService.findById(orderId));

        return modelAndView;
    }


    @RequestMapping(value = "/order/add/set-truck", method = RequestMethod.GET)
    public ModelAndView setTruckToOrder(Model model, @RequestParam("id") String orderId ) throws TransportProjectException {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("AddOrderStepTwo");
        modelAndView.addObject("truckAll", orderService.getSuitableTruckList(orderService.findById(orderId)));
        modelAndView.addObject("orderId", orderId);

        return modelAndView;
    }

    @RequestMapping(value = "/order/add/set-truck-confirm", method = RequestMethod.GET)
    public ModelAndView setTruckToOrderConfirm(Model model,
                                               @RequestParam("truckId") String truckId,
                                               @RequestParam("orderId") String orderId) throws TransportProjectException{

        OrderEntity orderEntity = orderService.findById(orderId);
        orderEntity.setCurrentTruck(truckService.findById(truckId));
        orderService.update(orderEntity);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("AddOrderStepThree");
        modelAndView.addObject("driverAll", orderService.getSuitableDriverList(orderService.findById(orderId)));
        modelAndView.addObject("orderId", orderId);
        modelAndView.addObject("truckId", truckId);
        modelAndView.addObject("currentTruckDutySize", truckService.findById(truckId).getDutySize());

        return modelAndView;
    }

    @RequestMapping(value = "/order/add/set-driver-confirm", method = RequestMethod.GET)
    public ModelAndView setDriverToOrderConfirm(Model model,
                                               @RequestParam("truckId") String truckId,
                                               @RequestParam("orderId") String orderId,
                                               @RequestParam("driverId") String[] driverIdList) throws  TransportProjectException {

        OrderEntity orderEntity = orderService.findById(orderId);

        List<DriverEntity> driverEntityList = new ArrayList<>();
        for (String s : driverIdList) {
            DriverEntity driverEntity = driverService.findById(s);
            driverEntity.setOrder(orderEntity);
            driverService.update(driverEntity);

            driverEntityList.add(driverEntity);
        }

        orderEntity.setDriverList(driverEntityList);
        orderEntity.assignDriverList(driverEntityList);

        orderService.validateOrderForDriverList(orderEntity);
        orderEntity.setStartDate(new Date());

        orderService.update(orderEntity);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("SuccessOrder");
        modelAndView.addObject("driverAll", orderService.findAll());
        modelAndView.addObject("orderId", orderId);
        modelAndView.addObject("truckId", truckId);
        modelAndView.addObject("currentTruckDutySize", truckService.findById(truckId).getDutySize());
        modelAndView.addObject("order", orderService.findById(orderId));

        return modelAndView;
    }

    @RequestMapping(value = "/order/delete", method = RequestMethod.GET)
    public String delete(Model model, @RequestParam("id") String id) {
        orderService.deleteById(id);
        return "redirect:/order/getAll";
    }

    @ModelAttribute("list")
    public List<OrderEntity> allOrder()
    {
        return orderService.findAll();
    }


}