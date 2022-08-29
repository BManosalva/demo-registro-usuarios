package com.example.demo.service;

import java.util.List;

import com.example.demo.models.entity.User;
import com.example.demo.request.UpdateUserRequest;
import com.example.demo.request.UserRequest;
import com.example.demo.response.RegisterUserResponse;
import com.example.demo.response.UserDeleteResponse;
import com.example.demo.response.UserUpdateResponse;

public interface UserService {
	
	public RegisterUserResponse createUser(String token, UserRequest request);

	public List<User> findAllUsers(String token);

	public User findUser(String token, Long id);

	public UserUpdateResponse updateUser(String token, UpdateUserRequest request);

	public UserDeleteResponse deleteUser(String token, Long id);

	public User findByEmail(String token, String email);

}// Interface Closure