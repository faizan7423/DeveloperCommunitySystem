package com.dcs.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dcs.dao.ResponseDao;
import com.dcs.dto.CommentDTO;
import com.dcs.dto.ResponseDTO;
import com.dcs.entity.Comment;
import com.dcs.entity.Developer;
import com.dcs.entity.Post;
import com.dcs.entity.Response;
import com.dcs.exception.DeveloperCommunitySystemException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class ResponseServiceImpl implements IResponseService {

	@Autowired
	private ResponseDao responseDao;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseDTO addResponse(ResponseDTO response) {
		Response entity1 = modelMapper.map(response, Response.class);
		entity1 = responseDao.save(entity1);
		return modelMapper.map(entity1, ResponseDTO.class);

	}

	//@Override

//	public ResponseDTO updateResponse(ResponseDTO response) {
//		Response entity1 = modelMapper.map(response, Response.class);
//		entity1 = responseDao.save(entity1);
//		return modelMapper.map(entity1, ResponseDTO.class);
//
//	}
	
	@Override
	public ResponseDTO updateResponse(Integer responseId,ResponseDTO response) throws DeveloperCommunitySystemException {
		Optional<Response> ro = responseDao.findById(responseId);
		if(ro.isPresent())
		{
			Response res = ro.get();
			res.setDeveloper(response.getDeveloper());
			res.setAnswer(response.getAnswer());
			responseDao.save(res);
		}
		if(!(responseDao.existsById(responseId))||ro == null)
		{
			throw new DeveloperCommunitySystemException("CommentId Not Found",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
		
	}
//	@Override
//	public CommentDTO updateComment(Integer commentId, CommentDTO comment) throws Exception {
//		Optional<Comment> co = commentDao.findById(commentId);
//		if(co.isPresent())
//		{
//			Comment com = co.get();
//			com.setText(comment.getText());
//			commentDao.save(com);
//			}
//		if(co == null)
//		{
//			throw new Exception();
//		}
//		return comment;
//	}

	@Override

	public ResponseDTO removeResponse(Integer respId) {

		Response existingResponseEntity = responseDao.findById(respId)
				.orElseThrow(() -> new EntityNotFoundException("Response not found"));

		responseDao.deleteById(respId);

		return modelMapper.map(existingResponseEntity, ResponseDTO.class);
	}

	@Override
	public Page<ResponseDTO> getAllResponses(int page, int pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize);
		Page<Response> responsesPage = responseDao.findAll(pageable);

		Page<ResponseDTO> responseDto = responsesPage.map(re -> modelMapper.map(re, ResponseDTO.class));
		return responseDto;
	}
	private ResponseDTO convertToDTO(Response response) {
        return modelMapper.map(response, ResponseDTO.class);
        
    }
	@Override
	public Page<ResponseDTO> getResponseByPost(Integer postId, Pageable pageable) {
		Page<Response> responsePage = responseDao.getResponseByPostId(postId, pageable);
		return responsePage.map(this::convertToDTO);
	}
	@Override
	public Page<ResponseDTO> getResponseByDeveloper(Integer userId, Pageable pageable) {
		Page<Response> responsePage = responseDao.getResponseByUserId(userId, pageable);
		return responsePage.map(this::convertToDTO);
	}

	

	
}
