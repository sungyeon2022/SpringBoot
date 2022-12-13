package com.example.demo.user;

import java.security.Principal;
import java.util.Optional;
import com.example.demo.DataNotFoundException;

import groovyjarjarantlr4.v4.parse.ANTLRParser.id_return;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService{
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public SiteUser create(String username, String email,String password) {
		SiteUser user = new SiteUser();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		this.userRepository.save(user);
		return user;
	}
	public SiteUser getUser(String username) {
		Optional<SiteUser> siteUser = this.userRepository.findByUsername(username);
		if(siteUser.isPresent()) {
			return siteUser.get();
		} else {
			throw new DataNotFoundException("사용자를 찾을 수 없습니다.");
		}
	}
	
	public boolean matcher(String username, String submitpw) {
		Optional<SiteUser> siteUser = this.userRepository.findByUsername(username);
		if(!siteUser.isPresent()) {
			throw new DataNotFoundException("사용자를 찾을 수 없습니다.");		
			}
		return passwordEncoder.matches(submitpw,siteUser.get().getPassword());
	}
	
	public void modifyPw(SiteUser siteUser, String modipw) throws Exception {
		String securePw = passwordEncoder.encode(modipw);
		siteUser.setPassword(securePw);
		this.userRepository.save(siteUser);
	}
}
