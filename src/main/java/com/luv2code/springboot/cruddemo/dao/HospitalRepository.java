package com.luv2code.springboot.cruddemo.dao;


import com.luv2code.springboot.cruddemo.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
    Optional<Hospital> findByName(String name); // 檢查名稱是否存在
}
    // that's it ... no need to write any code LOL!


