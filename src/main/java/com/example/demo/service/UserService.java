package com.example.demo.service;

import java.util.List;

import com.example.demo.models.entity.User;

import request.UpdateUserRequest;
import request.UserRequest;
import response.UserDeleteResponse;
import response.UserUpdateResponse;
import response.RegisterUserResponse;

public interface UserService {
	
	public RegisterUserResponse createUser(final String token, final UserRequest request);

	public List<User> findAllUsers();

	public User findUser(final Long id);

	public UserUpdateResponse updateUser(UpdateUserRequest request);

	public UserDeleteResponse deleteUser(Long id);

	public User findByEmail(String email);

}// Interface Closure