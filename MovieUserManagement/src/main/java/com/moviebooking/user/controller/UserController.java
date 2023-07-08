package com.moviebooking.user.controller;

import com.moviebooking.user.dto.ForgotPassword;
import com.moviebooking.user.helper.UserFoundException;
import com.moviebooking.user.helper.UserNotFoundException;
import com.moviebooking.user.model.Role;
import com.moviebooking.user.model.User;
import com.moviebooking.user.model.UserRole;
import com.moviebooking.user.response.ResponseHandler;
import com.moviebooking.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //creating user
    @PostMapping("/")
    //data user me aaega
    public User createUser(@RequestBody User user) throws Exception {

      //  user.setProfile("default.org");
        //encoding password with bcrypt password encoder
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));



        Set<UserRole> roles=new HashSet<>();
        Role role=new Role();
        role.setRoleId(45);
        role.setRoleName("NORMAL");

        UserRole userRole =new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        roles.add(userRole);
        return this.userService.createUser(user, roles) ;
    }

    @GetMapping("/{userName}")
    public User getUser(@PathVariable("userName") String userName)
    {
        return this.userService.getUser(userName);
    }

    //delete the user by id
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long userId)
    {
        this.userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK) ;
    }

    //update password of user by username
    @PutMapping("/forgot/{email}")
    public ResponseEntity<?> forgotPassword(@PathVariable("email") String email, @RequestBody ForgotPassword forgotPassword)
    {
        if(userService.updatePassword(email,forgotPassword))
        {
         //   return new ResponseEntity<String>("Password Updated Successfully",HttpStatus.OK);
            return ResponseHandler.generateResponse("Password Updated Successfully",HttpStatus.OK);
        }
       else
        //return new ResponseEntity<String>("User not Exists",HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseHandler.generateResponse("User not Exists",HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserNotFoundException ex){
        return ResponseEntity.ok(ex.getMessage());
    }

    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserFoundException ex){
        return ResponseEntity.ok(ex.getMessage());
    }

}
