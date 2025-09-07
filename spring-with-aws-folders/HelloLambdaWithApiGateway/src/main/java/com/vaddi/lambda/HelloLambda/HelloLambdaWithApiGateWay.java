package com.vaddi.lambda.HelloLambda;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HelloLambdaWithApiGateWay implements RequestHandler<APIGatewayProxyRequestEvent, ApiResponse> {

	@Override
	public ApiResponse handleRequest(APIGatewayProxyRequestEvent input, Context context) {
		return buildApiResponse(context);
	}

	private ApiResponse buildApiResponse(Context context)  {
		
		Map<String,String> headers = new HashMap<>();
		headers.put("X-amazon-author","Raghava");
		headers.put("X-amazon-apiversion","1.0");
		
		String body = null;
		try {
			body = new ObjectMapper().writeValueAsString(getEmployeeList());
		} catch (JsonProcessingException e) {
			context.getLogger().log("Error :: "+ e.getMessage());
		}
		
		ApiResponse apiResponse = new ApiResponse(200, body, headers);
		context.getLogger().log("Response :: " + apiResponse);
		return apiResponse;
		
	}
	
	private List<Employee> getEmployeeList() {
		return  Arrays.asList(new Employee(1,"Raghava","cse",233.99),
				new Employee(2,"Raghu","cse",2333.99)
				,new Employee(3,"Test","IT",5333.99)
				,new Employee(4,"OO","IT",2233.99)
				,new Employee(5,"Suj","Civil",1233.99)
				,new Employee(6,"Na","Civil",6233.99));
	}
}
