package com.mkrt4an.controller;

/**
 * Created by 123 on 04.10.2016.
 */

import com.mkrt4an.dto.UserDTO;
import com.mkrt4an.entity.CargoEntity;
import com.mkrt4an.entity.DriverEntity;
import com.mkrt4an.entity.UserEntity;
import com.mkrt4an.service.CargoService;
import com.mkrt4an.service.CityService;
import com.mkrt4an.service.DriverService;
import com.mkrt4an.service.UserService;
import org.apache.commons.collections.comparators.BooleanComparator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;

@Controller
@SessionAttributes({"user", "cityAll"})
public class LoginController extends HttpServlet {

    @Autowired
    UserService userService;
    @Autowired
    DriverService driverService;

    private static final Logger log = Logger.getLogger(LoginController.class);

    @Autowired
    CityService cityService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main() {
        return new ModelAndView("login", "user", new UserDTO());
    }


    @RequestMapping(value = "/check-user", method = RequestMethod.POST)
    public ModelAndView checkUser(@ModelAttribute("user") UserDTO userDTO)
    {
        ModelAndView modelAndView = new ModelAndView();

        log.info("UserDTO: " + userDTO);
        log.info("UserDTO.isDriver: " + userDTO.isDriver());


        if (userDTO.isDriver()) {
            for(DriverEntity driverEntity : driverService.findAllDrivers()) {
                if (driverEntity.getId().toString().equals(userDTO.getName()) &&
                        "456".equals(userDTO.getPassword())) {

                    log.debug("driver found");
                    if(driverEntity.getOrder() == null ){
                        log.debug("driver has no order");
                        modelAndView.setViewName("redirect:/");
                        return modelAndView;
                    }

                    log.debug("driver has order");
                    modelAndView.setViewName("DriverInfo");
                    modelAndView.addObject("driver", driverEntity);
                    modelAndView.addObject("order", driverEntity.getOrder());
                    modelAndView.addObject("cityAll", cityService.findAll());
                    log.warn("Driver is logined");
                    return modelAndView;
                }
            }
        } else {
            for(UserEntity entity : userService.findAllUsers()) {
                if (entity.getName().equals(userDTO.getName()) &&
                        entity.getPassword().equals(userDTO.getPassword())) {

                    modelAndView.setViewName("redirect:/order/getAll");
                    modelAndView.addObject("user", entity);
                    modelAndView.addObject("cityAll", cityService.findAll());
                    log.warn("Manager is logined");
                    return modelAndView;
                }
            }
        }

        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }


}