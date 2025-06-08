package com.vaddi.rag.mysql;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.vaddi.rag.mysql.StudentCourseService;

@RestController
@RequestMapping("/chat")
public class StudentCourseController {

	private final StudentCourseService studentCourseResource;

	public StudentCourseController(StudentCourseService studentCourseResource) {
		this.studentCourseResource = studentCourseResource;
	}

	@GetMapping("/ask")
	public ResponseEntity<String> askQuestion(@RequestParam String question) {
		System.out.println("Question:"+question);
		String htmlResponse = studentCourseResource.processQuestion(question);
		return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(htmlResponse);
	}
}
