package com.communitydeveloper;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import org.modelmapper.ModelMapper;

import com.dcs.dao.PostDao;

import com.dcs.dao.VoteDao;

import com.dcs.dto.PostDTO;

import com.dcs.entity.Post;
import com.dcs.exception.DeveloperCommunitySystemException;
import com.dcs.service.PostServiceImpl;

@ExtendWith(MockitoExtension.class)

class PostServiceTest {

	@Mock
	private PostDao postDao;
	@Mock
	private VoteDao voteDao;
	@Mock
	private ModelMapper modelMapper;
	@InjectMocks
	private PostServiceImpl postService;
	@Test
	void testAddPost() {
		// Arrange
		PostDTO postDTO = new PostDTO();
		Post postEntity = new Post();
		when(modelMapper.map(postDTO, Post.class)).thenReturn(postEntity);
		when(postDao.save(postEntity)).thenReturn(postEntity);
		when(modelMapper.map(postEntity, PostDTO.class)).thenReturn(postDTO);
		// Act
		PostDTO result = postService.addPost(postDTO);
		// Assert
		assertNotNull(result);
		assertEquals(postDTO, result);
		// Verify
		verify(modelMapper).map(postDTO, Post.class);
		verify(postDao).save(postEntity);
		verify(modelMapper).map(postEntity, PostDTO.class);
	}

	@Test

	void testUpdatePost() throws DeveloperCommunitySystemException {

		// Arrange

		PostDTO postDTO = new PostDTO();

		Post postEntity = new Post();

		when(modelMapper.map(postDTO, Post.class)).thenReturn(postEntity);

		when(postDao.save(postEntity)).thenReturn(postEntity);

		when(modelMapper.map(postEntity, PostDTO.class)).thenReturn(postDTO);

		// Act

		PostDTO result = postService.updatePost(null, postDTO);

		// Assert

		assertNotNull(result);

		assertEquals(postDTO, result);

		// Verify

		verify(modelMapper).map(postDTO, Post.class);

		verify(postDao).save(postEntity);

		verify(modelMapper).map(postEntity, PostDTO.class);

	}

	@Test

	void testGetPostById() throws DeveloperCommunitySystemException {

		// Arrange

		int postId = 1;

		Post postEntity = new Post();

		PostDTO postDTO = new PostDTO();

		when(postDao.findById(postId)).thenReturn(Optional.of(postEntity));

		when(modelMapper.map(postEntity, PostDTO.class)).thenReturn(postDTO);

		// Act

		PostDTO result = postService.getPostById(postId);

		// Assert

		assertNotNull(result);

		assertEquals(postDTO, result);

		// Verify

		verify(postDao).findById(postId);

		verify(modelMapper).map(postEntity, PostDTO.class);

	}

	@Test

	void testRemovePost() throws DeveloperCommunitySystemException {

		// Arrange

		int postId = 1;

		Post postEntity = new Post();

		PostDTO postDTO = new PostDTO();

		when(postDao.findById(postId)).thenReturn(Optional.of(postEntity));

		when(modelMapper.map(postEntity, PostDTO.class)).thenReturn(postDTO);

		// Act

		PostDTO result = postService.removePost(postId);

		// Assert

		assertNotNull(result);

		assertEquals(postDTO, result);

		// Verify

		verify(postDao).findById(postId);

		verify(postDao).delete(postEntity);

		verify(modelMapper).map(postEntity, PostDTO.class);

	}

	@Test

	void testFindByTopic() {

		// Arrange

		String topic = "Test Topic";

		List<Post> posts = new ArrayList<>();

		posts.add(new Post());

		posts.add(new Post());

		when(postDao.findByTopic(topic)).thenReturn(posts);

		when(modelMapper.map(any(), eq(PostDTO.class))).thenReturn(new PostDTO());

		// Act

		List<PostDTO> result = postService.findByTopic(topic);

		// Assert

		assertNotNull(result);

		assertEquals(posts.size(), result.size());

		// Verify

		verify(postDao).findByTopic(topic);

		verify(modelMapper, times(posts.size())).map(any(), eq(PostDTO.class));

	}

	// Add more tests for other methods as needed...

}
