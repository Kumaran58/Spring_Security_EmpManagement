package com.week17.GradedProj.SpringSecurity.SercurityConfig;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	UserRepo repo;

	public void add(UserCredentials user)
	{
		repo.save(user);
	}
	
	public List<UserCredentials> getAll()
	{
		return repo.findAll();
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<UserCredentials> userCred = repo.findByName(username);
		
		//we are converting the roles from db to grantedAuth of userDetailsService
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		
		for(String temp : userCred.get().getAuthorities())
		{
			GrantedAuthority ga = new SimpleGrantedAuthority(temp);
			grantedAuthorities.add(ga);
		}
		
		//converting userCredentials to user from user security
		User user = new User(username, userCred.get().getPassword(), grantedAuthorities);
		return user;
	}

}
