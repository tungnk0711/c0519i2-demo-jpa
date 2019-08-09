package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.service.CustomerService;
import com.codegym.service.ProvinceService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProvinceService provinceService;


    @ModelAttribute("provinces")
    public Iterable<Province> provinces(){
        return provinceService.findAll();
    }

    @GetMapping("/customers")
    public ModelAndView listCustomers() {

        Iterable<Customer> customers = customerService.findAll();

        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customers);
        modelAndView.addObject("delete-message", "deleted.");

        return modelAndView;
    }

    @GetMapping("add-customer")
    public ModelAndView createFormCustomer() {
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("save-customer")
    public ModelAndView saveCustomer(@Validated @ModelAttribute("customer") Customer customer, BindingResult bindingResult) {

        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/customer/create");
            //modelAndView.addObject("customer", new Customer());
            return modelAndView;
        }

        customerService.save(customer);

        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("message", "created.");
        return modelAndView;
    }

    @GetMapping("search")
    public ModelAndView searchCustomer(@RequestParam("name") String name){

        Iterable<Customer> customers = customerService.findByName(name);

        ModelAndView modelAndView = new ModelAndView("/customer/search");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("delete-customer/{id}")
    public ModelAndView deleteProduct(@PathVariable("id") Long id){

        customerService.deleteById(id);

        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("delete-message", "deleted.");
        return modelAndView;

    }

}
