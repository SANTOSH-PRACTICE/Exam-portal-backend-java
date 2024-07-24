package com.exam.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.config.JwtUtils;
import com.exam.model.JwtRequest;
import com.exam.model.JwtResponse;
import com.exam.model.User;
import com.exam.service.impl.UserDetailsServiceImpl;

@RestController
@CrossOrigin("*")
public class AuthenticateController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	@Autowired
	private JwtUtils jwtUtils;
	
	//ganrate token
	@PostMapping("/genrate-token")
	public ResponseEntity<?> genrateToken(@RequestBody JwtRequest jwtRequest) throws Exception
	{
		try {
			System.out.println(jwtRequest.getUsername());
			System.out.println(jwtRequest.getPassword());

			//authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
		}
		catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("User Not Found");
		}
		
		////Autheticate user
		UserDetails userDetails= this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());
		String token=this.jwtUtils.generateToken(userDetails);
		System.out.println("token: "+token);
		return ResponseEntity.ok(new JwtResponse(token));
		
	}
	
	private void  authenticate(String username,String password) throws Exception
	{
		try
		{
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}
		catch (DisabledException e) {
			throw new Exception("USER DISABLED"+e.getMessage());
			
		}
		catch (BadCredentialsException e) {
			// TODO: handle exception
			throw new Exception(" Invalid Crediatials"+e.getMessage());
		}
		
	}
	
	//return  the details of current user
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal)
	{
		return (User)this.userDetailsServiceImpl.loadUserByUsername(principal.getName());
		
		
	}

}
