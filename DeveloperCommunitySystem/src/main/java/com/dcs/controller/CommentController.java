package com.dcs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import com.dcs.exception.DeveloperCommunitySystemException;
import com.dcs.service.CommentServiceImpl;
import com.dcs.service.ICommentService;


@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	ICommentService commentService;
	// Method to add a new comment
	@PostMapping(path = "add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CommentDTO> addComment(@Validated @RequestBody CommentDTO response)throws DeveloperCommunitySystemException {
	
			CommentDTO newResponse = commentService.addComment(response);
			if(newResponse==null) {
				throw new DeveloperCommunitySystemException(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<CommentDTO>(newResponse, HttpStatus.OK);
	}
	
	//Method to update an existing comment
	@PutMapping(path = "update/{ID}", consumes = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<CommentDTO> editResponse(@PathVariable("ID") Integer commentId, @RequestBody CommentDTO comment)throws Exception {
			CommentDTO updateResponse = commentService.updateComment(commentId, comment);
			if(updateResponse==null) {
				throw new DeveloperCommunitySystemException(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<CommentDTO>(updateResponse, HttpStatus.OK);

		} 
	// Method to remove a comment
	@DeleteMapping("remove/{Id}")
	public ResponseEntity<String> removeComment(@PathVariable Integer Id) throws Exception{
		
			CommentDTO isDelete = commentService.removeComment(Id);
			if (isDelete == null) {
				return new ResponseEntity<String>("Response not deleted", HttpStatus.OK);
			}
			return new ResponseEntity<String>("Response deleted", HttpStatus.OK);
	}
	
	// Method to get a comment by its ID
	@GetMapping(path = "get/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CommentDTO> getByCommentId(@PathVariable Integer commentId) throws DeveloperCommunitySystemException{
		if(commentId==null) {
			throw new DeveloperCommunitySystemException("Invalid Comment ID");
		}
		else {
			CommentDTO response = commentService.getByCommentId(commentId);
			return new ResponseEntity<CommentDTO>(response, HttpStatus.OK);
		}
	}
	
	 // Method to get comments by response ID
	@GetMapping(path = "/responseId/{resId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<CommentDTO>> getCommentsByResponseId(@PathVariable Integer resId, Pageable pageable) {
		
		Page<CommentDTO> response = commentService.getCommentsByResponseId(resId,pageable);
		return new ResponseEntity<Page<CommentDTO>>(response, HttpStatus.OK);
	}
	// Method to get comments by post ID
	@GetMapping(path = "/postId/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<CommentDTO>> getCommentsByPostId(@PathVariable Integer postId, Pageable pageable) {
		
		Page<CommentDTO> response = commentService.getCommentsByPostId(postId,pageable);
		return new ResponseEntity<Page<CommentDTO>>(response, HttpStatus.OK);
	}
	
	

	


	
	
	
	
	

	

}