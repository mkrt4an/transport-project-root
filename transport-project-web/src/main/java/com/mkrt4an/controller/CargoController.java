package com.mkrt4an.controller;

/**
 * Created by 123 on 04.10.2016.
 */

import com.mkrt4an.entity.CargoEntity;
import com.mkrt4an.service.CargoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import java.util.List;

@Controller
public class CargoController extends HttpServlet {

    private static final Logger log = Logger.getLogger(CargoController.class);

    private final CargoService cargoService;

    @Autowired
    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @RequestMapping(value = "/cargo/getAll", method = RequestMethod.GET)
    public String getAllDrivers(Model model) {
        return "GetAllCargo";
    }

    @RequestMapping(value = {"/cargo/update", "/cargo/add"}, method = RequestMethod.GET)
    public String updateRedirect(Model model, @RequestParam(value = "id", required = false) Integer id) {

        if (id != null) {
            CargoEntity cargoEntity = cargoService.findById(id);
            model.addAttribute("cargo", cargoEntity);
        }

        return "AddOrUpdateCargo";
    }


    @RequestMapping(value = "/cargo/AddNewCargoServlet", method = RequestMethod.GET)
    public String updateRedirect(Model model,
                                 @RequestParam("id") Integer id,
                                 @RequestParam("name") String name,
                                 @RequestParam("weight") String weight,
                                 @RequestParam("status") String status) {

        if (id == null) {
            cargoService.addNew(name, weight, status);
        } else {
            cargoService.update(id, name, weight, status);
        }

        return "redirect:/cargo/getAll";
    }


    @RequestMapping(value = "/cargo/delete", method = RequestMethod.GET)
    public String delete(Model model, @RequestParam("id") String id) {
        cargoService.deleteById(id);
        return "redirect:/cargo/getAll";
    }


    /**
     * Bind all cargo list
     */
    @ModelAttribute("list")
    public List<CargoEntity> allCargo() {
        return cargoService.findAll();
    }


}