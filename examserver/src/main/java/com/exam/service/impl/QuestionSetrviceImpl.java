package com.exam.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.exam.Questions;
import com.exam.model.exam.Quiz;
import com.exam.repo.QuestionRepsitory;
import com.exam.service.QuestionService;
@Service
public class QuestionSetrviceImpl implements QuestionService {
	
	@Autowired
	private QuestionRepsitory questionRepsitory;

	public Questions addQuesion(Questions questions) {
		return this.questionRepsitory.save(questions);
	}

	
	public Questions updateQuesion(Questions questions) {
		return this.questionRepsitory.save(questions);
	}


	public Set<Questions> getQuestions() {
		return new HashSet<>(this.questionRepsitory.findAll());
	}


	public Questions getquestion(long questionId) {
		return this.questionRepsitory.findById(questionId).get();
	}

	
	public Set<Questions> getQuestionsOfQWuiz(Quiz quiz) {
		return this.questionRepsitory.findByQuiz(quiz);
	}


	@Override
	public void deleteQuestion(long quesId) {
		Questions questions=new Questions();
		questions.setQuesid(quesId);
	 this.questionRepsitory.delete(questions);
		
	}


	@Override
	public Questions get(long questionsId) {
		
		return this.questionRepsitory.getOne(questionsId);
	}

}
