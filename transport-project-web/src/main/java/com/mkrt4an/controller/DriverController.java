package com.mkrt4an.controller;

/**
 * Created by 123 on 04.10.2016.
 */

import com.mkrt4an.entity.DriverEntity;
import com.mkrt4an.exception.TransportProjectException;
import com.mkrt4an.service.DriverService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import java.util.List;

@Controller
public class DriverController extends HttpServlet {

    private static final Logger log = Logger.getLogger(DriverController.class);

    private final DriverService driverService;

    @Inject
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @RequestMapping(value = "/driver/getAll", method = RequestMethod.GET)
    public String getAllDrivers(Model model) {

        return "GetAllDrivers";
    }

    @RequestMapping(value = {"/driver/update", "/driver/add"}, method = RequestMethod.GET)
    public String AddOrUpdateDriver(Model model, @RequestParam(value = "id", required = false) Integer id) {

        if (id != null) {
            DriverEntity driverEntity = driverService.findById(id);
            model.addAttribute("driver", driverEntity);
        }

        return "AddOrUpdateDriver";
    }


    @RequestMapping(value = "/driver/AddNewDriverServlet", method = RequestMethod.GET)
    public String AddOrUpdateDriverRedirect(Model model,
                                 @RequestParam("id") Integer id,
                                 @RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName,
                                 @RequestParam("workedHours") Integer workedHours,
                                 @RequestParam("status") Integer status,
                                 @RequestParam("city") Integer cityId) throws TransportProjectException{

        if (id == null) {
            driverService.addNew(firstName, lastName, workedHours, status, cityId);
            log.info("Driver successfully added");
        } else {
            driverService.update(id, firstName, lastName, workedHours, status, cityId);
            log.info("Driver successfully updated");
        }

        return "redirect:/driver/getAll";
    }


    @RequestMapping(value = "/driver/delete", method = RequestMethod.GET)
    public String delete(Model model, @RequestParam("id") String id) {
        driverService.deleteById(id);
        return "redirect:/driver/getAll";
    }

    @ModelAttribute("list")
    public List<DriverEntity> allDriver()
    {
        return driverService.findAllDrivers();
    }


}