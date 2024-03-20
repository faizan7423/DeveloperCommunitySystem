package com.dcs.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
import com.dcs.dao.DeveloperDao;
import com.dcs.dao.PostDao;
import com.dcs.dto.CommentDTO;
import com.dcs.dto.DeveloperDTO;
import com.dcs.dto.PostDTO;
import com.dcs.dto.ResponseDTO;
import com.dcs.entity.Developer;
import com.dcs.exception.DeveloperCommunitySystemException;
import com.dcs.service.ICommentService;
import com.dcs.service.IDeveloperService;
import com.dcs.service.IPostService;
import com.dcs.service.IResponseService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("developer")
public class DeveloperController {
	@Autowired
	IDeveloperService developerService;
	@Autowired
	IPostService postService;
	@Autowired
	PostDao postDao;
	@Autowired
	ICommentService commentService;
	@Autowired
	IResponseService responseService;
	
	// Method to add a new developer
	@PostMapping(path = "add")
	public ResponseEntity<DeveloperDTO> saveDeveloper(@Valid @RequestBody DeveloperDTO developer)
			throws DeveloperCommunitySystemException {
		DeveloperDTO newDeveloper = developerService.addDeveloper(developer);
		if (newDeveloper == null) {
			throw new DeveloperCommunitySystemException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<DeveloperDTO>(newDeveloper, HttpStatus.OK);
	}
	// Method to update an existing developer
	@PutMapping(path = "update/{userId}")
	public ResponseEntity<DeveloperDTO> editDeveloper(@RequestBody DeveloperDTO developer, @PathVariable("userId") Integer userId)
			throws Exception {
		DeveloperDTO developer1 = developerService.updateDeveloper(userId, developer);
		if (developer1 == null) {
			throw new DeveloperCommunitySystemException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<DeveloperDTO>(developer1, HttpStatus.OK);
	}
	 // Method to get developers by status
	@GetMapping("/getByStatus/{status}")
	public ResponseEntity<Page<DeveloperDTO>> getDevelopersByStatus(@PathVariable("status") String status, Pageable pageable) {
	    Page<DeveloperDTO> developerPage = developerService.getDevelopersByStatus(status, pageable);
	    return new ResponseEntity<>(developerPage, HttpStatus.OK);
	}
	// Method to get developers by reputation
	@GetMapping("/getByReputation/{reputation}")
	public ResponseEntity<List<DeveloperDTO>> getDeveloperByReputation(@PathVariable("reputation") Integer reputation) throws DeveloperCommunitySystemException{
		List<DeveloperDTO> developer = developerService.getDeveloperByReputation(reputation);
		return new ResponseEntity<List<DeveloperDTO>>(developer, HttpStatus.OK);

	}
	// Method to get all posts by a developer
	@GetMapping("/getPostByUserId/{userId}")
	public ResponseEntity<Page<PostDTO>> getAllPostsByDeveloper(@PathVariable("userId") Integer userId, Pageable pageable) throws DeveloperCommunitySystemException{
		Page<PostDTO> developerPage = developerService.getAllPostsByDeveloper(userId, pageable);
		return new ResponseEntity<>(developerPage, HttpStatus.OK);
		
	}
	 // Method to get developers by the number of posts and topic// Method to get developers by the number of posts and topic
	@GetMapping("/getByNoOfPosts/{noOfPosts}")
	public ResponseEntity<List<DeveloperDTO>> getByNoOfPosts(@PathVariable("noOfPosts") Integer noOfPosts, @RequestParam(value = "topic") String topic){
		List<DeveloperDTO> developer = developerService.getByNoOfPosts(noOfPosts, topic);
		return new ResponseEntity<List<DeveloperDTO>>(developer, HttpStatus.OK);
	}
	
	
	
	@GetMapping("search/topic/{topic}")
	public ResponseEntity<List<PostDTO>> getPostsByTopic(@PathVariable("topic") String topic)
			throws Exception {
		if (topic == null) {
			throw new Exception("Topic not Found");
		}
		List<PostDTO> response = postService.findByTopic(topic);
		return new ResponseEntity<List<PostDTO>>(response, HttpStatus.OK);
	}
	@PostMapping(path = "addPost", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PostDTO> savePost(@RequestBody PostDTO post) throws DeveloperCommunitySystemException {
		PostDTO post3 = postService.addPost(post);
		if (post3 == null) {
			throw new DeveloperCommunitySystemException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<PostDTO>(post3, HttpStatus.OK);
	}

	@PutMapping(path = "updatePost/{postId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PostDTO> editPost(@PathVariable("postId") Integer postId,@RequestBody PostDTO post) throws DeveloperCommunitySystemException {
		PostDTO post1 = postService.updatePost(postId,post);
		if (post1 == null) {
			throw new DeveloperCommunitySystemException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<PostDTO>(post1, HttpStatus.OK);
	}

	@DeleteMapping(path = "removePost/{postId}")
	public ResponseEntity<String> removePost(@PathVariable("postId") Integer postId) throws DeveloperCommunitySystemException {
		PostDTO isDelete = postService.removePost(postId);
		if (isDelete == null) {
			return new ResponseEntity<String>("Response not deleted", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Response deleted", HttpStatus.OK);
	}

	@GetMapping("getPost/{postId}")
	public PostDTO getPostById(@PathVariable("postId") Integer postId) throws DeveloperCommunitySystemException {
		if (postId == null) {
			throw new DeveloperCommunitySystemException("Invalid Post Id");
		}
		return postService.getPostById(postId);
	}
	
	@PostMapping(path = "addComment", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CommentDTO> addComment(@Validated @RequestBody CommentDTO response)throws DeveloperCommunitySystemException {
	
			CommentDTO newResponse = commentService.addComment(response);
			if(newResponse==null) {
				throw new DeveloperCommunitySystemException(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<CommentDTO>(newResponse, HttpStatus.OK);
	}
	@PutMapping(path = "updateComment/{ID}", consumes = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<CommentDTO> editResponse(@PathVariable("ID") Integer commentId, @RequestBody CommentDTO comment)throws Exception {
			CommentDTO updateResponse = commentService.updateComment(commentId, comment);
			if(updateResponse==null) {
				throw new DeveloperCommunitySystemException(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<CommentDTO>(updateResponse, HttpStatus.OK);

		} 
	@PostMapping(path="addResponse",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> saveResponse(@RequestBody ResponseDTO response){
		ResponseDTO newResponse=responseService.addResponse(response);
		return new ResponseEntity<ResponseDTO>(newResponse,HttpStatus.OK);
	}
	 
	@PutMapping(path="updateResponse/{id}",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> editResponse(@PathVariable("responseId")Integer responseId,@RequestBody ResponseDTO response) throws DeveloperCommunitySystemException{
		ResponseDTO updateResponse=responseService.updateResponse(responseId,response);
		return new ResponseEntity<ResponseDTO>(updateResponse,HttpStatus.OK);
	}
	@GetMapping(path = "get/{userId}")
	public ResponseEntity<DeveloperDTO> getDevelopers(@PathVariable("userId") Integer userId) throws DeveloperCommunitySystemException{
		DeveloperDTO developer1 = developerService.getDeveloperById(userId);
		return new ResponseEntity<DeveloperDTO>(developer1, HttpStatus.OK);
	}
	
	
	@DeleteMapping("delete/{userId}")
	public ResponseEntity<DeveloperDTO> delete(@PathVariable("userId") Integer userId)
	{
		return ResponseEntity.ok(developerService.removeDeveloper(userId));
	}
}
