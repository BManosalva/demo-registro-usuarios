package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ServiceException;
import com.example.demo.models.entity.User;
import com.example.demo.request.UpdateUserRequest;
import com.example.demo.request.UserRequest;
import com.example.demo.response.RegisterUserResponse;
import com.example.demo.response.UserUpdateResponse;
import com.example.demo.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService service;

	/**
	 * 
	 * @param token {@link String}
	 * @return {@link ResponseEntity}
	 */
	@ApiOperation("Endpoint para listar usuarios.")
	@GetMapping("/v1/users/list")
	public ResponseEntity<List<User>> listUsers(
			@RequestHeader(value = "Authorization", required=true) String token) throws ServiceException{
			LOGGER.info("Initiating service.");
			final List<User> userList = service.findAllUsers(token);
			LOGGER.info("End of user list service GET.");
			return ResponseEntity.status(HttpStatus.OK).body(userList);
	}// Method Closure

	/**
	 * 
	 * @param token {@link String}
	 * @param id {@link Long}
	 * @return {@link ResponseEntity}
	 * @throws ServiceException
	 */
	@ApiOperation("Endpoint para buscar usuario.")
	@GetMapping("/v1/users/{user-id}")
	public ResponseEntity<User> findUser(
			@RequestHeader(value = "Authorization", required=true) String token,
			@PathVariable(value = "user-id", required=true) Long id) throws ServiceException{
			LOGGER.info("Initiating service.");
			final User user = service.findUser(token, id);
			LOGGER.info("End of find user service GET."); 
			return ResponseEntity.status(HttpStatus.OK).body(user);
	}// Method Closure
	
	/**
	 * 
	 * @param token {@link String}
	 * @param request {@link UserRequest}
	 * @return {@link ResponseEntity}
	 * @throws ServiceException
	 */
	@ApiOperation("Endpoint para crear usuarios.")
	@PostMapping("/v1/users/create-user")
	public ResponseEntity<RegisterUserResponse> createUser(
			@RequestHeader(value = "Authorization", required=true) String token,
			@RequestBody UserRequest request) throws ServiceException{
			LOGGER.info("Initiating service.");
			final RegisterUserResponse response = service.createUser(token, request);
			LOGGER.info("End of user service POST.");
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}// Method Closure
	
	/**
	 * 
	 * @param token {@link String}
	 * @param request {@link UserUpdateRequest}
	 * @return {@link ResponseEntity}
	 * @throws ServiceException
	 */
	@ApiOperation("Endpoint para modificar usuario.")
	@PutMapping("/v1/users/update-user")
	public ResponseEntity<UserUpdateResponse> updateUser(
			@RequestHeader(value = "Authorization", required=true) String token,
			@RequestBody UpdateUserRequest request) throws ServiceException{
			LOGGER.info("Initiating service.");
			final UserUpdateResponse response = service.updateUser(token, request);
			LOGGER.info("End of update user service PUT.");
			return ResponseEntity.status(HttpStatus.OK).body(response);
	}// Method Closure


	/////////////

	/**
	 * 
	 * @param token {@link String}
	 * @param id {@link Long}
	 * @return {@link ResponseEntity}
	 * @throws ServiceException
	 */
	@ApiOperation("Endpoint para buscar usuario por email.")
	@GetMapping("/v1/users/usuarios/{email}")
	public ResponseEntity<User> findUserByEmail(
			@RequestHeader(value = "Authorization", required=true) String token,
			@PathVariable(value = "email", required=true) String email) throws ServiceException{
			LOGGER.info("Initiating service.");
			final User user = service.findByEmail(token, email);
			LOGGER.info("End of find user service GET.");
			return ResponseEntity.status(HttpStatus.OK).body(user);
	}// Method Closure
}// Class Closure