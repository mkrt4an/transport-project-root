package com.mkrt4an.controller;

import com.mkrt4an.entity.TruckEntity;
import com.mkrt4an.service.TruckService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import java.util.List;

@Controller
public class TruckController extends HttpServlet {

    private static final Logger log = Logger.getLogger(TruckController.class);

    private final TruckService truckService;

    @Autowired
    public TruckController(TruckService truckService) {
        this.truckService = truckService;
    }

    @RequestMapping(value = "/truck/getAll", method = RequestMethod.GET)
    public String getAllDrivers(Model model) {

        return "GetAllTrucks";
    }

    @RequestMapping(value = {"/truck/update", "/truck/add"}, method = RequestMethod.GET)
    public String AddOrUpdateTruck(Model model, @RequestParam(value = "id", required = false) String id) {

        if (id != null) {
            TruckEntity truckEntity = truckService.findById(id);
            model.addAttribute("truck", truckEntity);
        }

        return "AddOrUpdateTruck";
    }

    @RequestMapping(value = "/truck/AddNewTruckServlet", method = RequestMethod.GET)
    public String AddOrUpdateTruckRedirect (Model model,
                                 @RequestParam("id") Integer id,
                                 @RequestParam("dutySize") Integer dutySize,
                                 @RequestParam("capacity") Integer capacity,
                                 @RequestParam("status") Integer status,
                                 @RequestParam("regNumber") String regNumber,
                                 @RequestParam("city") Integer cityId) {

        if (id == null) {
            truckService.addNew(dutySize, capacity, status, regNumber, cityId);
            log.info("Truck successfully added");
        } else {
            truckService.update(id, dutySize, capacity, status, regNumber, cityId);
            log.info("Truck successfully updated");
        }

        return "redirect:/truck/getAll";
    }

    @RequestMapping(value = "/truck/delete", method = RequestMethod.GET)
    public String delete(Model model, @RequestParam("id") String id) {
        truckService.deleteById(id);
        return "redirect:/truck/getAll";
    }

    @ModelAttribute("list")
    public List<TruckEntity> allTruck()
    {
        return truckService.findAllTrucks();
    }



}