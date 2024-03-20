package com.dcs.dto;

 

import com.dcs.entity.Response;
import com.dcs.util.VoteType;
 
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteDTO {
	private Integer voteId;
	private VoteType voteType;
	private Integer responseId;
	private Integer userId;
	public Integer getVoteId() {
		return voteId;
	}
	//getter and setter methods
	public void setVoteId(Integer voteId) {
		this.voteId = voteId;
	}
	public VoteType getVoteType() {
		return voteType;
	}
	public void setVoteType(VoteType voteType) {
		this.voteType = voteType;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getResponseId() {
		return responseId;
	}
	public void setResponseId(Integer responseId) {
		this.responseId = responseId;
	}
	public VoteDTO(Integer voteId, VoteType voteType, Integer userId, Integer responseId) {
		super();
		this.voteId = voteId;
		this.voteType = voteType;
		this.userId = userId;
		this.responseId = responseId;
	}
	//default constructor
	public VoteDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "VoteDTO [voteId=" + voteId + ", voteType=" + voteType + ", userId=" + userId + ", responseId="
				+ responseId + "]";
	}

}