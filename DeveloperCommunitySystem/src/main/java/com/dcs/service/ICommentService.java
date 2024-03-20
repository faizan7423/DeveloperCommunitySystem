package com.dcs.service;


import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.dcs.dto.CommentDTO;
import com.dcs.entity.Comment;
import com.dcs.exception.DeveloperCommunitySystemException;
@Service
public interface ICommentService {

	CommentDTO addComment(CommentDTO  comment);
	
	CommentDTO  removeComment(Integer respId) throws Exception;
	
	Integer getNoOfVotesOnCommentByVoteType(String  voteType, Integer commentId);
	
	CommentDTO  getByCommentId(Integer commentId) throws DeveloperCommunitySystemException;
		
		
	CommentDTO updateComment(Integer commentId, CommentDTO comment) throws Exception;
    
	Page<CommentDTO> getCommentsByPostId(Integer postId, org.springframework.data.domain.Pageable pageable);
	
	Page<CommentDTO> getCommentsByResponseId(Integer resId, org.springframework.data.domain.Pageable pageable);
}