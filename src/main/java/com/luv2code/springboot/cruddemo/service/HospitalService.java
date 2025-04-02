package com.luv2code.springboot.cruddemo.service;


import com.luv2code.springboot.cruddemo.entity.Hospital;

import java.util.List;

public interface HospitalService {

    List<Hospital> findAll();

    Hospital findById(int theId);

    Hospital save(Hospital theHospital);

    void deleteById(int theId);

    boolean existsByName(String name);
    




}
