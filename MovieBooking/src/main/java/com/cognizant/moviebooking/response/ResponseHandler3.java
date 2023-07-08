//package com.cognizant.moviebooking.response;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class ResponseHandler3 {
//
//    public static ResponseEntity<Object> generateResponse(String message, HttpStatus statusCode, Object responseObj)
//    {
//        Map<String, Object> mapObj = new HashMap<String, Object>();
//
//        mapObj.put("Message", message);
//        mapObj.put("Status Code", statusCode);
//        mapObj.put("Payload" , responseObj);
//        return new ResponseEntity<Object>(mapObj, statusCode);
//
//    }
//
//}
