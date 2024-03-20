package com.dcs.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

import com.dcs.dao.PostDao;
import com.dcs.dto.DeveloperDTO;
import com.dcs.dto.PostDTO;
import com.dcs.entity.Post;
import com.dcs.exception.DeveloperCommunitySystemException;
import com.dcs.service.IPostService;


@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	IPostService postService;
	@Autowired
	PostDao postDao;
	// Method to add a new post
	@PostMapping(path = "add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PostDTO> savePost(@RequestBody PostDTO post) throws DeveloperCommunitySystemException {
		PostDTO post3 = postService.addPost(post);
		if (post3 == null) {
			throw new DeveloperCommunitySystemException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<PostDTO>(post3, HttpStatus.OK);
	}
	// Method to update an existing post
	@PutMapping(path = "update/{postId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PostDTO> editPost(@PathVariable("postId") Integer postId,@RequestBody PostDTO post) throws DeveloperCommunitySystemException {
		PostDTO post1 = postService.updatePost(postId,post);
		if (post1 == null) {
			throw new DeveloperCommunitySystemException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<PostDTO>(post1, HttpStatus.OK);
	}
	 // Method to remove a post
	@DeleteMapping(path = "remove/{postId}")
	public ResponseEntity<String> removePost(@PathVariable("postId") Integer postId) throws DeveloperCommunitySystemException {
		PostDTO isDelete = postService.removePost(postId);
		if (isDelete == null) {
			return new ResponseEntity<String>("Response not deleted", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Response deleted", HttpStatus.OK);
	}
	 // Method to get details of a specific post by its ID
	@GetMapping("get/{postId}")
	public PostDTO getPostById(@PathVariable("postId") Integer postId) throws DeveloperCommunitySystemException {
		if (postId == null) {
			throw new DeveloperCommunitySystemException("Invalid Post Id");
		}
		return postService.getPostById(postId);
	}
	 // Method to search posts by topic
	@GetMapping("search/topic/{topic}")
	public ResponseEntity<List<PostDTO>> getPostsByTopic(@PathVariable("topic") String topic)
			throws Exception {
		if (topic == null) {
			throw new Exception("Topic not Found");
		}
		List<PostDTO> response = postService.findByTopic(topic);
		return new ResponseEntity<List<PostDTO>>(response, HttpStatus.OK);
	}
	// Method to retrieve all posts
	@GetMapping("/allposts")
    public ResponseEntity<Page<PostDTO>> viewPosts(Pageable pageable) {
        Page<PostDTO> postPage = postService.viewPost(pageable);
        return new ResponseEntity<>(postPage, HttpStatus.OK);
    }
}