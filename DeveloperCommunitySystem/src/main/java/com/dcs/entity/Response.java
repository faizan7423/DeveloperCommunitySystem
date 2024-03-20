package com.dcs.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.dcs.dto.DeveloperDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Response {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "responseId")
	private Integer responseId;
	private String answer;
	private LocalDateTime respDateTime;
	@ManyToOne
	@JoinColumn(name = "postId", referencedColumnName = "postId")
	private Post post;
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	private Developer developer;
	@OneToMany(mappedBy = "response")
	private List<Comment> listOfComments;
	@OneToMany(mappedBy = "response", cascade = CascadeType.REFRESH)
	private List<Vote> vote;

	public Integer getResponseId() {
		return responseId;
	}

	public void setResponseId(Integer responseId) {
		this.responseId = responseId;
	}

	public String getAnswer(String string) {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public LocalDateTime getRespDateTime() {
		return respDateTime;
	}

	public void setRespDateTime(LocalDateTime respDateTime) {
		this.respDateTime = respDateTime;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Developer getDeveloper() {
		return developer;
	}

	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}

	public List<Comment> getListOfComments() {
		return listOfComments;
	}

	public void setListOfComments(List<Comment> listOfComments) {
		this.listOfComments = listOfComments;
	}

	public List<Vote> getVote() {
		return vote;
	}

	public void setVote(List<Vote> vote) {
		this.vote = vote;
	}

	@Override
	public String toString() {
		return "Response [responseId=" + responseId + ", answer=" + answer + ", respDateTime=" + respDateTime
				+ ", post=" + post + ", developer=" + developer + ", listOfComments=" + listOfComments + ", vote="
				+ vote + "]";
	}

	public Response(Integer responseId, String answer, LocalDateTime respDateTime, Post post, Developer developer,
			List<Comment> listOfComments, List<Vote> vote) {
		super();
		this.responseId = responseId;
		this.answer = answer;
		this.respDateTime = respDateTime;
		this.post = post;
		this.developer = developer;
		this.listOfComments = listOfComments;
		this.vote = vote;
	}
	//default constructor
	public Response() {
		super();
	}

	public void setDeveloper(DeveloperDTO developer2) {
		// TODO Auto-generated method stub
		this.developer = developer;
		
	}

	

}