package com.communitydeveloper;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import org.modelmapper.ModelMapper;

import com.dcs.dao.UserDao;

import com.dcs.dto.UserDTO;

import com.dcs.entity.User;
import com.dcs.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)

class UserServiceTest {

	@Mock
	private UserDao userDao;

	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	private UserServiceImpl userService;

	@Test

	void testRegisterUser() {

		// Arrange

		UserDTO userDTO = new UserDTO(null, null, null, null);

		User userEntity = new User();

		when(modelMapper.map(userDTO, User.class)).thenReturn(userEntity);

		when(userDao.save(userEntity)).thenReturn(userEntity);

		when(modelMapper.map(userEntity, UserDTO.class)).thenReturn(userDTO);

		// Act

		UserDTO result = userService.registerUser(userDTO);

		// Assert

		assertNotNull(result);

		assertEquals(userDTO, result);

		// Verify

		verify(modelMapper).map(userDTO, User.class);

		verify(userDao).save(userEntity);

		verify(modelMapper).map(userEntity, UserDTO.class);

	}

	@Test

	public void testSignOut() {

		String result = userService.signOut();

		assertEquals("User signed out", result);

	}

	

}
