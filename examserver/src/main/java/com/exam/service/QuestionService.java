package com.exam.service;

import java.util.Set;

import com.exam.model.exam.Questions;
import com.exam.model.exam.Quiz;

public interface QuestionService {
	
	public Questions addQuesion(Questions questions);
	
	public Questions updateQuesion(Questions questions);

	public Set<Questions>getQuestions();
	
	public Questions getquestion(long questionId);
	
	public Set<Questions> getQuestionsOfQWuiz(Quiz quiz);
	
	public void deleteQuestion(long quesId);
	
	public Questions get(long questionsId);
	
	

}
