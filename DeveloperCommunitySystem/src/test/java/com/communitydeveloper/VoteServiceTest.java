package com.communitydeveloper;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
import java.util.Collections;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import org.modelmapper.ModelMapper;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageImpl;

import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;

import com.dcs.dao.VoteDao;

import com.dcs.dto.VoteDTO;

import com.dcs.entity.Vote;

import com.dcs.service.VoteServiceImpl;

public class VoteServiceTest {

	@Mock

	private VoteDao voteDao;

	@Mock

	private ModelMapper modelMapper;

	@InjectMocks

	private VoteServiceImpl voteService;

	@BeforeEach

	public void setUp() {

		MockitoAnnotations.openMocks(this);

	}

	@Test

	public void testGetAllVotes() {

		// Arrange

		int page = 0;

		int pageSize = 10;

		List<Vote> testVotes = Collections.singletonList(new Vote(/* initialize with test data */));

		Page<Vote> testVotePage = new PageImpl<>(testVotes);

		when(voteDao.findAll(any(Pageable.class))).thenReturn(testVotePage);

		when(modelMapper.map(any(Vote.class), eq(VoteDTO.class)))
				.thenReturn(new VoteDTO(/* initialize with test data */));

		// Act
		Pageable pageable = PageRequest.of(page, pageSize);
		Page<VoteDTO> resultVotePage = voteService.getAllVotes(pageable);

		// Assert

		assertNotNull(resultVotePage);

		assertEquals(1, resultVotePage.getTotalElements());

		// Add more assertions based on your requirements

	}

	// Add more tests for other methods...

}
