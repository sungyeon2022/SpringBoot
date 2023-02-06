package com.example.demo.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserSecurityService implements UserDetailsService{
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<SiteUser> siteUser = userRepository.findByUsername(username);

		if(siteUser.isPresent()) {
			SiteUser user = siteUser.get();

			SiteUser authUser = SiteUser.builder()
					.idLong(user.getIdLong())
					.username(user.getUsername())
					.password(user.getPassword())
					.email(user.getEmail())
					.role(user.getRole())
					.createAt(user.getCreateAt())
					.updateAt(user.getUpdateAt())
					.build();
			log.info("authUser : {}", authUser);
			return authUser;
		}
		return null;
	}

}
