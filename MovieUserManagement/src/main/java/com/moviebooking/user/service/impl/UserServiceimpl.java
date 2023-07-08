package com.moviebooking.user.service.impl;

import com.moviebooking.user.dto.ForgotPassword;
import com.moviebooking.user.helper.UserFoundException;
import com.moviebooking.user.model.User;
import com.moviebooking.user.model.UserRole;
import com.moviebooking.user.repo.RoleRepository;
import com.moviebooking.user.repo.UserRepository;
import com.moviebooking.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserRepository userrepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

    //creating user
     @Override
    public User createUser(User user, Set<UserRole> userRole) throws Exception {

        User local=this.userrepository.findByUserName(user.getUserName());
        User local2 = this.userrepository.findByEmail(user.getEmail());
        if(local!=null)
        {
            System.out.println("User is already there!!");
            throw new UserFoundException();
        }
        if(local2!=null)
        {
            System.out.println("Email Id Alraedy Exists");
            throw new UserFoundException();
        }
        else {
            //user create
            for(UserRole ur:userRole)
            {
                roleRepository.save(ur.getRole()); //save roles
            }

            user.getUserRoles().addAll(userRole);  //assign all roles to user
            local = this.userrepository.save(user);

        }
        return local;
    }

    //getting user by username
    @Override
    public User getUser(String userName) {
        return this.userrepository.findByUserName(userName);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userrepository.deleteById(userId);
    }

    @Override
    public boolean updatePassword(String email, ForgotPassword forgotPassword) {
        User byEmail = this.userrepository.findByEmail(email);
        System.out.println("_________");
        System.out.println(byEmail);
        if(byEmail!=null)
        {
            System.out.println("Inside password ");
            if(byEmail.getSecurityQuestion().equals(forgotPassword.getSecurityQuestion()) && byEmail.getAnswer().equals(forgotPassword.getSecurityQuestionAns())) {
                System.out.println("Inside @@@@@@@");
                byEmail.setPassword(this.bCryptPasswordEncoder.encode(forgotPassword.getPassword()));
                userrepository.saveAndFlush(byEmail);
                return true;
            }
        }
        System.out.println("outside password ");
        return false;
    }
}
