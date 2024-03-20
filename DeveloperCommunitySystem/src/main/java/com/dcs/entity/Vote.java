package com.dcs.entity;



import com.dcs.util.VoteType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Vote {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "voteId")
	private Integer voteId;
	
	@Enumerated(EnumType.STRING)
	private VoteType voteType;
	
	@OneToOne
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	private Developer developerWhoVoted;
	
	@ManyToOne
	@JoinColumn(name = "responseId")
	private Response response;

	public Integer getVoteId() {
		return voteId;
	}

	public void setVoteId(Integer voteId) {
		this.voteId = voteId;
	}

	public VoteType getVoteType() {
		return voteType;
	}

	public void setVoteType(VoteType voteType) {
		this.voteType = voteType;
	}

	public Developer getDeveloperWhoVoted() {
		return developerWhoVoted;
	}

	public void setDeveloperWhoVoted(Developer developerWhoVoted) {
		this.developerWhoVoted = developerWhoVoted;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public Vote(Integer voteId, VoteType voteType, Developer developerWhoVoted, Response response) {
		super();
		this.voteId = voteId;
		this.voteType = voteType;
		this.developerWhoVoted = developerWhoVoted;
		this.response = response;
	}
	//default constructor
	public Vote() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Vote [voteId=" + voteId + ", voteType=" + voteType + ", developerWhoVoted=" + developerWhoVoted
				+ ", response=" + response + "]";
	}

}