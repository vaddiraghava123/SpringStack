package com.vaddi.aws.springbootUploadIntoAWS;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class SpringbootUploadIntoAwsApplicationTests {
	
	@Autowired
	MockMvc mockMvc;

	@Test
	void contextLoads() throws Exception {
		
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/hello")
				.accept(MediaType.TEXT_PLAIN)).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Welcome Spring Boot application UAT Profile"));
             
	}

}
