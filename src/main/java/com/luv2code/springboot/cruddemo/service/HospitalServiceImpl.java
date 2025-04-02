package com.luv2code.springboot.cruddemo.service;


import com.luv2code.springboot.cruddemo.dao.HospitalRepository;

import com.luv2code.springboot.cruddemo.entity.Hospital;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalServiceImpl implements HospitalService {

    private HospitalRepository hospitalRepository;
    private HttpSession session;

    @Autowired
    public HospitalServiceImpl(HospitalRepository hospitalRepository, HttpSession session) {
        this.hospitalRepository = hospitalRepository;
        this.session = session;
    }

    @Override
    public List<Hospital> findAll() {
        return hospitalRepository.findAll();
    }

    @Override
    public Hospital findById(int theId) {
        Optional<Hospital> result = hospitalRepository.findById(theId);

        Hospital theHospital = null;

        if (result.isPresent()) {
            theHospital = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find the hospital you are looking for - " + theId);
        }

        return theHospital;
    }

    @Override
    public Hospital save(Hospital theHospital) {
        String currentUser = (String) session.getAttribute("user");

        if (currentUser != null) {
            // 如果是新建資料，設定 creator，並且初始化 modifier
            if (theHospital.getId() == 0) { // 判斷是否是新建
                theHospital.setCreator(currentUser);
            } else {
                // 確保 creator 不會被覆蓋
                Hospital existingHospital = hospitalRepository.findById(theHospital.getId()).orElse(null);
                if (existingHospital != null) {
                    theHospital.setCreator(existingHospital.getCreator());
                }
            }
            // 更新 modifier
            theHospital.setModifier(currentUser);
        }
            return hospitalRepository.save(theHospital);
    }

    @Override
    public void deleteById(int theId) {
        hospitalRepository.deleteById(theId);
    }

    @Override
    public boolean existsByName(String name) {
        return hospitalRepository.findByName(name).isPresent();
    }
}






