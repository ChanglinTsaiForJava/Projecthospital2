package com.luv2code.springboot.cruddemo.service;


import com.luv2code.springboot.cruddemo.dao.CustomerRepository;
import com.luv2code.springboot.cruddemo.entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer login(String username, String password) {
        if(username!=null && password!=null && password.length()!=0) {
            Optional<Customer> optional = this.customerRepository.findById(username);
            if(optional.isPresent()) {
                Customer  bean = optional.get();
                String pass = bean.getPassword();	//資料庫抓出
                String temppass = password;	//使用者輸入
                if(Objects.equals(pass, temppass)) {
                    return bean;
                }
            }
        }
        return null;
    }
}
