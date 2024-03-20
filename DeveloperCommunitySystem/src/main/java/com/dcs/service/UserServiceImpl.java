package com.dcs.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dcs.dao.UserDao;
import com.dcs.dto.UserDTO;
import com.dcs.entity.User;
import com.dcs.exception.DeveloperCommunitySystemException;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDTO registerUser(UserDTO user) {
		User entity1 = modelMapper.map(user, User.class);
		User savedUser = userDao.save(entity1);
		return modelMapper.map(savedUser, UserDTO.class);
	}

	@Override
	public UserDTO signIn(String userName, String password) throws DeveloperCommunitySystemException {
		User userEntity = userDao.findByUserName(userName);
		if (userEntity != null && userEntity.getUserPassword().equals(password)) {
			// Map the userEntity to UserDTO
			return modelMapper.map(userEntity, UserDTO.class);
		} else {
			throw new DeveloperCommunitySystemException("Enter Details Correctly",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public String signOut() {
		return "User signed out";
	}

}
