//package com.exam.service.impl;
//
//import com.exam.model.User;
//import com.exam.repo.UserRepository;
//import com.exam.service.forgot;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class forgotimpl implements forgot {
//
//    @Autowired
//    private UserRepository userRepository;
//
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Override
//    public User updatePassword(String userName, User user) {
//        User byUserName = userRepository.findByUserName(userName);
//        if(byUserName!=null)
//        {
//            byUserName.setEmail(user.getEmail());
//            byUserName.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
//            User user1 = userRepository.saveAndFlush(byUserName);
//            return user1;
//        }
//        return null;
//    }
//}
