package com.company.retrospective.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.retrospective.model.User;
import com.company.retrospective.model.UserDetails;
import com.company.retrospective.repository.UserDetailsRepository;

@RestController
@RequestMapping(path = "/v1/userdetails")
public class UserDetailsController {
	
	@Autowired
	UserDetailsRepository userDetailsRepository;

	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDetails> getUserDetailsByUserId(@PathVariable String id) {
		System.out.println("Request for Get User Details with UId "+id);
		UserDetails userDetails = userDetailsRepository.findByUserId(id);
		System.out.println("Response for getUser Details "+userDetails);
		
		MultiValueMap<String, String> headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		return new ResponseEntity<>(userDetails,headers, HttpStatus.OK);
	}
	
	@GetMapping
	public List<UserDetails> getAlluserDetails(){
		
		System.out.println("Get all user request");
		List<UserDetails> list = userDetailsRepository.findAll();
		System.out.println("Get all user details response "+list);
		return list;
	}
	
	@PostMapping
	@CrossOrigin(origins = "*")
	public ResponseEntity<UserDetails> saveUserDetails(@RequestBody UserDetails userDetails) {
		
		System.out.println("Saving the UserDetails "+userDetails);
		UserDetails userDetail =  userDetailsRepository.save(userDetails);
		System.out.println("Saved Object "+userDetail);
	//	MultiValueMap<String, String> headers = new HttpHeaders();
	//	headers.add("Access-Control-Allow-Origin", "*");
		return new ResponseEntity<>(userDetails, HttpStatus.OK);	}
	
	
	
}