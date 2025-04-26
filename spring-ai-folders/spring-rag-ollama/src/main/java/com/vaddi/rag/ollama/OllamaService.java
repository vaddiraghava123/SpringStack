package com.vaddi.rag.ollama;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.stereotype.Service;

@Service
public class OllamaService {

	private final ChatClient chatClient ;

    public OllamaService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }
    
    /**
     * Ask about local pdf file
     * @param filePath
     * @param userQuestion
     * @return
     * @throws IOException
     */
    public String askAboutPdf(String filePath, String userQuestion) throws IOException {
        String pdfContent = readPdf(filePath);
        List<String> chunks = splitText(pdfContent, 1000); // Adjust chunk size if needed

        // Simple strategy: append all chunks together (for small PDFs)
        String combinedContext = String.join("\n", chunks);

        // Send to Ollama
        String response = chatClient.prompt()
                .user("You are an expert reader. Based on this document content:\n\n" + combinedContext +
                      "\n\nAnswer this question:\n" + userQuestion)
                .call()
                .content();
        
        return response;
    }

    /**
     * Read pdf
     * @param filePath
     * @return
     * @throws IOException
     */
    public String readPdf(String filePath) throws IOException {
        try (PDDocument document = PDDocument.load(new File(filePath))) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }
    }

    /**
     *  Split text into small chunks (for example, every 1000 characters)
     * @param text
     * @param chunkSize
     * @return
     */
    public List<String> splitText(String text, int chunkSize) {
        return IntStream.range(0, text.length() / chunkSize + 1)
                .mapToObj(i -> text.substring(i * chunkSize, Math.min((i + 1) * chunkSize, text.length())))
                .collect(Collectors.toList());
    }
}
