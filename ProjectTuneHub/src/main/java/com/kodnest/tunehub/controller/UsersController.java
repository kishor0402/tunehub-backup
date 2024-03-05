
package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.service.SongService;
import com.kodnest.tunehub.serviceimpl.UserServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController {
	@Autowired
	UserServiceImpl serviceImpl;
	@Autowired
	SongService songService;
	@PostMapping("/register")
	public String addUser(@ModelAttribute User user) {
 
		String email=user.getEmail();
		boolean status = serviceImpl.emailExists(email);

		if(status == false) {
			serviceImpl.addUser(user);
			System.out.println("user adder");
		}
		else {
			System.out.println("user already exist");
		}
		return "Home";
	}
	@PostMapping("/validate")
	public String validate(@RequestParam("email")String email,
			@RequestParam("password")String password, HttpSession session,Model model) {

		if(serviceImpl.validateUser(email,password)==true) {
			String role=serviceImpl.getRole(email);
			session.setAttribute("email",email);
			if(role.equals("admin")){
				return "admin";
			}
			else{
				User user= serviceImpl.getUser(email);
				boolean userstatus=user.isIspremium();
				List<Song>fetchAllSong=songService.fetchAllSong();
				model.addAttribute("songs", fetchAllSong);
				model.addAttribute("ispremium",userstatus);
	
			
				return "costmer";
			}
		}else{
			return "login";
		}
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return"login";
	}
}

