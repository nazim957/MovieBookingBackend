package com.moviebooking.user.service;

import com.moviebooking.user.dto.ForgotPassword;
import com.moviebooking.user.model.User;
import com.moviebooking.user.model.UserRole;

import java.util.Set;

public interface UserService {

    //creating user
    public User createUser(User user, Set<UserRole> userRole) throws Exception;

    //get user by username
    public User getUser(String userName);

    //delete user by id
    public void deleteUser(Long userId);

    //update user by username
    public boolean updatePassword(String email, ForgotPassword forgotPassword);
}
