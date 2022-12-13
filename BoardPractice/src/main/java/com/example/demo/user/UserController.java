package com.example.demo.user;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.ScriptUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
	private final UserService userService;
	
	@GetMapping("/signup")
	public String signup(UserCreateForm userCreateForm) {
		return "signup_form";
	}
	@PostMapping("/signup")// signup 오버리딩
	public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "signup_form";
		}
		if(!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
			bindingResult.rejectValue("password2", "passwordInCorrect", 
					"2개의 패스워드가 일치하지 않습니다.");
			return "signup_form";
		}
		try {
			userService.create(userCreateForm.getUsername(),userCreateForm.getEmail(),
					userCreateForm.getPassword1());
		}catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed","이미 등록된 사용자입니다.");
			return "signup_form";
		} catch (Exception e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed",e.getMessage());
			return "signup_form";
		}
		
		return "redirect:/";
	}
	@GetMapping("/login")
	public String login() {
		return "login_form";
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/info")
	public String Info(Model model,Principal principal) {
		SiteUser siteUser = this.userService.getUser(principal.getName());
		model.addAttribute("siteuser", siteUser);
		return "user_info";
	}
	
	@GetMapping("/pwmodi")
	public String pwmodi(PwModifyForm pwModifyForm) {
		return "pwmodi";
	}
	
	@PostMapping("/pwmodi")
	public String pwmodi(@ModelAttribute("pwModifyForm") @Valid PwModifyForm pwModifyForm,
			BindingResult bindingResult, Principal principal, HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		SiteUser siteUser = this.userService.getUser(principal.getName());
		if(bindingResult.hasErrors()) {
			bindingResult.reject("ModifyPwFailed","알수없는 오류입니다.");
			return "pwmodi";
		}
		if(!userService.matcher(siteUser.getUsername(), pwModifyForm.getNow_pw())) {
			bindingResult.rejectValue("now_pw", "now_pwInCorrect", 
					"현재 이용중인 비밀번호와 일치하지 않습니다.");
			return "pwmodi";
		}
		else if(!pwModifyForm.getModipw1().equals(pwModifyForm.getModipw2())) {
			bindingResult.rejectValue("password2", "modipwInCorrect", 
					"2개의 패스워드가 일치하지 않습니다.");
			return "pwmodi";
		} try {
			userService.modifyPw(siteUser, pwModifyForm.getModipw1());
			
		} catch (Exception e) {
			e.printStackTrace();
			bindingResult.reject("ModifyPwFailed","알수없는 오류입니다.");
			return "pwmodi";
		}
		ScriptUtil.alertAndMovePage(response, "비밀번호가 성공적으로 변경되었습니다.", "/");
		return "redirect";
	}
}
