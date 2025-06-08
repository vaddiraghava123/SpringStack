# Spring AI using ollama and mysql database

# Ollama model 
    -  llama3.2:1b
    
# Important properties of ollama and mysql database

-  ollama

            spring.ai.ollama.base-url=http://localhost:11434
            spring.ai.ollama.chat.options.model=llama3.2:1b

            spring.ai.ollama.embedding.enabled=true
            spring.ai.ollama.embedding.options.model=llama3.2:1b
  
-  mysql database

            spring.datasource.url=jdbc:mysql://localhost:3306/****?useSSL=false&allowPublicKeyRetrieval=true
            spring.datasource.username=root
            spring.datasource.password=*****

# Call Ollama

  -      String prompt = "PROVIDE QUERY and USER QUESTION : "%s" ".formatted(user_question);

  -      ChatResponse response = ollamaChatModel.call(new Prompt(prompt));
         String sql = cleanSql(response.getResult().getOutput().getText());

  -      and then execute and get query results

         List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
     
   # Validation

   http://localhost:8080/chat/ask?question="get mysqldb.course_ai table records only"  
       - [ here **mysqldb.** required for specific table records]

![image](https://github.com/user-attachments/assets/fec6d1b9-41a7-476b-9cf0-3261c637f794)


   
