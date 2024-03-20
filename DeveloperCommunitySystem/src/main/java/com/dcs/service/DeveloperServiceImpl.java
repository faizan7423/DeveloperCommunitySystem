package com.dcs.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dcs.dao.DeveloperDao;
import com.dcs.dao.PostDao;
import com.dcs.dto.DeveloperDTO;
import com.dcs.dto.PostDTO;
import com.dcs.entity.Developer;
import com.dcs.entity.Post;
import com.dcs.exception.DeveloperCommunitySystemException;

@Service
public class DeveloperServiceImpl implements IDeveloperService {
	@Autowired
	DeveloperDao developerDao;
	
    @Autowired
    private PostDao postDao;

	@Autowired
	private ModelMapper modelMapper;
	
	 private PostDTO convertToDTO(Post post) {
	        return modelMapper.map(post, PostDTO.class);
	    }

	@Override
	public DeveloperDTO addDeveloper(DeveloperDTO developer) {
		Developer entity1 = modelMapper.map(developer, Developer.class);
		entity1 = developerDao.save(entity1);
		return modelMapper.map(entity1, DeveloperDTO.class);
	}

	@Override
	public DeveloperDTO getDeveloperById(Integer devId) throws DeveloperCommunitySystemException {
		Developer orElse= developerDao.findById(devId).orElseThrow(
				()-> new DeveloperCommunitySystemException("Developer Not Found"));
		return modelMapper.map(orElse, DeveloperDTO.class);

	}

	@Override
	public List<DeveloperDTO> getDeveloperByReputation(Integer reputation) throws DeveloperCommunitySystemException {
		List<Developer> entity4 = developerDao.findByReputation(reputation);
		if(!(developerDao.existsById(reputation))||entity4 == null)
		{
			throw new DeveloperCommunitySystemException("Please enter correct reputation",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		List<DeveloperDTO> developerDTOs = entity4.stream().map(entity -> modelMapper.map(entity, DeveloperDTO.class))
				.collect(Collectors.toList());
		return developerDTOs;
	}
	@Override
	public Page<PostDTO> getAllPostsByDeveloper(Integer userId, Pageable pageable) throws DeveloperCommunitySystemException {
	    Page<Post> postsPage = postDao.findByDeveloperUserId(userId, pageable);
	    if(!(developerDao.existsById(userId))||postsPage == null)
		{
			throw new DeveloperCommunitySystemException("Please enter correct userId",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	    return postsPage.map(this::convertToDTO);
	}

	@Override
	public Page<DeveloperDTO> getAllDevelopers(int page, int pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize);
		Page<Developer> developersPage = developerDao.findAll(pageable);
		List<DeveloperDTO> developerDTOs = developersPage.getContent().stream()
				.map(entity -> modelMapper.map(entity, DeveloperDTO.class)).collect(Collectors.toList());
		return new PageImpl<>(developerDTOs, developersPage.getPageable(), developersPage.getTotalElements());
			}
	
	@Override
	public List<DeveloperDTO> getByNoOfPosts(Integer noOfPosts, String topic) {
		List<Developer> entity5 = developerDao.findByNoOfPosts(noOfPosts, topic);
		List<DeveloperDTO> developerDTOs = entity5.stream().map(entity -> modelMapper.map(entity, DeveloperDTO.class))
				.collect(Collectors.toList());
		return developerDTOs;
	}
	
	
	

	@Override
	public Page<DeveloperDTO> getDevelopersByStatus(String status, Pageable pageable) {
		Page<Developer> developersPage = developerDao.findByStatus(status, pageable);
		List<DeveloperDTO> developerDTOs = developersPage.getContent().stream()
				.map(entity -> modelMapper.map(entity, DeveloperDTO.class)).collect(Collectors.toList());
		return new PageImpl<>(developerDTOs, developersPage.getPageable(), developersPage.getTotalElements());

	}

//	@Override
//	public DeveloperDTO updateDeveloper(DeveloperDTO developer) {
//		Developer entity1 = modelMapper.map(developer, Developer.class);
//		entity1 = developerDao.save(entity1);
//		DeveloperDTO entity = modelMapper.map(entity1, DeveloperDTO.class);
//		return entity;
//	}
	@Override
	public DeveloperDTO updateDeveloper(Integer userId,DeveloperDTO developer) throws Exception {
		Optional<Developer> dev=developerDao.findById(userId);
		if(dev.isPresent())
		{
			Developer devp = dev.get();
			//devp.setUserId(developer.getUserId());
			devp.setDevName(developer.getDevName());
			devp.setDevSkill(developer.getDevSkill());
			devp.setReputation(developer.getReputation());
			devp.setStatus(developer.getStatus());
			devp.setMemberSince(developer.getMemberSince());
			devp.setUserRole(developer.getUserRole());
			devp.setUserName(developer.getUserName());
			devp.setUserPassword(developer.getUserPassword());
			developerDao.save(devp);
		}
		if (!(developerDao.existsById(userId))||dev == null) {
			 
			throw new DeveloperCommunitySystemException("Enter Correct UserId",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return developer;
	}

	@Override
	public DeveloperDTO removeDeveloper(Integer devId) {
		Developer developer = developerDao.findById(devId).orElse(null);
		if (developer != null) {
			developerDao.delete(developer);
			DeveloperDTO entity1 = modelMapper.map(developer, DeveloperDTO.class);
			return entity1;
		}
		return modelMapper.map(developer, DeveloperDTO.class);
	}

	public List<DeveloperDTO> viewDevelopers() {
		List<DeveloperDTO> developerDTOs = new ArrayList<DeveloperDTO>();
		List<Developer> developer = developerDao.findAll();

		for (Developer developer1 : developer) {
			DeveloperDTO developerDTO = new DeveloperDTO(developer1, developer1, developer1, developer1);
			developerDTO.setDevName(developer1.getDevName());
			developerDTO.setDevSkill(developer1.getDevSkill());
			developerDTO.setMemberSince(developer1.getMemberSince());
			developerDTO.setReputation(developer1.getReputation());
			developerDTO.setUserId(developer1.getUserId());
			developerDTO.setUserName(developer1.getUserName());
			developerDTO.setUserPassword(developer1.getUserPassword());
			developerDTO.setUserRole(developer1.getUserRole());
			developerDTOs.add(developerDTO);
		}
		return developerDTOs;
	}

	@Override
	public String delete(Integer id) {
		Developer developer;
		try {
			developer = developerDao.findById(id).orElseThrow(() -> new DeveloperCommunitySystemException("Developer not found"));
			// Integer size = developer.getNoOfPassengers();

			developerDao.deleteById(id);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			return "Developer Not found";

		}
		return "Developer Deleted Successfully";
	}

}
