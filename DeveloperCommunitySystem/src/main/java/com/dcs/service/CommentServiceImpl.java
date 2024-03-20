package com.dcs.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dcs.dao.CommentDao;
import com.dcs.dto.CommentDTO;
import com.dcs.dto.DeveloperDTO;
import com.dcs.dto.PostDTO;
import com.dcs.entity.Comment;
import com.dcs.entity.Developer;
import com.dcs.entity.Post;
import com.dcs.exception.DeveloperCommunitySystemException;

//import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

@Service
public class CommentServiceImpl implements ICommentService {

	@Autowired
	private CommentDao commentDao;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDTO addComment(CommentDTO comment) {

		Comment entity1 = modelMapper.map(comment, Comment.class);
		entity1 = commentDao.save(entity1);

		CommentDTO savedCommentDTO = modelMapper.map(entity1, CommentDTO.class);
		savedCommentDTO.setResponseId(entity1.getResponse().getResponseId());
		return savedCommentDTO;

	}

//	@Override
//	public CommentDTO updateComment(CommentDTO comment) {
//
//		Comment entity1 = modelMapper.map(comment, Comment.class);
//		entity1 = commentDao.save(entity1);
//		return modelMapper.map(entity1, CommentDTO.class);
//	}
	@Override
	public CommentDTO updateComment(Integer commentId, CommentDTO comment) throws Exception {
		Optional<Comment> co = commentDao.findById(commentId);
		if (co.isPresent()) {
			Comment com = co.get();
			com.setText(comment.getText());
			commentDao.save(com);
		}
		if (!(commentDao.existsById(commentId)) || co == null) {
			throw new DeveloperCommunitySystemException("CommentId Not Found", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return comment;
	}

	@Override
	public CommentDTO removeComment(Integer respId) throws DeveloperCommunitySystemException {

		Comment existingResponseEntity = commentDao.findById(respId)
				.orElseThrow(() -> new DeveloperCommunitySystemException("Response not found"));

		commentDao.deleteById(respId);

		return modelMapper.map(existingResponseEntity, CommentDTO.class);
	}

	@Override
	public CommentDTO getByCommentId(Integer commentId) throws DeveloperCommunitySystemException {
		Comment comment = commentDao.findById(commentId).get();
		if (!(commentDao.existsById(commentId))) {
			throw new DeveloperCommunitySystemException("CommentId Not Found", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (comment != null) {
			CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);

			return commentDTO;
		} else {

			return null;
		}
	}

	private CommentDTO convertToDTO(Comment comment) {
		return modelMapper.map(comment, CommentDTO.class);

	}

	@Override
	public Page<CommentDTO> getCommentsByResponseId(Integer resId, Pageable pageable) {
		Page<Comment> commentPage = commentDao.getCommentsByResponseId(resId, pageable);
		return commentPage.map(this::convertToDTO);
	}

	@Override
	public Page<CommentDTO> getCommentsByPostId(Integer postId, Pageable pageable) {

		Page<Comment> commentPage = commentDao.getCommentsByPostId(postId, pageable);

		return commentPage.map(this::convertToDTO);
	}

	@Override
	public Integer getNoOfVotesOnCommentByVoteType(String voteType, Integer commentId) {
		// TODO Auto-generated method stub
		return null;
	}

}