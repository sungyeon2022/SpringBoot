package com.example.demo.question;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionRepository extends JpaRepository<Question, Integer>{
		Question findBySubject(String subject);
		Question findBySubjectAndContent(String subject,String content);
		List<Question> findBysubjectLike(String subject);
		Page<Question> findAll(Pageable pageable);
		Page<Question> findAll(Specification<Question> spec, Pageable pageable);
}
