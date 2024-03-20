package com.dcs.controller;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RestController;
 
import com.dcs.dao.DeveloperDao;
import com.dcs.dao.PostDao;
import com.dcs.dto.CommentDTO;
import com.dcs.dto.DeveloperDTO;
import com.dcs.dto.PostDTO;
import com.dcs.dto.ResponseDTO;
import com.dcs.dto.VoteDTO;
import com.dcs.entity.Developer;
import com.dcs.entity.Post;
import com.dcs.exception.DeveloperCommunitySystemException;
import com.dcs.service.ICommentService;
import com.dcs.service.IDeveloperService;
import com.dcs.service.IPostService;
import com.dcs.service.IResponseService;
import com.dcs.service.IVoteService;
import com.dcs.util.VoteType;

import jakarta.validation.Valid;
 
@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	IDeveloperService developerService;
	@Autowired
	DeveloperDao developerDao;
	@Autowired
	IPostService postService;
	@Autowired
	PostDao postDao;
	@Autowired
	ICommentService commentService;
	@Autowired
	IResponseService responseService;
	
    // Method to add a new developer
	@PostMapping(path = "addDeveloper")
	public ResponseEntity<DeveloperDTO> saveDeveloper(@Valid @RequestBody DeveloperDTO developer)
			throws DeveloperCommunitySystemException {
		DeveloperDTO newDeveloper = developerService.addDeveloper(developer);
		if (newDeveloper == null) {
			throw new DeveloperCommunitySystemException("Developer Should not be null");
		}
		return new ResponseEntity<DeveloperDTO>(newDeveloper, HttpStatus.OK);
	}
	 // Method to update an existing developer
	@PutMapping(path = "updateDeveloper/{userId}")
	public ResponseEntity<DeveloperDTO> editDeveloper(@Valid @RequestBody DeveloperDTO developer,
			@PathVariable("userId") Integer userId) throws Exception {
		DeveloperDTO developer1 = developerService.updateDeveloper(userId, developer);
 
		return new ResponseEntity<DeveloperDTO>(developer1, HttpStatus.OK);
	}
	 // Method to delete a developer
	@DeleteMapping("deleteDeveloper/{userId}")
	public ResponseEntity<DeveloperDTO> delete(@Valid @PathVariable("userId") Integer userId) {
		return ResponseEntity.ok(developerService.removeDeveloper(userId));
	}
	// Method to get details of a specific developer
	@GetMapping(path = "getDeveloper/{userId}")
	public ResponseEntity<DeveloperDTO> getDevelopers(@Valid @PathVariable("userId") Integer userId)
			throws DeveloperCommunitySystemException {
		DeveloperDTO developer1 = developerService.getDeveloperById(userId);
		return new ResponseEntity<DeveloperDTO>(developer1, HttpStatus.OK);
	}
	// Method to get developers by status
	@GetMapping("/DevelopergetByStatus/{status}")
	public ResponseEntity<Page<DeveloperDTO>> getDevelopersByStatus(@Valid @PathVariable("status") String status,
			Pageable pageable) throws DeveloperCommunitySystemException {
		Page<DeveloperDTO> developerPage = developerService.getDevelopersByStatus(status, pageable);
		return new ResponseEntity<>(developerPage, HttpStatus.OK);
	}
	// Method to get developers by reputation
	@GetMapping("/DevelopergetByReputation/{reputation}")
	public ResponseEntity<List<DeveloperDTO>> getDeveloperByReputation(@Valid @PathVariable("reputation") Integer reputation)
			throws DeveloperCommunitySystemException {
		List<DeveloperDTO> developer = developerService.getDeveloperByReputation(reputation);
		return new ResponseEntity<List<DeveloperDTO>>(developer, HttpStatus.OK);
	}
    //Method to get developer post by user id
	@GetMapping("/DevelopergetPostByUserId/{userId}")
	public ResponseEntity<Page<PostDTO>> getAllPostsByDeveloper(@Valid @PathVariable("userId") Integer userId,
			Pageable pageable) throws DeveloperCommunitySystemException {
		Page<PostDTO> developerPage = developerService.getAllPostsByDeveloper(userId, pageable);
		return new ResponseEntity<>(developerPage, HttpStatus.OK);
	}
 //method to get posts by topic
	@GetMapping("Developer/search/topic/{topic}")
	public ResponseEntity<List<PostDTO>> getPostsByTopic(@Valid @PathVariable("topic") String topic) throws Exception {
		List<PostDTO> response = postService.findByTopic(topic);
		return new ResponseEntity<List<PostDTO>>(response, HttpStatus.OK);
	}
	// Retrieves and returns a list of all developers
	@GetMapping("/allDevelopers")
	public List<DeveloperDTO> readAllDevelopers() throws DeveloperCommunitySystemException {
		return developerService.viewDevelopers();
	}
	// Calls postService to add the post
	@PostMapping(path = "addPost", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PostDTO> savePost(@Valid @RequestBody PostDTO post) throws DeveloperCommunitySystemException {
		PostDTO post3 = postService.addPost(post);
		return new ResponseEntity<PostDTO>(post3, HttpStatus.OK);
	}
	
	@PutMapping(path = "updatePost/{postId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PostDTO> editPost(@Valid @PathVariable("postId") Integer postId, @RequestBody PostDTO post)
			throws DeveloperCommunitySystemException {
		PostDTO post1 = postService.updatePost(postId, post);
		return new ResponseEntity<PostDTO>(post1, HttpStatus.OK);
	}
 
	@DeleteMapping(path = "removePost/{postId}")
	public ResponseEntity<String> removePost(@Valid @PathVariable("postId") Integer postId) throws DeveloperCommunitySystemException {
		PostDTO isDelete = postService.removePost(postId);
		return new ResponseEntity<String>("Response deleted", HttpStatus.OK);
	}
 
	@GetMapping("getPost/{postId}")
	public PostDTO getPostById(@Valid @PathVariable("postId") Integer postId) throws DeveloperCommunitySystemException {
		return postService.getPostById(postId);
	}
 
	@GetMapping("post/search/topic/{topic}")
	public ResponseEntity<List<PostDTO>> getPostsByTopic1(@Valid @PathVariable String topic)
			throws DeveloperCommunitySystemException {
		List<PostDTO> response = postService.findByTopic(topic);
		return new ResponseEntity<List<PostDTO>>(response, HttpStatus.OK);
	}
 
	@GetMapping("/allposts")
    public ResponseEntity<Page<PostDTO>> viewPosts(Pageable pageable) {
        Page<PostDTO> postPage = postService.viewPost(pageable);
        return new ResponseEntity<>(postPage, HttpStatus.OK);
    }
 
	@PostMapping(path = "addComment", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CommentDTO> addComment(@Valid @RequestBody CommentDTO response)
			throws DeveloperCommunitySystemException {
		CommentDTO newResponse = commentService.addComment(response);
		return new ResponseEntity<CommentDTO>(newResponse, HttpStatus.OK);
	}
 
	@PutMapping(path = "updateComment/{ID}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CommentDTO> editResponse(@Valid @PathVariable("ID") Integer commentId,
			@RequestBody CommentDTO comment) throws Exception {
		CommentDTO updateResponse = commentService.updateComment(commentId, comment);
		return new ResponseEntity<CommentDTO>(updateResponse, HttpStatus.OK);
	}
	@DeleteMapping("/remove/{Id}")
	public ResponseEntity<String> removeComment(@Valid @PathVariable Integer Id) throws Exception {
			CommentDTO isDelete = commentService.removeComment(Id);
			return new ResponseEntity<String>("Response deleted", HttpStatus.OK);
	}
	@GetMapping(path = "get/comment/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CommentDTO> getByCommentId(@Valid @PathVariable Integer commentId) throws DeveloperCommunitySystemException{
			CommentDTO response = commentService.getByCommentId(commentId);
			return new ResponseEntity<CommentDTO>(response, HttpStatus.OK);
	}
 
	@PostMapping(path = "addResponse", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> saveResponse(@Valid @RequestBody ResponseDTO response) {
		ResponseDTO newResponse = responseService.addResponse(response);
		return new ResponseEntity<ResponseDTO>(newResponse, HttpStatus.OK);
	}
 
	@PutMapping(path = "updateResponse/{responseId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> editResponse(@Valid @PathVariable("responseId") Integer responseId,
			@RequestBody ResponseDTO response) throws DeveloperCommunitySystemException {
		ResponseDTO updateResponse = responseService.updateResponse(responseId, response);
		return new ResponseEntity<ResponseDTO>(updateResponse, HttpStatus.OK);
	}
	@GetMapping("response/{postId}")
	public ResponseEntity<Page<ResponseDTO>> getResponsesByPost(@Valid @PathVariable(value = "postId") 
	Integer postId, Pageable pageable)throws DeveloperCommunitySystemException {
		Post post = new Post();
		post.setPostId(postId);
		Page<ResponseDTO> responsePage = responseService.getResponseByPost(postId, pageable);
		return new ResponseEntity<>(responsePage, HttpStatus.OK);
	}
	@GetMapping(path = {"get/responseByPost/{postId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<ResponseDTO>> getResponseByPostId(@PathVariable("postId") Integer postId, Pageable pageable){
		Page<ResponseDTO> response = responseService.getResponseByPost(postId, pageable);
		return new ResponseEntity<Page<ResponseDTO>>(response, HttpStatus.OK);
	}
	@GetMapping(path = {"get/responseByDeveloper/{userId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<ResponseDTO>> getResponseByUserId(@PathVariable("userId") Integer userId, Pageable pageable){
		Page<ResponseDTO> response = responseService.getResponseByPost(userId, pageable);
		return new ResponseEntity<Page<ResponseDTO>>(response, HttpStatus.OK);
	}
	@GetMapping(path = "/developer/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<ResponseDTO>> getResponseByDeveloper(
				Pageable pageable,
		    @PathVariable("userId") Integer userId
		) throws DeveloperCommunitySystemException {
	Developer developer=new Developer();
	developer.setUserId(userId);
		    Page<ResponseDTO> responsePage = responseService.getResponseByDeveloper(userId, pageable);
		    return new ResponseEntity<>(responsePage.getContent(), HttpStatus.OK);
		}
	@Autowired
	IVoteService voteService;
	@PostMapping(path="add",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VoteDTO> saveVote(@Valid @RequestBody VoteDTO vote) throws DeveloperCommunitySystemException{
		VoteDTO newVote=voteService.addVote(vote);
		return new ResponseEntity<VoteDTO>(newVote,HttpStatus.OK);
	}
	@GetMapping("all")
	public ResponseEntity<Page<VoteDTO>> getAllVotes(@Valid Pageable pageable) {
	    Page<VoteDTO> entities = voteService.getAllVotes(pageable);
	    return new ResponseEntity<Page<VoteDTO>>(entities, HttpStatus.OK);
		}
	//Retrieves the count of votes for a specific response based on the vote type.
	@GetMapping("/count/{voteType}/{responseId}")
    public ResponseEntity<Integer> getVoteCountByTypeAndCommentId( @PathVariable VoteType voteType,@PathVariable Integer responseId ) {
        Integer count = voteService.getNoOfVotesOnResponseByVoteType(voteType, responseId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
	
 
}