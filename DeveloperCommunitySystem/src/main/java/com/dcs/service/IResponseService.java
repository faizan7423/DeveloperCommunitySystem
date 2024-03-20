package com.dcs.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dcs.dto.ResponseDTO;
import com.dcs.entity.Developer;
import com.dcs.entity.Post;
import com.dcs.entity.Response;
import com.dcs.exception.DeveloperCommunitySystemException;

import jakarta.validation.Valid;
@Service
@Qualifier("responseServiceImpl")
public interface IResponseService {
	ResponseDTO  addResponse(ResponseDTO  response);
	ResponseDTO  removeResponse(Integer respId);
	Page<ResponseDTO> getAllResponses(int page, int size);
	ResponseDTO updateResponse(Integer responseId, ResponseDTO response) throws DeveloperCommunitySystemException;
	Page<ResponseDTO> getResponseByDeveloper(Integer userId, Pageable pageable);
	Page<ResponseDTO> getResponseByPost(@Valid Integer postId, Pageable pageable);
	
}