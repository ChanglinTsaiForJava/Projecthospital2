//package tw.com.ispan.controller;
//
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import tw.com.ispan.domain.ProductBean;
//import tw.com.ispan.service.ProductService;
//import tw.com.ispan.util.DatetimeConverter;
//
//@RestController
//public class ProductAjaxController {
//    @Autowired
//    private ProductService productService;
//    @GetMapping("`/ajax/pages/products/{id}`")
//    public String findById(@PathVariable(name="id")Integer id) {
//        JSONObject responseBody = new JSONObject();
//        JSONArray array = new JSONArray();
//        if(id!=null){
//            ProductBean productBean = productService.findById(id);
//            if(productBean!=null){
//                String make = DatetimeConverter.toString(productBean.getMake(), "yyyy-MM-dd");
//                JSONObject item = new JSONObject()
//                        .put("id", productBean.getId())
//                        .put("name", productBean.getName())
//                        .put("price", productBean.getPrice())
//                        .put("make", make)
//                        .put("expire", productBean.getExpire());
//                array = array.put(item);
//            }
//        }
//        responseBody.put("list", array);
//        return responseBody.toString();
//    }