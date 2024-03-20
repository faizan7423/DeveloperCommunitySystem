package com.communitydeveloper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.*;

import java.util.Arrays;

import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import org.modelmapper.ModelMapper;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageImpl;

import org.springframework.data.domain.PageRequest;

import com.dcs.dao.DeveloperDao;

import com.dcs.dto.DeveloperDTO;

import com.dcs.entity.Developer;
import com.dcs.exception.DeveloperCommunitySystemException;
import com.dcs.service.DeveloperServiceImpl;

public class DeveloperServiceTest {

	@Mock

	private DeveloperDao developerDao;

	@Mock

	private ModelMapper modelMapper;

	@InjectMocks

	private DeveloperServiceImpl developerService;

	@BeforeEach

	public void setUp() {

		MockitoAnnotations.openMocks(this);

	}

//	@Test
//
//	public void testGetDeveloperByReputation() throws DeveloperCommunitySystemException {
//		// Arrange
//		int reputation = 100;
//		List<Developer> testDevelopers = Arrays.asList(new Developer(/* initialize with test data */));
//		when(developerDao.findByReputation(reputation)).thenReturn(testDevelopers);
//		// Act
//		List<DeveloperDTO> resultDeveloperDTOs = developerService.getDeveloperByReputation(reputation);
//		// Assert
//		assertNotNull(resultDeveloperDTOs);
//
//	}

	@Test

	public void testDeleteDeveloper() {

		Integer devId = 1;
		Developer developer = new Developer();
		when(developerDao.findById(anyInt())).thenReturn(Optional.of(developer));
		String result = developerService.delete(devId);
		assertEquals("Developer Deleted Successfully", result);
		verify(developerDao, times(1)).deleteById(anyInt());
	}
}
// Add more tests for other methods...
