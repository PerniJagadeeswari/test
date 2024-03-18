package com.example.tunehubproject.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tunehubproject.entities.Songs;
import com.example.tunehubproject.entities.Users;
import com.example.tunehubproject.services.SongsService;
import com.example.tunehubproject.services.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController 
{
	@Autowired
	//Marks a constructor, field, setter method, 
	//or config method as to be autowired bySpring's 
	//dependency injection facilities.
	UsersService userv;
	
	@Autowired
	SongsService songserv;

	@PostMapping("/register")
	//Annotation for mapping HTTP POST requests 
	//onto specific handlermethods
	public String addUser(@ModelAttribute Users user) {
		boolean userstatus = userv.emailExists
				(user.getEmail());
		if(userstatus == false) {
			userv.addUser(user);
			return "login";
		}
		else
		{
			return "login";
		}
	}

	@PostMapping("/login")
	public String validateUser(@RequestParam String email,
			@RequestParam String password, HttpSession session)
	{
		//invoking validateUser() in service
		if(userv.validateUser(email, password) == true)
		{
			
			session.setAttribute("email", email);
			//checking whether the user is admin or customer
			if(userv.getRole(email).equals("admin"))
			{
				return "adminhome";
			}
			else
			{
				return "customerhome";
			}
		}
		else
		{
			return "login";
		}
	}
	
	
	
	@GetMapping("/exploreSongs")
	public String exploreSongs(HttpSession session, Model model) {
		
			String email = (String) session.getAttribute("email");
			
			Users user = userv.getUser(email);
			
			boolean userStatus = user.isPremium();
			if(userStatus == true) {
				List<Songs> songslist = songserv.fetchAllSongs();
				model.addAttribute("songslist", songslist);
				return "displaysongs";
			}
			else {
				return "payment";
			}
	}
}