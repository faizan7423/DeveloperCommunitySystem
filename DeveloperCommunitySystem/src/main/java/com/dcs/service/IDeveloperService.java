package com.dcs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dcs.dao.DeveloperDao;
import com.dcs.dto.DeveloperDTO;
import com.dcs.dto.PostDTO;
import com.dcs.exception.DeveloperCommunitySystemException;

import jakarta.validation.Valid;

@Service
public interface IDeveloperService {

	DeveloperDTO addDeveloper(DeveloperDTO developer);

	DeveloperDTO getDeveloperById(Integer devId) throws DeveloperCommunitySystemException;

	List<DeveloperDTO> getDeveloperByReputation(Integer reputation) throws DeveloperCommunitySystemException;

	Page<DeveloperDTO> getDevelopersByStatus(String status, Pageable pageable);

    Page<PostDTO> getAllPostsByDeveloper(Integer userId,Pageable pageable ) throws DeveloperCommunitySystemException;
    
    List<DeveloperDTO> getByNoOfPosts(Integer noOfPosts, String topic);
    
	DeveloperDTO removeDeveloper(Integer devId);
	
	Page<DeveloperDTO> getAllDevelopers(int page, int size);

	List<DeveloperDTO> viewDevelopers();

	String delete(Integer id);

	DeveloperDTO updateDeveloper(Integer userId, DeveloperDTO developer) throws Exception;

}