package com.cognizant.moviebooking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class UserDTO{

         private String username;
         private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //        @Id
//        @GeneratedValue(strategy = GenerationType.AUTO)
//        private int id;
//        private String userRole;
//        private String userName;
//        private String email;
//        private String password;
//        private String securityQuestion;
//
//        @JsonIgnore
//        private String answer;
//
//        public String getAnswer() {
//            return answer;
//        }
//
//        public void setAnswer(String answer) {
//            this.answer = answer;
//        }
//
//        public String getUserRole() {
//            return userRole;
//        }
//
//        public void setUserRole(String userRole) {
//            this.userRole = userRole;
//        }
//
//        public String getEmail() {
//            return email;
//        }
//
//        public void setEmail(String email) {
//            this.email = email;
//        }
//
//        public String getSecurityQuestion() {
//            return securityQuestion;
//        }
//
//        public void setSecurityQuestion(String securityQuestion) {
//            this.securityQuestion = securityQuestion;
//        }
//
//
//
//        public UserDTO() {
//        }
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public String getUserName() {
//            return userName;
//        }
//
//        public void setUserName(String userName) {
//            this.userName = userName;
//        }
//
//        public String getPassword() {
//            return password;
//        }
//
//        public void setPassword(String password) {
//            this.password = password;
//        }
}