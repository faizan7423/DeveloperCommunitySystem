package com.dcs.service;

import java.util.List;

import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageImpl;

import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import com.dcs.dao.VoteDao;

import com.dcs.dto.VoteDTO;

import com.dcs.entity.Vote;
import com.dcs.util.VoteType;

import jakarta.validation.Valid;

@Service

public class VoteServiceImpl implements IVoteService {

	@Autowired
	VoteDao voteDao;

	@Autowired
	private ModelMapper modelMapper;
	/**
     * Adds a new vote to the system.
     *
     * @param vote The VoteDTO object containing vote details.
     * @return The added VoteDTO.
     */

	@Override
	public VoteDTO addVote(VoteDTO vote) {

		Vote entity1 = modelMapper.map(vote, Vote.class);

		entity1 = voteDao.save(entity1);

		return modelMapper.map(entity1, VoteDTO.class);

	}
	/**
     * Retrieves all votes with pagination.
     *
     * @param pageable The pagination information.
     * @return A Page of VoteDTOs containing the list of votes.
     */
	
	@Override
	public Page<VoteDTO> getAllVotes(Pageable pageable) {
		Page<Vote> votePage = voteDao.findAll(pageable);

		List<VoteDTO> voteDTOs = votePage.getContent().stream().map(entity -> modelMapper.map(entity, VoteDTO.class))
				.collect(Collectors.toList());

		return new PageImpl<>(voteDTOs, votePage.getPageable(), votePage.getTotalElements());
	}
	@Override
    public Integer getNoOfVotesOnResponseByVoteType(VoteType voteType, Integer responseId) {
        List<Vote> votes = voteDao.findByVoteTypeAndResponse_ResponseId(voteType, responseId);
        return votes.size(); // Count the number of votes based on your logic
    }
}