package com.example.demo.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.entity.User;
import com.example.demo.models.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.utils.MessageUtils;
import com.example.demo.utils.Utils;

import exception.ServiceException;
import lombok.RequiredArgsConstructor;
import request.UpdateUserRequest;
import request.UserRequest;
import response.RegisterUserResponse;
import response.UserDeleteResponse;
import response.UserUpdateResponse;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	// private UserRepository userRepository;

	private final Utils utils;

	@Autowired
	UserRepository userRepo;

	@Override
	@Transactional
	public RegisterUserResponse createUser(final String token, final UserRequest request) {
		try {
			LOGGER.info("Inicio de servicio crear usuario.");

			// Buscamos si ya existe usuario con email ingresado.
			final var searchUser = userRepo.findByEmail(request.getEmail());
			LOGGER.info("Usuario db {}", searchUser);

			// Validamos si email ya existe
			final var response = new RegisterUserResponse();

			if (null == userRepo.findByEmail(request.getEmail())) {

				// Validamos estructura de email y password.
				Utils.emailValidator(request.getEmail());
				Utils.passwordValidator(request.getPassword());
				
				

				Date date = new Date();
				LOGGER.info("Date: {}", date);

				final var user = new User();
				user.setName(request.getName());
				user.setEmail(request.getEmail());
				user.setPassword(request.getPassword());
				user.setIsActive(true);
				user.setCreatedAt(date);
				user.setModified(date);
				user.setLastLogin(date);
				user.setPhones(request.getPhones());

				// Ejecución Repository
				final var createdUser = userRepo.save(user);
				LOGGER.info("Usuario creado: {}", createdUser);

				
				response.setId(createdUser.getId().toString());
				response.setIsActive(createdUser.getIsActive());
				response.setCreated(createdUser.getCreatedAt());
				response.setModified(createdUser.getModified());
				response.setLastLogin(createdUser.getLastLogin());

				//agregar modificado
				response.setLastLogin(createdUser.getLastLogin());

				LOGGER.info("Response: {}", response);

			} else {
				LOGGER.error("Error email {}", MessageUtils.EXISTING_EMAIL);
				throw new ServiceException(String.valueOf(HttpStatus.CONFLICT.value()), MessageUtils.EXISTING_EMAIL);
			}

			// Metodo para validar conflicto existente de usuario

			return response;

		} catch (ServiceException e) {
			LOGGER.error("Service Error : {} ", e.getLocalizedMessage());
			throw e;
		} catch (Exception e) {
			LOGGER.info("Error : {}", e.getLocalizedMessage());
			// throw new ServiceExpection(String.valueOf(HttpStatus.CONFLICT.value()),
			// e.getMessage());
		}
		return null;
	}// Method Closure

	@Override
	public List<User> findAllUsers() {
		return (List<User>) userRepo.findAll();
	}// Method Closure

	@Override
	public User findUser(final Long id) {

		/*
		 * LOGGER.info("Inicio de busqueda de usuario de id {}.", id);
		 * final var user = utils.findUser(id);
		 * return user;
		 */
		try {
			LOGGER.info("Inicio de busqueda de usuario de id {}.", id);
			final var user = utils.findUser(id);

			return user;
		} catch (ServiceException e) {
			LOGGER.error("Service error : {}", e.getLocalizedMessage());
		}
		return null;

	}// Method Closure

	@Override
	public UserUpdateResponse updateUser(UpdateUserRequest request) {
		// TODO Auto-generated method stub

		final var user = userRepo.findById(request.getId()).orElse(null);

		Date date = new Date();
		LOGGER.info("Date: {}", date);

		final var userUpdate = new User();
		userUpdate.setId(user.getId());
		userUpdate.setName(request.getName());
		userUpdate.setEmail(request.getEmail()); // TODO: Crear validador de email
		userUpdate.setPassword(request.getPassword());
		userUpdate.setPhones(request.getPhones());

		userUpdate.setCreatedAt(user.getCreatedAt());// Mantiene fecha de creación.
		userUpdate.setModified(date);
		userUpdate.setLastLogin(user.getCreatedAt());// Mantiene fecha de ultimo login.

		// Se actualiza el usuario
		final var update = userRepo.save(userUpdate);

		// Mapeo resultado
		final var updateUser = new UserUpdateResponse();
		updateUser.setId(update.getId());
		updateUser.setCreated(update.getCreatedAt());
		updateUser.setModified(update.getModified());
		updateUser.setLastLogin(update.getLastLogin());
		// updateUser.setToken(update);
		updateUser.setIsactive(update.getIsActive());
		updateUser.setPhones(update.getPhones());
		updateUser.setMessage(MessageUtils.UPDATE_USER_MESSAGE);
		return updateUser;
	}// Method Closure

	@Override
	public UserDeleteResponse deleteUser(Long id) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepo.findByEmail(email);
	}

}// Class Closure