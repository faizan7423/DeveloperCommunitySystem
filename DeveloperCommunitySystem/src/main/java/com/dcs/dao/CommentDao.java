package com.dcs.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dcs.entity.Comment;
import com.dcs.entity.Developer;
@Repository
public interface CommentDao extends JpaRepository<Comment, Integer> {
	//Retrieves a Page of comments associated with a specific post.
	Page<Comment> findByPostId(Integer postId, Pageable pageable);
	//Retrieves a Page of comments associated with a specific response.
	@Query("SELECT comment FROM Comment comment WHERE comment.response.responseId = :respId")
	Page<Comment> getCommentsByResponseId(@Param("respId") Integer respId, Pageable pageable);
	//Retrieves a Page of comments associated with a specific post.
	@Query("SELECT c FROM Comment c WHERE c.postId = :postId")
	Page<Comment> getCommentsByPostId(@Param("postId") Integer postId, Pageable pageable);
}