package com.dcs.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dcs.entity.Post;
@Repository
public interface PostDao extends JpaRepository<Post, Integer> {
    //Retrieves a Page of posts based on the specified topic.
	Page<Post> findByTopic(String topic,Pageable pageable);
	//Retrieves a list of posts based on the specified topic.
	List<Post> findByTopic(String topic);
	// Retrieves a Page of posts based on the developer's user ID.
	Page<Post> findByDeveloperUserId(Integer userId, Pageable pageable);


}
