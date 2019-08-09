package com.codegym.service.impl;

import com.codegym.model.Province;
import com.codegym.repository.ProvinceRepository;
import com.codegym.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;

public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public Iterable<Province> findAll() {

        return provinceRepository.findAll();
    }

    @Override
    public void save(Province customer) {
        provinceRepository.save(customer);
    }

    @Override
    public Iterable<Province> findByName(String name) {
        return provinceRepository.findByName(name);
    }

    @Override
    public void deleteById(Long id) {
        provinceRepository.delete(id);
    }

    @Override
    public Province findById(Long id) {
        return provinceRepository.findOne(id);
    }
}