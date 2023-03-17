package com.id.springsecurity.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.id.springsecurity.models.User;

@Service
public class UserService {
	
	List<User> list=new ArrayList<>();
	
	
	
	public UserService() {
		list.add(new User("abc","abc","abc@gmail.com"));
		list.add(new User("xyz","abcxyz","xyz@gmail.com"));
	}
	
	
	//get all users
	public List<User> getAllUsers(){
		return this.list;
	}
	//get single user
	public User getUser(String username) {
		return this.list.stream().filter((user)->user.getUsername().equals(username)).findAny().orElse(null);
	}
	
	//add users
	public User addUser(User user) {
		this.list.add(user);
		return user;
	}

}