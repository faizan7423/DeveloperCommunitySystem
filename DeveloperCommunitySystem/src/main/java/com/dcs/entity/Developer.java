package com.dcs.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("developer")
public class Developer extends User {
	private String devName;
	private String devSkill;
	private LocalDate memberSince;
	private Integer reputation;
	private Integer noOfPosts;
	private String status;
	@OneToMany(mappedBy = "developer", fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JsonIgnore
	private List<Post> listOfPosts;

	public Developer(Integer userId, String userName, String userPassword, String userRole, String devName,
			String devSkill, LocalDate memberSince, Integer reputation, String status,Integer noOfPosts, List<Post> listOfPosts) {
		super(userId, userName, userPassword, userRole);
		this.devName = devName;
		this.devSkill = devSkill;
		this.memberSince = memberSince;
		this.reputation = reputation;
		this.status = status;
		this.listOfPosts = listOfPosts;
		this.noOfPosts = noOfPosts;
	}

	public Developer(Integer userId, String userName, String userPassword, String userRole) {
		super(userId, userName, userPassword, userRole);
	}
    //setter and getter methods
	public String getDevName() {
		return devName;
	}

	public Integer getNoOfPosts() {
		return noOfPosts;
	}

	public void setNoOfPosts(Integer noOfPosts) {
		this.noOfPosts = noOfPosts;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getDevSkill() {
		return devSkill;
	}

	public void setDevSkill(String devSkill) {
		this.devSkill = devSkill;
	}

	public LocalDate getMemberSince() {
		return memberSince;
	}

	public void setMemberSince(LocalDate memberSince) {
		this.memberSince = memberSince;
	}

	public Integer getReputation() {
		return reputation;
	}

	public void setReputation(Integer reputation) {
		this.reputation = reputation;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Post> getListOfPosts() {
		return listOfPosts;
	}

	public void setListOfPosts(List<Post> listOfPosts) {
		this.listOfPosts = listOfPosts;
	}

	@Override
	public String toString() {
		return "Developer [devName=" + devName + ", devSkill=" + devSkill + ", memberSince=" + memberSince
				+ ", reputation=" + reputation + ", noOfPosts=" + noOfPosts + ", status=" + status + ", listOfPosts="
				+ listOfPosts + "]";
	}

	public Developer() {
		super();
	}

}