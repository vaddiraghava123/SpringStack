package com.vaddi.rag.ollama;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class OllamaService {

	private final ChatClient chatClient ;
	private final JdbcTemplate jdbcTemplate;

    public OllamaService(ChatClient chatClient, JdbcTemplate jdbcTemplate) {
        this.chatClient = chatClient;
        this.jdbcTemplate = jdbcTemplate;
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

	public String processQuestion(String question) {
		String prompt = """
	            You have access to a database with tables:
	            accounts
	            orders
	            employee
	            
	            User question: "%s"

	            Return only a valid SQL SELECT query based on the question.
	            DO NOT explain anything, only raw SQL. No markdown, no comments.
	        """.formatted(question);

		String response = chatClient.prompt(new Prompt(prompt))
                 .call()
                .content();
	        //ChatResponse response = ollamaChatModel.call(new Prompt(prompt));
//	        String sql = cleanSql(response.getResult().getOutput().getText());
		

	        System.out.println("Generated SQL:"+response);
	        if (!response.trim().toLowerCase().contains("select")) {
	            throw new IllegalArgumentException("Only SELECT queries are allowed.");
	        }

	        return executeQueryAndBuildHtml(response);
	    }

	    private String cleanSql(String raw) {
	        return raw.replaceAll("```sql", "")
	                  .replaceAll("```", "")
	                  .trim();
	    }

	    private String executeQueryAndBuildHtml(String sql) {
	        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

	        if (rows.isEmpty()) {
	            return "<p>No results found.</p>";
	        }

	        StringBuilder html = new StringBuilder("<table border='1'><tr>");
	        for (String column : rows.get(0).keySet()) {
	            html.append("<th style='background-color: #007BFF; color: white;'>").append(column).append("</th>");
	        }
	        html.append("</tr>");
	        for (Map<String, Object> row : rows) {
	            html.append("<tr style='background-color: yellow; color: black;'>");
	            for (Object value : row.values()) {
	                html.append("<td>").append(value != null ? value.toString() : "").append("</td>");
	            }
	            html.append("</tr>");
	        }
	        html.append("</table>");
	        return html.toString();
	    }
}
