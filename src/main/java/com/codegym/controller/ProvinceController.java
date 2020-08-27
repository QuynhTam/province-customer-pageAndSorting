package com.codegym.controller;

import com.codegym.model.Province;
import com.codegym.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    @GetMapping("/provinces")
    public ModelAndView showList() {
        Iterable<Province> provinces = provinceService.findAll();
        ModelAndView modelAndView = new ModelAndView("/province/list");
        modelAndView.addObject("province", provinces);
        return modelAndView;
    }

    @GetMapping("/create-province")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("/province/create");
        modelAndView.addObject("province", new Province());
        return modelAndView;
    }

    @PostMapping("/create-province")
    public ModelAndView create(@ModelAttribute("province") Province province) {
        provinceService.save(province);
        ModelAndView modelAndView = new ModelAndView("/province/create");
        modelAndView.addObject("province", province);
        modelAndView.addObject("message", "Created successfully !");
        return modelAndView;
    }

    @GetMapping("/edit-province/{id}")
    public ModelAndView showEdit(@PathVariable Long id) {
        Province province = provinceService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/province/edit");
        modelAndView.addObject("province", province);
        return modelAndView;
    }

    @PostMapping("/edit-province")
    public ModelAndView edit(@ModelAttribute("province") Province province) {
        provinceService.save(province);
        ModelAndView modelAndView = new ModelAndView("/provicne/edit");
        modelAndView.addObject("province", province);
        modelAndView.addObject("message", "Edited successfully !");
        return modelAndView;
    }

    @GetMapping("/delete-province/{id}")
    public ModelAndView showDelete(@PathVariable Long id) {
        Province province = provinceService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/province/delete");
        modelAndView.addObject("province", province);
        return modelAndView;
    }

    @PostMapping("/delete-province")
    public String delete(@ModelAttribute("province") Province province) {
        provinceService.delete(province.getId());
        return "redirect:provinces";
    }

//    @GetMapping("/view-province")
//    public ModelAndView showView() {
//        ModelAndView modelAndView = new ModelAndView("/province/view");
//
//    }
}
