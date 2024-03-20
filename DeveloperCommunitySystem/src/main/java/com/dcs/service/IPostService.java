package com.dcs.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dcs.dto.PostDTO;
import com.dcs.exception.DeveloperCommunitySystemException;

@Service
public interface IPostService {

	PostDTO addPost(PostDTO post);
	PostDTO getPostById(Integer postId) throws DeveloperCommunitySystemException;
	PostDTO removePost(Integer postId) throws DeveloperCommunitySystemException;
	Page<PostDTO> viewPost(Pageable pageable);
	List<PostDTO> findByTopic(String topic);
	PostDTO updatePost(Integer postId, PostDTO post) throws DeveloperCommunitySystemException;
}