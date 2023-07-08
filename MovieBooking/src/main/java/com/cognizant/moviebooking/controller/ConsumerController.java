package com.cognizant.moviebooking.controller;

import com.cognizant.moviebooking.model.UserDTO;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("call/consumer")
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

//    @PostMapping("/login")
//    public ResponseEntity<?> consumeLogin(@RequestBody UserDTO userdto) throws RestClientException, Exception {
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        //HttpEntity<String> requestEntity = new HttpEntity<>(headers);
//        HttpEntity<UserDTO> requestEntity = new HttpEntity<>(userdto, headers);
//
//        ResponseEntity<String> responseEntity = restTemplate.exchange(
//                "http://localhost:8082/auth/v1/login", HttpMethod.POST, requestEntity, String.class);
//
//        if (responseEntity.getStatusCode() == HttpStatus.OK) {
//            String jwtToken = responseEntity.getBody();
//            // Save the token for further use
//        }
//        else {
//            System.out.println("Fail");
//        }
//
//        return responseEntity;
//    }


    @PostMapping("/login")
    public ResponseEntity<?> consumeLogin(@RequestBody UserDTO userdto) throws RestClientException, Exception {
        // String baseUrl="http://localhost:8082/auth/v1/login";
// API consumption.. actual api is hidden -not exposed

        String baseUrl = "http://localhost:8082/generate-token";
    	//String baseUrl = "http://52.36.187.62:8082/generate-token";
    	

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserDTO> requestEntity = new HttpEntity<>(userdto, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(baseUrl, HttpMethod.POST, requestEntity, String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String jwtToken = responseEntity.getBody();
        } else {
            System.out.println("Fail");
        }

        //System.out.println(responseEntity);

        return responseEntity;

//        RestTemplate restTemplate = new RestTemplate();
//
//        ResponseEntity<Map<String, String>> result;


//        try
//        {
//            result= restTemplate.exchange(baseUrl, HttpMethod.POST, getHeaders(userdto), new ParameterizedTypeReference<Map<String,String>>(){});
//        }
//        catch(Exception e)
//        {
//            return new ResponseEntity<String>("Login failed", HttpStatus.UNAUTHORIZED);
//        }
//
//        return new ResponseEntity<Map<String, String>>(result.getBody(), HttpStatus.UNAUTHORIZED);
//    }
//
//    private static HttpEntity<UserDTO> getHeaders(UserDTO userdto)
//    {
//        HttpHeaders headers = new HttpHeaders();
//
//        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
//        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
//        return new HttpEntity<UserDTO>(userdto,headers);
    }

    private static HttpEntity<UserDTO> getHeaders(UserDTO userdto)
    {HttpHeaders headers = new HttpHeaders();

        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<UserDTO>(userdto,headers);
    }


}
