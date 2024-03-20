package com.dcs.dto;

import java.time.LocalDate;
import java.util.List;

import com.dcs.entity.Developer;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

//import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class DeveloperDTO extends UserDTO {
	// Developer's name with validation for non-blank and maximum length
	@NotBlank(message = "Developer name cannot be blank")
	@Size(max = 20, message = "devName field cannot exceed maximum length of 20")
	private String devName;
	 // Developer's skill with validation for non-blank
	@NotBlank(message = "Developer skill cannot be blank")
	private String devSkill; 
	// Date when the developer became a member with validation for past or present
	@NotNull(message = "Member since cannot be null")
	@PastOrPresent(message = "Date must be in the pas ot present")
	private LocalDate memberSince;
	// If no. of Upvote on Post is 5, then reputation value is 1
	// If no. of Upvote on Post is 10, then reputation value is 2 and so on
	// Reputation score of the developer with validation for maximum value
	@Max(value = 100, message = "Reputation cannot exceed 100")
	@NotNull(message = "Reputation cannot be null")
	private Integer reputation;
	

//	public List<PostDTO> getListOfPosts() {
//		return listOfPosts;
//	}
//
//	public void setListOfPosts(List<PostDTO> listOfPosts) {
//		this.listOfPosts = listOfPosts;
//	}

	// Block or Unblock

	private String status;
	// List of posts associated with the developer
	private List<PostDTO> listOfPosts;
	// Number of posts created by the developer
	private Integer noOfPosts;
	public DeveloperDTO(Developer developer1, Developer developer12, Developer developer13, Developer developer14) {
		// super();
	}

	public DeveloperDTO() {
		super();
	}

	

	public DeveloperDTO(Integer userId, @NotNull String userName, @Min(5) @Max(10) String userPassword,
			String userRole, @NotNull String devName, String devSkill, LocalDate memberSince, Integer reputation,
			String status, List<PostDTO> listOfPosts, Integer noOfPosts) {
		super(userId, userName, userPassword, userRole);
		this.devName = devName;
		this.devSkill = devSkill;
		this.memberSince = memberSince;
		this.reputation = reputation;
		this.status = status;
		this.listOfPosts = listOfPosts;
		this.noOfPosts = noOfPosts;
	}
    // Getter and setter methods for the number of posts
	public Integer getNoOfPosts() {
		return noOfPosts;
	}

	public void setNoOfPosts(Integer noOfPosts) {
		this.noOfPosts = noOfPosts;
	}
	// Getter and setter methods for the developer's name
	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}
	// Getter and setter methods for the developer's skill
	public String getDevSkill() {
		return devSkill;
	}

	public void setDevSkill(String devSkill) {
		this.devSkill = devSkill;
	}
	// Getter and setter methods for the member since date
	public LocalDate getMemberSince() {
		return memberSince;
	}
	public void setMemberSince(LocalDate memberSince) {
		this.memberSince = memberSince;
	}
	// Getter and setter methods for the reputation score
	public Integer getReputation() {
		return reputation;
	}

	public void setReputation(Integer reputation) {
		this.reputation = reputation;
	}
	 // Getter and setter methods for the status
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	// Override toString method for better representation
	@Override
	public String toString() {
		return "DeveloperDTO [devName=" + devName + ", devSkill=" + devSkill + ", memberSince=" + memberSince
				+ ", reputation=" + reputation + ", status=" + status + ", listOfPosts=" + listOfPosts + ", noOfPosts="
				+ noOfPosts + "]";
	}

}