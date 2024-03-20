package com.dcs.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dcs.entity.Developer;

@Repository

public interface DeveloperDao extends JpaRepository<Developer, Integer> {
	//Retrieves a Page of developers based on their status.
	Page<Developer> findByStatus(String status, Pageable pageable);
	//Retrieves a list of developers based on their reputation.
	List<Developer> findByReputation(Integer reputation);
	//Retrieves a list of developers based on the number of posts they have and the post topic.
	@Query("SELECT d FROM Developer d JOIN d.listOfPosts p WHERE SIZE(d.listOfPosts) = :noOfPosts AND p.topic = :topic")
	List<Developer> findByNoOfPosts(@Param("noOfPosts") int noOfPosts, @Param("topic") String topic);
}
