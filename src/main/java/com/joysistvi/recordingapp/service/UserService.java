package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    boolean registerUser(String username, String password);

    User login(String username, String password);

    boolean deleteUser(int id);
}