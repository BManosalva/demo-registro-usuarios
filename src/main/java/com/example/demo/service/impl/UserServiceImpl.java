package com.example.demo.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.ServiceException;
import com.example.demo.models.entity.User;
import com.example.demo.models.repository.UserRepository;
import com.example.demo.request.UpdateUserRequest;
import com.example.demo.request.UserRequest;
import com.example.demo.response.RegisterUserResponse;
import com.example.demo.response.UserDeleteResponse;
import com.example.demo.response.UserUpdateResponse;
import com.example.demo.service.UserService;
import com.example.demo.utils.MessageUtils;
import com.example.demo.utils.Utils;

import lombok.RequiredArgsConstructor;

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

			// Se valida estructura de email y password ingresados.
			utils.emailValidator(request.getEmail());
			utils.passwordValidator(request.getPassword());
			LOGGER.info("Formatos de email y password validos.");

			final var response = new RegisterUserResponse();
			
			if (null == userRepo.findByEmail(request.getEmail())) {
				Date date = new Date();
				LOGGER.info("Date: {}", date);

				final var user = new User();
				user.setId(Utils.generateID());
				user.setName(request.getName());
				user.setEmail(request.getEmail());
				user.setPassword(request.getPassword());
				user.setIsActive(true);
				user.setCreatedAt(date);
				user.setModified(date);
				user.setLastLogin(date);
				user.setPhones(request.getPhones());

				// Ejecución Repository.
				final var createdUser = userRepo.save(user);
				LOGGER.info("Usuario creado: {}", createdUser);

				// Se mapea entity en clase response.
				response.setId(createdUser.getId().toString());
				response.setIsActive(createdUser.getIsActive());
				response.setCreated(createdUser.getCreatedAt());
				response.setModified(createdUser.getModified());
				response.setLastLogin(createdUser.getLastLogin());
				response.setLastLogin(createdUser.getLastLogin());
				response.setToken(token);
				LOGGER.info("Response: {}", response);

			} else {
				LOGGER.error("Error ingreso {}", MessageUtils.EXISTING_EMAIL);
            	throw new ServiceException(String.valueOf(HttpStatus.CONFLICT.value()), MessageUtils.EXISTING_EMAIL);
			}

			// Se retorna objeto response.
			return response;

		} catch (ServiceException e) {
			LOGGER.error("Service Error : {} {} ", e.getCode(), e.getLocalizedMessage());
			throw e;
		} catch (Exception e) {
			LOGGER.info("Error : {}", e.getLocalizedMessage());
			throw new ServiceException(String.valueOf(HttpStatus.BAD_REQUEST.value()), e.getMessage());
		}
	}// Method Closure

	@Override
	public List<User> findAllUsers(final String token) {
		try {
			LOGGER.info("Inicio de servicio lista de usuarios.");

			final var usersList = (List<User>) userRepo.findAll();

			return usersList;

		} catch (ServiceException e) {
			LOGGER.error("Service Error : {} {} ", e.getCode(), e.getLocalizedMessage());
			throw e;
		} catch (Exception e) {
			LOGGER.info("Error : {}", e.getLocalizedMessage());
			throw new ServiceException(String.valueOf(HttpStatus.BAD_REQUEST.value()), e.getMessage());
		}
	}// Method Closure

	@Override
	public User findUser(final String token, final String id) {
		try {
			LOGGER.info("Inicio de busqueda de usuario de id {}.", id);
			final var user = utils.findUser(id);

			return user;
		} catch (ServiceException e) {
			LOGGER.error("Service Error : {} {} ", e.getCode(), e.getLocalizedMessage());
		}
		return null;

	}// Method Closure

	@Override
	public UserUpdateResponse updateUser(final String token, final UpdateUserRequest request) {
		try {
			LOGGER.info("Inicio de servicio modificacion de usuarios.");
			final var user = userRepo.findById(request.getId()).orElse(null);

			// Se valida estructura de email y password ingresados.
			utils.emailValidator(request.getEmail());
			utils.passwordValidator(request.getPassword());
			LOGGER.info("Formatos de email y password validos.");

			Date date = new Date();
			LOGGER.info("Date: {}", date);

			// Se arma Entity para actualizar.
			final var userUpdate = new User();
			userUpdate.setId(user.getId());
			userUpdate.setName(request.getName());
			userUpdate.setEmail(request.getEmail());
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
		} catch (ServiceException e) {
			LOGGER.error("Service Error : {} {} ", e.getCode(), e.getLocalizedMessage());
			throw e;
		} catch (Exception e) {
			LOGGER.info("Error : {}", e.getLocalizedMessage());
			throw new ServiceException(String.valueOf(HttpStatus.BAD_REQUEST.value()), e.getMessage());
		}
	}// Method Closure

	@Override
	public User findByEmail(final String token, String email) {
		// TODO Auto-generated method stub
		return userRepo.findByEmail(email);
	}

}// Class Closure