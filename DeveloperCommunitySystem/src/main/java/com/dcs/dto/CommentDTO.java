package com.dcs.dto;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;

import com.dcs.entity.Response;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
	// Unique identifier for the comment
	private Integer commentId;
	// Text content of the comment with size validation
	@Size(min = 10, max = 200, message = "Text should not be more than 200 characthers or less than 10 characters")
	private String text;
	// Developer information who created the comment
	private DeveloperDTO createdBy;
	// Identifier for the associated response
	private Integer responseId;
	// Associated response object
	private Response response;
	// Date when the comment was created
	@CreatedDate
	private LocalDate createdDate;
	 // Identifier for the associated post
	private Integer postId;
	// Getter and setter methods for the post identifier
	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	// Override toString method for better representation
	@Override
	public String toString() {
		return "CommentDTO [commentId=" + commentId + ", text=" + text + ", createdBy=" + createdBy + ", responseId="
				+ responseId + ", createdDate=" + createdDate + ", postId=" + postId + "]";
	}
	// Getter and setter methods for the response identifier
	public Integer getResponseId() {
		return responseId;
	}

	public void setResponseId(Integer responseId) {
		this.responseId = responseId;
	}
	// Getter and setter methods for the comment identifier
	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	// Getter and setter methods for the comment text
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	// Getter and setter methods for the developer who created the comment
	public DeveloperDTO getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(DeveloperDTO createdBy) {
		this.createdBy = createdBy;
	}
	 // Getter and setter methods for the comment creation date
	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	 // Default constructor
	public CommentDTO() {
		super();

	}

}
