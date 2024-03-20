package com.dcs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dcs.dao.PostDao;
import com.dcs.dto.DeveloperDTO;
import com.dcs.dto.PostDTO;
import com.dcs.entity.Developer;
import com.dcs.entity.Post;
import com.dcs.exception.DeveloperCommunitySystemException;

@Service
public class PostServiceImpl implements IPostService {
	@Autowired
	PostDao postDao;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDTO addPost(PostDTO post) {
		Post entity1 = modelMapper.map(post, Post.class);
		entity1 = postDao.save(entity1);
		PostDTO entity = modelMapper.map(entity1, PostDTO.class);
		return entity;
	}

	@Override
	public PostDTO updatePost(Integer postId, PostDTO post) throws DeveloperCommunitySystemException {
		Optional<Post> dev = postDao.findById(postId);
		try {
			if(!(postDao.existsById(postId))||dev == null)
			{
				throw new DeveloperCommunitySystemException("Please Enter Correct PostId",HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (DeveloperCommunitySystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (dev.isPresent()) {
			Post po = dev.get();
			po.setQuery(post.getQuery());
			po.setTopic(post.getTopic());
			po.setPostDateTime(post.getPostDateTime());
			postDao.save(po);
		}

		return post;
	}

	@Override
	public PostDTO getPostById(Integer postId)  {
		Post entity = postDao.findById(postId).orElse(null);
		
	    if (entity != null) {
	        
	        entity.setNoOfViews(entity.getNoOfViews() + 1);

	        
	        entity = postDao.save(entity);
	    }

		PostDTO entity1 = modelMapper.map(entity, PostDTO.class);

		return entity1;
	

	}

	@Override
	public PostDTO removePost(Integer postId) throws DeveloperCommunitySystemException {
		Post post = postDao.findById(postId).orElseThrow(()-> new DeveloperCommunitySystemException("Post Id Not FOund"));
		if (post != null) {
			postDao.delete(post);
			PostDTO entity1 = modelMapper.map(post, PostDTO.class);
			return entity1;
		}
		return modelMapper.map(post, PostDTO.class);
	}

	@Override
	public List<PostDTO> findByTopic(String topic) {
		// Pageable pageable = PageRequest.of(page, pageSize);
		List<Post> post = postDao.findByTopic(topic);

		return post.stream().map(post1 -> modelMapper.map(post1, PostDTO.class)).collect(Collectors.toList());
	}

	@Override
	public Page<PostDTO> viewPost(Pageable pageable) {
	    Page<Post> postPage = postDao.findAll(pageable);
	    return postPage.map(post1 -> {
	        PostDTO postDTO = new PostDTO();
	        postDTO.setNoOfViews(post1.getNoOfViews());
	        postDTO.setPostDateTime(post1.getPostDateTime());
	        postDTO.setDeveloper(modelMapper.map(post1.getDeveloper(), DeveloperDTO.class));
	        postDTO.setPostId(post1.getPostId());
	        postDTO.setQuery(post1.getQuery());
	        postDTO.setTopic(post1.getTopic());
	        return postDTO;
	    });
	}
}
