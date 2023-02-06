package com.example.demo.question;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionForm {
	@NotEmpty(message = "제목이 비어있습니다.")
	@Size(max = 200, message = "제목이 너무 깁니다.")
	private String subject;
	
	@NotEmpty(message = "내용이 비어있습니다.")
	private String content;

	@NotEmpty(message = "카테고리를 선택해주세요.")
	private String category;
}
//에러 처리
