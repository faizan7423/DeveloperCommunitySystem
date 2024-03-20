package com.dcs.service;


import org.springframework.stereotype.Service;

import com.dcs.dto.UserDTO;
import com.dcs.exception.DeveloperCommunitySystemException;
@Service
public interface IUserService {
	UserDTO registerUser(UserDTO user);
	UserDTO signIn(String userName, String password) throws DeveloperCommunitySystemException;
	public String signOut();
}