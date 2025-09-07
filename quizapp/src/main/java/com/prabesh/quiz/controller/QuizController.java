package com.prabesh.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prabesh.quiz.model.Question;
import com.prabesh.quiz.model.QuestionWrapper;
import com.prabesh.quiz.model.Response;
import com.prabesh.quiz.service.QuizService;

@RestController
public class QuizController {
	

	@Autowired
	QuizService service;
	
	@PostMapping("/add")
	public ResponseEntity<String> createQuiz (@RequestBody List<Question> question) {
		return service.createQuiz(question);
	}
	
	@GetMapping("/allQuestion")
	public ResponseEntity<List<Question>> getAllQuestions () {
		return service.getAllQuestions();
	}
	
	@GetMapping("/category/{category}")
	public ResponseEntity<List<Question>> getQuestionsbyCatg (@PathVariable String category) {
		return service.getQuestionsbyCatg(category);
	}
	
	@GetMapping("generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam int noOfQ) {
		return service.getQuestionsForQuiz(category, noOfQ);
	}
	
	@PostMapping("/getQuestion")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId (@RequestBody List<Integer> questionIds) {
		return service.getQuestionsFromId(questionIds);
	}
	
	@PostMapping("/getScore")
	public ResponseEntity<Integer> getScore (@RequestBody List<Response> response) {
		return service.getScore(response);
	}
 	
}
