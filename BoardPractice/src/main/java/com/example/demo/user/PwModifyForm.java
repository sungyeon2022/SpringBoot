package com.example.demo.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PwModifyForm {
	
	@NotEmpty(message = "현재 비밀번호가 입력되지 않았습니다.")
	private String now_pw;
	
	@Size(min = 3,max = 20)
	@NotEmpty(message = "변경하실 비밀번호를 입력해주세요.")
	private String modipw1;
	
	@NotEmpty(message = "비밀번호 확인란이 비어있습니다.")
	private String modipw2;
}
