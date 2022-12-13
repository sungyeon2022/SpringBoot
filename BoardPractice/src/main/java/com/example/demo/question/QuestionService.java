package com.example.demo.question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.example.demo.DataNotFoundException;
import com.example.demo.answer.Answer;
import com.example.demo.user.SiteUser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {	
	private final QuestionRepository questionRepository;
	
	private Specification<Question> search(String kw){
		return new Specification<>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Question> q, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				query.distinct(true);//중복 제거
				Join<Question, SiteUser> u1 = q.join("author", JoinType.LEFT);
				Join<Question, Answer> a  = q.join("answerList", JoinType.LEFT);
				Join<Answer, SiteUser> u2 = a.join("author",JoinType.LEFT);
				return  cb.or(
						cb.like(q.get("subject"), "%" + kw + "%"),//제목
						cb.like(q.get("content"), "%" + kw + "%"), //내용
						cb.like(u1.get("username"), "%" + kw + "%"), //작성자
						cb.like(a.get("content"), "%" + kw + "%"), // 답변 내용
						cb.like(u2.get("username"), "%" + kw + "%")//답변 작성자
						);
			}
		};
	}
	
	public Page<Question> getList(int page, String kw, String pagesort){
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.asc(pagesort));
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		Specification<Question> spec = search(kw);
		return this.questionRepository.findAll(spec, pageable);
	}
	
	public Question getQuestion(Integer id) {
		Optional<Question> question = this.questionRepository.findById(id);
		if (question.isPresent()) {
			return question.get();
			} else {
				throw new DataNotFoundException("question not found");
		}
	}
	
	public void create(String subject, String content, SiteUser user) {
		Question q =new Question();
		q.setSubject(subject);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());
		q.setAuthor(user);
		this.questionRepository.save(q);
		//질문 저장 메서드 생성
	}
	
	public void modify(Question question, String subject, String content) {
		question.setSubject(subject);
		question.setContent(content);
		question.setModifyDate(LocalDateTime.now());
		this.questionRepository.save(question);
	}
	public void delete(Question question) {
		this.questionRepository.delete(question);
	}
	
	public void vote(Question question, SiteUser siteUser) {
		question.getVoter().add(siteUser);
		this.questionRepository.save(question);
	}
	
	public void read(Question question, SiteUser siteUser) {
		question.getRead().add(siteUser);
		this.questionRepository.save(question);
	}
}
