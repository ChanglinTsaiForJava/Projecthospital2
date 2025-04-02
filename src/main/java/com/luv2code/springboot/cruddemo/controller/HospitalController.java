package com.luv2code.springboot.cruddemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luv2code.springboot.cruddemo.entity.Hospital;

import com.luv2code.springboot.cruddemo.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hospitals")
public class HospitalController {

    private HospitalService hospitalService;

    @Autowired
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping("/list")
    public String listEmployees(Model model) {
        //get the employees from db
        List<Hospital> hospitals = hospitalService.findAll();
        //add to the spring model
        model.addAttribute("hospitals", hospitals);
        return"hospitals/list-hospitals";

    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Hospital hospital = new Hospital();
        model.addAttribute("hospital", hospital);
        return "hospitals/hospital-form";
    }

    @PostMapping("/save")
    public String saveHospital(@ModelAttribute("hospital") Hospital hospital, Model model) {
        // 如果醫院名稱有變動，才進行重複檢查
        if (hospital.getId() != 0) { // 只對已有ID的醫院進行名稱檢查
            Hospital existingHospital = hospitalService.findById(hospital.getId());

            // 檢查名稱是否有更改
            if (!hospital.getName().equals(existingHospital.getName())) {
                if (hospitalService.existsByName(hospital.getName())) {
                    model.addAttribute("errorMessage", "醫院名稱已存在，請使用不同的名稱。");
                    return "hospitals/hospital-form"; // 回到表單頁面顯示錯誤
                }
            }
        }
        // 如果名稱沒有重複或不需要檢查，則儲存醫院
        hospitalService.save(hospital);

        return "redirect:/hospitals/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("hospitalId") int theId, Model  Model) {

       Hospital hospital = hospitalService.findById(theId);

//      下面兩行加入是因為輸入表單得時候不會填寫創建者跟更動者 所以會讓兩者被null覆蓋
        Model.addAttribute("creatorName", hospital.getCreator());
        Model.addAttribute("modifierName", hospital.getModifier());
        Model.addAttribute("hospital", hospital);

//        這是用來確認原本的資料有加入model
        Map<String, Object> modelAttributes = Model.asMap();
        for (Map.Entry<String, Object> entry : modelAttributes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        return "hospitals/hospital-form";

    }

    @GetMapping("/delete")
    public String deleteHospital(@RequestParam("hospitalId") int theId) {
        hospitalService.deleteById(theId);
        return "redirect:/hospitals/list";
    }

    @PostMapping("/importJson")
    public String importHospitals(@RequestParam("file") MultipartFile file, Model model) {
        try {
            // 解析 JSON 檔案
            ObjectMapper objectMapper = new ObjectMapper();
            List<Hospital> hospitals = objectMapper.readValue(file.getInputStream(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Hospital.class));

            // 儲存至資料庫
            for (Hospital hospital : hospitals) {
                try {
                    // 嘗試儲存醫院資料
                    hospitalService.save(hospital);
                } catch (Exception e) {
                    // 當資料庫中有重複資料時捕獲錯誤並跳轉到首頁
                    return "redirect:/hospitals/list";  // 重定向到首頁
                }
                hospitalService.save(hospital);
            }

            model.addAttribute("message", "Hospitals imported successfully!");
        } catch (IOException e) {
            model.addAttribute("message", "Error while importing JSON: " + e.getMessage());
        }

        return "redirect:/hospitals/list"; // 匯入後返回顯示所有 hospital 的頁面
    }




}
