package com.example.demo.response;

import java.util.Date;
import java.util.List;

import com.example.demo.models.entity.Phone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateResponse {

    private String message;
    private Long id;
	private Date created;
	private Date modified;
	private Date lastLogin;
	private String token;
	private Boolean isactive;
	private List<Phone> phones;

}// Class Closure