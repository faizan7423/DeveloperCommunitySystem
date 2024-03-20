package com.communitydeveloper;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import org.modelmapper.ModelMapper;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageImpl;

import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;

import com.dcs.dao.ResponseDao;

import com.dcs.dto.ResponseDTO;

import com.dcs.entity.Response;
import com.dcs.exception.DeveloperCommunitySystemException;
import com.dcs.service.ResponseServiceImpl;

@ExtendWith(MockitoExtension.class)

class ResponseServiceImplTest {

	@Mock

	private ResponseDao responseDao;

	@Mock

	private ModelMapper modelMapper;

	@InjectMocks

	private ResponseServiceImpl responseService;

	@Test

	void testAddResponse() {

		// Arrange

		ResponseDTO responseDTO = new ResponseDTO();

		Response responseEntity = new Response();

		when(modelMapper.map(responseDTO, Response.class)).thenReturn(responseEntity);

		when(responseDao.save(responseEntity)).thenReturn(responseEntity);

		when(modelMapper.map(responseEntity, ResponseDTO.class)).thenReturn(responseDTO);

		// Act

		ResponseDTO result = responseService.addResponse(responseDTO);

		// Assert

		assertNotNull(result);

		assertEquals(responseDTO, result);

		// Verify

		verify(modelMapper).map(responseDTO, Response.class);

		verify(responseDao).save(responseEntity);

		verify(modelMapper).map(responseEntity, ResponseDTO.class);

	}

	@Test

	void testUpdateResponse() throws DeveloperCommunitySystemException {

		// Arrange

		ResponseDTO responseDTO = new ResponseDTO();

		Response responseEntity = new Response();

		when(modelMapper.map(responseDTO, Response.class)).thenReturn(responseEntity);

		when(responseDao.save(responseEntity)).thenReturn(responseEntity);

		when(modelMapper.map(responseEntity, ResponseDTO.class)).thenReturn(responseDTO);

		// Act

		ResponseDTO result = responseService.updateResponse(null, responseDTO);

		// Assert

		assertNotNull(result);

		assertEquals(responseDTO, result);

		// Verify

		verify(modelMapper).map(responseDTO, Response.class);

		verify(responseDao).save(responseEntity);

		verify(modelMapper).map(responseEntity, ResponseDTO.class);

	}

	@Test

	void testRemoveResponse() {

		// Arrange

		int respId = 1;

		ResponseDTO responseDTO = new ResponseDTO();

		Response responseEntity = new Response();

		when(responseDao.findById(respId)).thenReturn(Optional.of(responseEntity));

		when(modelMapper.map(responseEntity, ResponseDTO.class)).thenReturn(responseDTO);

		// Act

		ResponseDTO result = responseService.removeResponse(respId);

		// Assert

		assertNotNull(result);

		assertEquals(responseDTO, result);

		// Verify

		verify(responseDao).findById(respId);

		verify(responseDao).deleteById(respId);

		verify(modelMapper).map(responseEntity, ResponseDTO.class);

	}

	@Test

	public void testGetAllResponses() {

		Page<ResponseDTO> expectedPage = new PageImpl<>(new ArrayList<>());

		when(responseDao.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(new ArrayList<>()));

		Page<ResponseDTO> result = responseService.getAllResponses(0, 10);

		assertEquals(expectedPage, result);

		verify(responseDao, times(1)).findAll(any(Pageable.class));

	}

	// Add more tests for other methods as needed...

}
