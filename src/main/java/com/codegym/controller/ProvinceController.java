package com.codegym.controller;

import com.codegym.model.Province;
import com.codegym.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    @GetMapping("/provinces")
    public ModelAndView listProvinces() {

        Iterable<Province> provinces = provinceService.findAll();

        ModelAndView modelAndView = new ModelAndView("/province/list");
        modelAndView.addObject("provinces", provinces);
        modelAndView.addObject("delete-message", "deleted.");

        return modelAndView;
    }

    @GetMapping("add-province")
    public ModelAndView createFormProvince() {
        ModelAndView modelAndView = new ModelAndView("/province/create");
        modelAndView.addObject("province", new Province());
        return modelAndView;
    }

    @PostMapping("save-province")
    public ModelAndView saveCustomer(@ModelAttribute Province province) {

        provinceService.save(province);

        ModelAndView modelAndView = new ModelAndView("/province/create");
        modelAndView.addObject("province", new Province());
        modelAndView.addObject("message", "created.");
        return modelAndView;
    }

    @GetMapping("search-province")
    public ModelAndView searchCustomer(@RequestParam("name") String name) {

        Iterable<Province> provinces = provinceService.findByName(name);

        ModelAndView modelAndView = new ModelAndView("/province/search");
        modelAndView.addObject("provinces", provinces);
        return modelAndView;
    }

    @GetMapping("delete-province/{id}")
    public ModelAndView deleteProvince(@PathVariable("id") Long id) {

        provinceService.deleteById(id);

        ModelAndView modelAndView = new ModelAndView("/province/list");
        modelAndView.addObject("delete-message", "deleted.");
        return modelAndView;

    }

}