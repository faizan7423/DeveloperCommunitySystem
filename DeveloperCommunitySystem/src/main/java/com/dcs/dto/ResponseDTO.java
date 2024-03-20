package com.dcs.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
@NotNull
	private Integer responseId;
	private String answer;
	private LocalDateTime respDateTime;
	private PostDTO post;
	private DeveloperDTO developer;
	public Integer getResponseId() {
		return responseId;
	}
	//getter and setter methods
	public void setResponseId(Integer responseId) {
		this.responseId = responseId;
	}
	public String getAnswer() {
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
	@Override
	public String toString() {
		return "ResponseDTO [responseId=" + responseId + ", answer=" + answer + ", respDateTime=" + respDateTime
				+ ", post=" + post + ", developer=" + developer + "]";
	}
	public PostDTO getPost() {
		return post;
	}
	public void setPost(PostDTO post) {
		this.post = post;
	}
	public DeveloperDTO getDeveloper() {
		return developer;
	}
	public void setDeveloper(DeveloperDTO developer) {
		this.developer = developer;
	}

}