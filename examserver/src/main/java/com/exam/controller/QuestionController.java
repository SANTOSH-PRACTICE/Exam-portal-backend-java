package com.exam.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.exam.Questions;
import com.exam.model.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;
@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuizService quizService;
	
	//add question
	@PostMapping("/")
	public ResponseEntity<Questions>add(@RequestBody Questions questions)
	{
		return ResponseEntity.ok(this.questionService.addQuesion(questions));
	}
	//update the question
	
	@PutMapping("/")
	public ResponseEntity<Questions>update (@RequestBody Questions questions)
	{
		return ResponseEntity.ok(this.questionService.updateQuesion(questions));
	}
	
	//get all question of any quiz
	
	@GetMapping("/quiz/{qid}")
	public ResponseEntity<?>getQuestionsOfQuiz(@PathVariable("qid") long qid)
	{
		Quiz quiz=this.quizService.getQuiz(qid);
		Set<Questions>questions=quiz.getQuestions();
		
		List<Questions> list=new ArrayList(questions);
		Collections.shuffle(list);
		if(list.size()>Integer.parseInt(quiz.getNumberOfQuestions()))
		{
			System.out.println(quiz.getNumberOfQuestions());
			list=list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions()));
		}
		list.forEach((q)->
		{
		q.setAnswer("");	
		});
		Collections.shuffle(list);
      	
	return ResponseEntity.ok(list);
		
		
//		Quiz quiz=new Quiz();
//		quiz.setqId(qid);
//	Set<Questions>questionsofQuiz	=this.questionService.getQuestionsOfQWuiz(quiz);
//	return ResponseEntity.ok(questionsofQuiz);
	}
	@GetMapping("/quiz/all/{qid}")
	public ResponseEntity<?>getQuestionsOfQuizAdmin(@PathVariable("qid") long qid)
	{   
		Quiz quiz=new Quiz();
		quiz.setqId(qid);
		Set<Questions>questions=this.questionService.getQuestionsOfQWuiz(quiz);
		return ResponseEntity.ok(questions);
		
		
		
      	
	
		
		
//		Quiz quiz=new Quiz();
//		quiz.setqId(qid);
//	Set<Questions>questionsofQuiz	=this.questionService.getQuestionsOfQWuiz(quiz);
//	return ResponseEntity.ok(questionsofQuiz);
	}
	//get single question
	@GetMapping("/{quesId}")
	public Questions get(@PathVariable("quesId") long quesId)
	{
		return this.questionService.getquestion(quesId);
	}
	
	//delete question
	@DeleteMapping("/{quesId}")
	public void delete(@PathVariable("quesId")long quesId)
	{
		this.questionService.deleteQuestion(quesId);
	}
	
	//eval quiz
	@PostMapping("/eval-quiz")
	public ResponseEntity<?>evalQuiz(@RequestBody List<Questions> questions)
	{
		System.out.println(questions);
		double marksGot=0;
		int  correctAnswer=0;
		 int  attempted=0;
		for(Questions q:questions){
			//System.out.println(q.getGivenAnswer());
			//single questions
			Questions question=this.questionService.get(q.getQuesid());
			if(question.getAnswer().equals(q.getGivenAnswer()))
			{//correct
				correctAnswer++;
				
				double marksSingle=Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size();
				marksGot +=marksSingle;
				
			}
			if(q.getGivenAnswer()!=null)//|| !q.getGivenAnswer().equals(""))
			{
				attempted++;
			}
		}
		System.out.println("correctAnswer:-"+correctAnswer);
		System.out.println("marksGot"+marksGot);
		Map<String, Object>map=Map.of("marksGot",marksGot,"correctAnswer",correctAnswer,"attempted",attempted);
		return ResponseEntity.ok(map);
	}
	
}
