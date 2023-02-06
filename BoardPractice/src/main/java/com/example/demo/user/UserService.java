package com.example.demo.user;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;
import com.example.demo.DataNotFoundException;

import com.example.demo.UserRole;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
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
		user.setCreateAt(LocalDateTime.now());
		user.setRole(UserRole.USER.getValue());
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
		if(siteUser.isEmpty()) {
			throw new DataNotFoundException("사용자를 찾을 수 없습니다.");		
			}
		return passwordEncoder.matches(submitpw,siteUser.get().getPassword());
	}
	
	public void modifyPw(SiteUser siteUser, String modipw) {
		String securePw = passwordEncoder.encode(modipw);
		siteUser.setPassword(securePw);
		siteUser.setUpdateAt(LocalDateTime.now());
		this.userRepository.save(siteUser);
	}

}
