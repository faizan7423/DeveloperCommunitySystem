package com.dcs.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dcs.entity.Comment;
import com.dcs.entity.Developer;

import com.dcs.entity.Post;

import com.dcs.entity.Response;
import com.dcs.entity.Vote;
import com.dcs.util.VoteType;

@Repository

public interface ResponseDao extends JpaRepository<Response, Integer> {
	//Retrieves a Page of responses based on the specified post ID.
	@Query("SELECT re FROM Response re WHERE re.post.postId = :postId")
	Page<Response> getResponseByPostId(@Param("postId") Integer postId,Pageable pageable);
	// Retrieves a Page of responses based on the developer's user ID.
	@Query("SELECT u FROM Response u WHERE u.developer.userId = :userId")
	Page<Response> getResponseByUserId(@Param("userId")Integer userId, Pageable pageable);

//	@Query("SELECT comment FROM Comment comment WHERE comment.response.responseId = :respId")
//	Page<Comment> getCommentsByResponseId(@Param("respId") Integer respId, Pageable pageable);
	
	

}