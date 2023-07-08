package com.moviebooking.user.dto;

public class ForgotPassword {

    private String securityQuestion;

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    private String securityQuestionAns;
    private String password;

    public ForgotPassword() {
    }

    public String getSecurityQuestionAns() {
        return securityQuestionAns;
    }

    public void setSecurityQuestionAns(String securityQuestionAns) {
        this.securityQuestionAns = securityQuestionAns;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
