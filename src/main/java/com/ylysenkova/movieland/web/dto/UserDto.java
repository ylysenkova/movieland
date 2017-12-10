package com.ylysenkova.movieland.web.dto;


import com.ylysenkova.movieland.model.User;

public class UserDto {
    private int id;
    private String nickName;

    public UserDto(User user) {
        this.id = user.getId();
        this.nickName = user.getName();
    }

    public UserDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
