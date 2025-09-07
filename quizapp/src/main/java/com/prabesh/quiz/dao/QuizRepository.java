package com.prabesh.quiz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.prabesh.quiz.model.Question;

public interface QuizRepository extends JpaRepository<Question, Integer>{

	List<Question> findByCategory(String category);

	@Query(value = " SELECT Q.ID FROM QUESTION WHERE Q.CATEGORY = :category order by random() limit :noOfQ", nativeQuery = true)
	List<Integer> findRandomQuestionsForQuiz(String category, int noOfQ);

}
