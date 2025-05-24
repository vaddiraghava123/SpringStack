package com.vaddi.rag.ollama;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OllamaController {
	
	private final OllamaService pdfRagService;

    public OllamaController(OllamaService pdfRagService) {
        this.pdfRagService = pdfRagService;
    }

    @GetMapping("/ask")
    public String askFromPdf(@RequestParam String filepath,
                             @RequestParam String question) {
        try {
            return pdfRagService.askAboutPdf(filepath, question);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
    
    @GetMapping("/askmysqldb")
    public ResponseEntity<String> askQuestion_retrieveFromMysqlDatabase(@RequestParam String question) {
    	System.out.println("Question is ::" + question);
    	
    	String htmlResponse = pdfRagService.processQuestion(question);
		return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(htmlResponse);
    	
    }

}
