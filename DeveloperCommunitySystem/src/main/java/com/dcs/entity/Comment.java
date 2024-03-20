package com.dcs.entity;

import java.time.LocalDate;

import com.dcs.dto.DeveloperDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Comment {
	// Unique identifier for the comment
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "commentId")
	private Integer commentId;
	// Text content of the comment
	@Column(nullable = false)
	private String text;
	// Developer who created the comment
	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	private Developer createdBy;
	// Response associated with the comment
	@ManyToOne
	@JoinColumn(name = "responseId")
	@JsonIgnore
	private Response response;

	private LocalDate createdDate;

	private Integer postId;

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public Comment(Integer commentId, String text, Developer createdBy, Integer postId, LocalDate createdDate) {
		super();
		this.commentId = commentId;
		this.text = text;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.postId = postId;
	}

	// getter and setter methods
	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Developer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Developer developerDTO) {
		this.createdBy = developerDTO;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", text=" + text + ", createdBy=" + createdBy + ", response="
				+ response + ", createdDate=" + createdDate + "]";
	}

}