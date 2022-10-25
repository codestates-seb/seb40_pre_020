package com.SEB_Pre_020.demo.entity;
//임시//
public class Member {


    private String name;
    private Long email;
    private String password;

    public Member(String name, Long email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getEmail() {
        return email;
    }

    public void setEmail(Long email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
