package com.example.demo.answer;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.DataNotFoundException;
import com.example.demo.question.Question;
import com.example.demo.user.SiteUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerService {
	
	private final AnswerRepositoy answerRepositoy;
	
	public Answer create(Question question, String content,
			SiteUser author){
		Answer answer = new Answer();
		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());
		answer.setQuestion(question);
		answer.setAuthor(author);
		this.answerRepositoy.save(answer);
		return answer;
	}
	public Answer getAnswer(Integer id) {
		Optional<Answer> answer = this.answerRepositoy.findById(id);
		if (answer.isPresent()) {
			return answer.get();
		} else {
			throw new DataNotFoundException("answer not found");
		}
	}
	public void modify(Answer answer, String contnet) {
		answer.setContent(contnet);
		answer.setModifyDate(LocalDateTime.now());
		this.answerRepositoy.save(answer); // answer 저장
	}
	
	public void delete(Answer answer) {
		this.answerRepositoy.delete(answer);
	}
	
	public void vote(Answer answer,SiteUser siteUser) {
		answer.getVoter().add(siteUser);
		this.answerRepositoy.save(answer);
	}
}
