package com.prabesh.quiz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prabesh.quiz.dao.QuizRepository;
import com.prabesh.quiz.model.Question;
import com.prabesh.quiz.model.QuestionWrapper;
import com.prabesh.quiz.model.Response;

@Service
public class QuizService {
	
	@Autowired
	QuizRepository repo;

	public ResponseEntity<String> createQuiz(List<Question> question) {
		
		List<Question> q = repo.saveAll(question);
		
		if(q != null) {
			return new ResponseEntity<>("Created", HttpStatus.CREATED);
		}
		
		return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
		
	}

	public ResponseEntity<List<Question>> getAllQuestions() {
		return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
	}

	public ResponseEntity<List<Question>> getQuestionsbyCatg(String category) {
		return  new ResponseEntity<>(repo.findByCategory(category), HttpStatus.OK);
	}

	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, int noOfQ) {
		return new ResponseEntity<>(repo.findRandomQuestionsForQuiz(category, noOfQ), HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
		
		List<Question> questions = repo.findAllById(questionIds);
		
		List<QuestionWrapper> wrapperList = new ArrayList<>();
		
		for (Question q : questions) {
			QuestionWrapper wrapper = new QuestionWrapper();
			wrapper.setId(q.getId());
			wrapper.setQuestionTitle(q.getQuestionTitle());
			wrapper.setOption1(q.getOption1());
			wrapper.setOption2(q.getOption2());
			wrapper.setOption3(q.getOption3());
			wrapper.setOption4(q.getOption4());
			
			wrapperList.add(wrapper);
		}
		return new ResponseEntity<>(wrapperList, HttpStatus.OK);
	}

	public ResponseEntity<Integer> getScore(List<Response> response) {
		
		AtomicInteger right = new AtomicInteger(0);
		
		response.forEach(res -> {
			Question question = repo.findById(res.getId()).orElse(null);
			
			if(question != null && res.getResponse().equals(question.getRightAnswer())) {
				right.getAndIncrement();
			}
		});
		return new ResponseEntity<>(right.get(), HttpStatus.OK);
	}
	
}
