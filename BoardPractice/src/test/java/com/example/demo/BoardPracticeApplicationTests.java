package com.example.demo;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.assertj.core.api.Assertions.in;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.intThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.hql.internal.NameGenerator;

//import java.util.List;
//import java.util.Optional;

//import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.answer.Answer;
import com.example.demo.answer.AnswerRepositoy;
import com.example.demo.question.Question;
import com.example.demo.question.QuestionRepository;
import com.example.demo.question.QuestionService;

import net.bytebuddy.asm.Advice.This;

@SpringBootTest
class BoardPracticeApplicationTests {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private AnswerRepositoy answerRepositoy;
	
	//@Transactional //Test과정에서 데이터베이스 세션이 종료된 후 하위 데이터를 가져올때 에러가 발생할수있다 따라서
				   //	세션이 종료되지않게 하기위해 에너테이션을 추가한다
	@Test
	void testJpa() {
		for(int i = 1;i<=300;i++) {
			String subject = String.format("테스트 데이터입니다 : [%03d]",i); 
			String content = "내용무";
			this.questionService.create(subject, content,null);
		}
		
//		Optional<Question> oq = this.questionRepository.findById(2);
//		assertTrue(oq.isPresent());
//		Question q = oq.get();
//		
//		List<Answer> answerList = q.getAnswerList();
//		
//		assertEquals(1, answerList.size());
//		assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
		
//		답변 조회 ID는 1
//		Optional<Answer> oa = this.answerRepositoy.findById(1);
//		assertTrue(oa.isPresent());
//		Answer a = oa.get();
//		assertEquals(2, a.getQuestion().getId());
		
//		질문 ID 2번의 대한 답변을 생성후 저장
//		Optional<Question> oq = this.questionRepository.findById(2);
//		assertTrue(oq.isPresent());
//		Question q = oq.get();
//		
//		Answer a = new Answer();
//		a.setContent("네 자동으로 생성됩니다.");
//		a.setQuestion(q);
//		a.setCreateData(LocalDateTime.now());
//		this.answerRepositoy.save(a);	
		
//		데이터에서 ID가 1번을 제거하고 데이터 총개수를 제거 전과 후로 카운트한다 
//		assertEquals(2, this.questionRepository.count());
//		Optional<Question> oq = this.questionRepository.findById(1);
//		assertTrue(oq.isPresent());
//		Question q = oq.get();
//		this.questionRepository.delete(q);
//		assertEquals(1, this.questionRepository.count());
		
//		제목 수정하기 제목 말고 내용도 수정가능
//		Optional<Question> oq = this.questionRepository.findById(1);
//		assertTrue(oq.isPresent());
//		Question q = oq.get();
//		q.setSubject("수정된 제목");
//		this.questionRepository.save(q);
		
		
//		pb%는 pb로 시작하는 문자열을 찾아 출력합니다
//		List<Question> qList = this.questionRepository.findBysubjectLike("pb%");
//		Question q = qList.get(0);
//		assertEquals("pb가 무엇인가요?", q.getSubject());
	}
}
	
	
//	void testJpa() {
//		Question q = this.questionRepository.findBySubjectAndContent("pb가 무엇인가요?", "pb에 대해서 알고 싶습니다.");
//		assertEquals(1, q.getId());
//	}
//}
	
//	void testJqa() { 아래 내용과 동일 v3 통과
//		Question q = this.questionRepository.findBySubject("pb가 무엇인가요?");
//		assertEquals(1, q.getId());
//	}
	
//	void testJpa() { 질문 데이터베이스에서 내용 찾는 구문 v2 데스트 통과
//		Optional<Question> oq = this.questionRepository.findById(1);
//		if(oq.isPresent()) {
//			Question q = oq.get();
//			assertEquals("pb가 무엇인가요?", q.getSubject());
//		}
//	}
	
//	void testJpa() { 질문 데이터베이스에서 2번째 구문과 구문내의 내용을 찾아냄 테스트 통과
//		List<Question> all = this.questionRepository.findAll();
//		assertEquals(2, all.size());
//		
//		Question q = all.get(0);
//		assertEquals("pb가 무엇인가요?", q.getSubject());	
	
	
	
//	void testJpa() { 질문 데이테베이스에 해당 질문을 추가하는 내용 테스트 통과
//		Question q1 = new Question(); 
//		q1.setSubject("pb가 무엇인가요?");
//		q1.setContent("pb프로젝트에 대해 알고 싶습니다.");
//		q1.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q1);
//		
//		Question q2 = new Question();
//		q2.setSubject("스프링부트 모델 질문입니다.");
//		q2.setContent("id는 자동으로 생성되나요?");
//		q2.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q2);
//	}
