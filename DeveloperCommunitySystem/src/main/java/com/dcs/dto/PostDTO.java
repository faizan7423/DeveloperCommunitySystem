package com.dcs.dto;


import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
	// Unique identifier for the post
	private Integer postId;
	private String query;
	// Date and time when the post was created
	private LocalDateTime postDateTime;
	private String topic;
	private DeveloperDTO developer;
	private List<ResponseDTO> listOfResponse;
	private Integer noOfViews;
	public Integer getPostId() {
		return postId;
	}
	//getter and setter methods
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public LocalDateTime getPostDateTime() {
		return postDateTime;
	}
	public void setPostDateTime(LocalDateTime postDateTime) {
		this.postDateTime = postDateTime;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public DeveloperDTO getDeveloper() {
		return developer;
	}
	public void setDeveloper(DeveloperDTO developer) {
		this.developer = developer;
	}
	public Integer getNoOfViews() {
		return noOfViews;
	}
	public void setNoOfViews(Integer noOfViews) {
		this.noOfViews = noOfViews;
	}
	@Override
	public String toString() {
		return "PostDTO [postId=" + postId + ", query=" + query + ", postDateTime=" + postDateTime + ", topic=" + topic
				+ ", developer=" + developer + ", noOfViews=" + noOfViews + "]";
	}
	
}