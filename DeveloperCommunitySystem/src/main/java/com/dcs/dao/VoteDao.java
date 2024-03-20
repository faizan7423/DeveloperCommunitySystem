package com.dcs.dao;
 
import java.util.List;

 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
//Repository interface for managing Vote entities.
//Provides basic CRUD operations for Vote entities.
 
import com.dcs.entity.Vote;
import com.dcs.util.VoteType;
@Repository
public interface VoteDao extends JpaRepository<Vote, Integer>{
	List<Vote> findByVoteTypeAndResponse_ResponseId(VoteType voteType, Integer responseId);

}