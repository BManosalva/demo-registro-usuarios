package com.example.demo.utils;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.example.demo.exception.ServiceException;
import com.example.demo.models.entity.User;
import com.example.demo.models.repository.UserRepository;

@Component
public class Utils {

    @Autowired
	UserRepository userRepo;


    private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);

    public Date generateDateNowDate(){
        Date date = new Date();
        return date;
    }// Method Closure

    /**
     * Método para validar email.
     * 
     * @param email {@link String}
     */
    public static void emailValidator(final String email){
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        Matcher validEmail = pattern.matcher(email);
        if(validEmail.find() == false){
            LOGGER.error("Error email {}", MessageUtils.INVALID_EMAIL);
            throw new ServiceException(String.valueOf(HttpStatus.CONFLICT.value()), MessageUtils.INVALID_EMAIL);
        }
    }// Method Closure

    /**
     * Metodo para validar formato de contraseña.
     * 
     * @param password {@link String}
     */
    public static void passwordValidator(final String password){
        // Al menos un número y una mayuscula
        Pattern pattern = Pattern.compile("^(.*[0-9].*)(.*[A-Z].*)$");
        Matcher validPassword = pattern.matcher(password);
        if(validPassword.find() == false){
            LOGGER.error("Error password {}", MessageUtils.INVALID_PASSWORD);
            throw new ServiceException(String.valueOf(HttpStatus.CONFLICT.value()), MessageUtils.INVALID_PASSWORD);
        }
    }// Method Closure

    public static void userValidator(final User user){
        if(null == user){
            LOGGER.error("Error usuario {}", MessageUtils.USER_NOT_FOUND);
            throw new ServiceException(String.valueOf(HttpStatus.NOT_FOUND.value()), MessageUtils.USER_NOT_FOUND);
        }
    }// Method Closure

    public void existingEmailValidator(final String email){
        if(null != userRepo.findByEmail(email)){
            LOGGER.error("Error ingreso {}", MessageUtils.EXISTING_EMAIL);
            throw new ServiceException(String.valueOf(HttpStatus.CONFLICT.value()), MessageUtils.EXISTING_EMAIL);
        }
    }// Method Closure

    /**
     * Metodo para buscar un usuario.
     * 
     * @param id {@link Long}
     * @return {@link User}
     */
    public User findUser(Long id){
        var user = new User();
        LOGGER.info("User {}", user);
        user = userRepo.findById(id).orElse(null);
        return user;
    }// Method Closure




    //////////////

    /**
     * Metodo para validar si el email ya se encuentra registrado.
     * 
     * @param incomingEmail
     * @param dbEmail
     */
    /* 
    public static void existingEmail(final String incomingEmail, final User dbUser){

        
        if(incomingEmail.equals(dbEmail)){
            LOGGER.error("Error email {}", MessageUtils.EXISTING_EMAIL);
            throw new ServiceException(String.valueOf(HttpStatus.CONFLICT.value()), MessageUtils.EXISTING_EMAIL);
        }
    */

}// Class Closure