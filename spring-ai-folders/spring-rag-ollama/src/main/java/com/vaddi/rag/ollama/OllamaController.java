package com.vaddi.rag.ollama;

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

}
