package com.dcs.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.dcs.dto.UserDTO;
import com.dcs.exception.DeveloperCommunitySystemException;
import com.dcs.service.IUserService;

import jakarta.validation.Valid;
 
 
@RestController
@RequestMapping("/user")
public class UserController {
@Autowired
IUserService userService;
// Method to register a new user
@PostMapping(path="register",consumes=MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO user){
	UserDTO newUser=userService. registerUser(user);
	return new ResponseEntity<UserDTO>(newUser,HttpStatus.OK);
}
//Method for user sign-in
@GetMapping(path="signin/{userName}")
public ResponseEntity<UserDTO> signIn(@PathVariable String userName, String password) throws DeveloperCommunitySystemException{
	UserDTO newUser=userService. signIn(userName,password);
	return new ResponseEntity<UserDTO>(newUser,HttpStatus.OK);
}
//Method for user sign-out
@GetMapping(path="signout")
public ResponseEntity<String> signOut(){
	String newUser=userService. signOut();
	return new ResponseEntity<String>(newUser,HttpStatus.OK);
}
}