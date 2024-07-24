package com.exam.config;

import java.io.IOException;


import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.exam.service.impl.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter  {
	
	
	
	
	
	
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private JwtUtils jwtUtil;
	

//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		
//	final String requestTokenHeader=	request.getHeader("Authorization");
//	System.out.println(requestTokenHeader);
//	String username=null;
//	String jwtToken=null;
//	if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer")) {
//		
//		//yes
//		jwtToken=requestTokenHeader.substring(7);
//		try {
//		username=this.jwtUtil.extractUsername(jwtToken);
//		}
//		catch (ExpiredJwtException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			System.out.println("Jwt token has expired");
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("error");
//		}
//	}
//	else
//	{
//		System.out.println("Invalid Token ,not start with bearer string");
//	}
//
//	//validate
//	
//	if(username!= null &&SecurityContextHolder.getContext().getAuthentication()==null)
//	{
//	final	UserDetails userDetails= this.userDetailsServiceImpl.loadUserByUsername(username);
//	if(this.jwtUtil.validateToken(jwtToken, userDetails))
//	{
//		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
//				new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
//		usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails((javax.servlet.http.HttpServletRequest) request));
//		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//	}
//	}
//	else
//	{
//		System.out.println("Token is not valid");
//	}
//	filterChain.doFilter(request, response);
//	}
//

	@Override
	protected void doFilterInternal(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response, javax.servlet.FilterChain filterChain)
			throws javax.servlet.ServletException, IOException {

		
		final String requestTokenHeader=	request.getHeader("Authorization");
		System.out.println(requestTokenHeader);
		String username=null;
		String jwtToken=null;
		if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer")) {
			
			//yes
			jwtToken=requestTokenHeader.substring(7);
			try {
			username=this.jwtUtil.extractUsername(jwtToken);
			}
			catch (ExpiredJwtException e) {
				// TODO: handle exception
				e.printStackTrace();
				System.out.println("Jwt token has expired");
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("error");
			}
		}
		else
		{
			System.out.println("Invalid Token ,not start with bearer string");
		}

		//validate
		
		if(username!= null &&SecurityContextHolder.getContext().getAuthentication()==null)
		{
		final	UserDetails userDetails= this.userDetailsServiceImpl.loadUserByUsername(username);
		if(this.jwtUtil.validateToken(jwtToken, userDetails))
		{
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
					new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
			usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails((javax.servlet.http.HttpServletRequest) request));
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}
		}
		else
		{
			System.out.println("Token is not valid");
		}
		filterChain.doFilter(request, response);
		
		// TODO Auto-generated method stub
		
	}
	}
