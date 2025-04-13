package com.gotocode.nota.dto;

import com.gotocode.nota.entity.User;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String phoneNumber;
    private String name;
    private String threadId;

    public UserDTO() {
    }

    public UserDTO(Long id, String phoneNumber, String name, String threadId) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.threadId = threadId;
    }

    public UserDTO(User entity) {
        id = entity.getId();
        phoneNumber = entity.getPhoneNumber();
        name = entity.getName();
        threadId = entity.getThreadId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }
}
