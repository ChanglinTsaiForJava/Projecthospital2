package com.luv2code.springboot.cruddemo.controller;

import com.luv2code.springboot.cruddemo.entity.Customer;
import com.luv2code.springboot.cruddemo.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private CustomerService customerService;
    //送出後運作 從login.html來的
    @PostMapping("/controller/secure/login")
    //Model物件存在單個http request內部
    //HttpSession物件則可以在不同的request傳
    //下面的參數值login.html
    //Spring MVC 會根據 表單欄位的 name 屬性 自動將請求中的 username 和 password 參數 對應到方法參數。
    public String login(Model model, String username, String password, HttpSession session) {
        //接收htmlform資料
        //轉換資料
        //驗證資料
        //用map裝錯誤訊息 把html內有的參數運算後的錯誤html

        Map<String, String> errors = new HashMap<>();
        //將errors物件放入 並命名為"errors"
        model.addAttribute("errors", errors);
        System.out.println("startLoginController!!!");
        if(username==null || username.length()==0) {
            errors.put("key1", "請輸入帳號");
            System.out.println("this step lets we know username null or not");
        }
        if(password==null || password.length()==0) {
            errors.put("key2", "請輸入密碼");
            System.out.println("this step lets we know password null or not");
        }

        if(errors!=null&& !errors.isEmpty()) {
            return "/secure/login";
        }

//呼叫企業邏輯
        Customer loginData = customerService.login(username, password);

//根據企業邏輯職結果呼叫View
        if(loginData==null) {
            errors.put("key2", "登入失敗");
            return "/secure/login";
        } else {
//			model.addAttribute("user", bean);
            session.setAttribute("user", loginData.getId());
            return "redirect:/hospitals/list";
        }
    }
}
//String currentUser = session.getAttribute("user").toString();
//        if (currentUser != null) {
//        // 如果是新建資料，設定 creator
//        if (theHospital.getCreator() == null) {
//        theHospital.setCreator(currentUser);
//                theHospital.setModifier(currentUser);
//// 設定 modifier 為當前使用者
//            } else if(theHospital.getCreator() != null){
//        theHospital.setModifier(currentUser);
//            }}

