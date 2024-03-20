package com.dcs.controller;
	import java.util.List;
	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.DeleteMapping;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.PutMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dcs.dto.CommentDTO;
import com.dcs.dto.ResponseDTO;
import com.dcs.entity.Developer;
import com.dcs.entity.Post;
import com.dcs.entity.Response;
import com.dcs.exception.DeveloperCommunitySystemException;
import com.dcs.service.IResponseService;

	@RestController
	@RequestMapping("/response")
	public class ResponseController {
	@Autowired
	IResponseService responseService;
	// Method to add a new response
	@PostMapping(path="add",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> saveResponse(@RequestBody ResponseDTO response){
		ResponseDTO newResponse=responseService.addResponse(response);
		return new ResponseEntity<ResponseDTO>(newResponse,HttpStatus.OK);
	}
	// Method to update an existing response
	@PutMapping(path="update/{responseId}",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> editResponse(@PathVariable("responseId")Integer responseId,@RequestBody ResponseDTO response) throws DeveloperCommunitySystemException{
		ResponseDTO updateResponse=responseService.updateResponse(responseId,response);
		return new ResponseEntity<ResponseDTO>(updateResponse,HttpStatus.OK);
	}
	// Method to remove a response
	@DeleteMapping("remove/{respId}")
	public ResponseEntity<String> removeResponse(@PathVariable Integer respId){
		ResponseDTO isDelete=responseService.removeResponse(respId);
		if(isDelete==null) {
			return new ResponseEntity<String>("Response not deleted",HttpStatus.OK);
		}
		return new ResponseEntity<String>("Response deleted",HttpStatus.OK);
	}
	 // Method to get all responses with pagination
	@GetMapping("get/all")
	public ResponseEntity<Page<ResponseDTO>> getAllDevelopers(@RequestParam(name = "page", defaultValue = "0")
	int page,@RequestParam(name = "size", defaultValue = "10") int size) {
	    Page<ResponseDTO> entities = responseService.getAllResponses(page,size);
	    return new ResponseEntity<Page<ResponseDTO>>(entities, HttpStatus.OK);
		}
	// Method to get responses by post ID with pagination
	@GetMapping(path = {"get/responseByPost/{postId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<ResponseDTO>> getResponseByPostId(@PathVariable("postId") Integer postId, Pageable pageable){
		Page<ResponseDTO> response = responseService.getResponseByPost(postId, pageable);
		return new ResponseEntity<Page<ResponseDTO>>(response, HttpStatus.OK);
	}
	 // Method to get responses by developer ID with pagination
	@GetMapping(path = {"get/responseByDeveloper/{userId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<ResponseDTO>> getResponseByUserId(@PathVariable("userId") Integer userId, Pageable pageable){
		Page<ResponseDTO> response = responseService.getResponseByPost(userId, pageable);
		return new ResponseEntity<Page<ResponseDTO>>(response, HttpStatus.OK);
	}
	
	

}