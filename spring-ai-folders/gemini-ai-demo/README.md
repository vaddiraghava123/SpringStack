### Google Gemini

Build GenAI Apps with Java and Spring Boot
API- VertexAI
Model - Gemini 2.0 Flash Experimental

VertexAI Gemini Chat

The Vertex AI Gemini API allows developers to build generative AI applications using the Gemini model. The Vertex AI Gemini API supports multimodal prompts as input and output text or code. A multimodal model is a model that is capable of processing information from multiple modalities, including images, videos, and text. For example, you can send the model a photo of a plate of cookies and ask it to give you a recipe for those cookies.

Gemini is a family of generative AI models developed by Google DeepMind that is designed for multimodal use cases. The Gemini API gives you access to the Gemini 1.0 Pro Vision and Gemini 1.0 Pro models. For specifications of the Vertex AI Gemini API models,


### Spring jars

	- Spring-boot-starter-web
	Spring ai
	-  vertex ai [ spring-ai-vertex-ai-gemini-spring-boot-starter]
	
	- spring-ai-core-1.0*.jar [ ChatClient]
	
### Setup

	>>  In google cloud
		[Step1]- 
			Create a project in google cloud [ https://console.cloud.google.com/ ]
							Name : SpringBootProject 
							Id   : springbootproject-439006	
			Billing [ By default 300 credits after add credit card]
			
			Products > API & Services -> Enabled API & Services ->  
					"Vertex AI API"  - enable it
		

	>> 	In google AI Studio
		Create API key .. First [Step1] - project creation and billing, services
		Login into Google AI Studio and create api key [ TEST@gmail.com ] 
			https://aistudio.google.com/
				   
			API key - API-KEY
			
	>  	For locations
		https://cloud.google.com/gemini/docs/locations/
		Iowa (us-central1)
		Oregon (us-west1)
		Las Vegas (us-west4)
		N. Virginia (us-east4)
		Singapore (asia-southeast1)
 
 >>  Coming to local machine
 
	 Ref spring-ai documentation, under AI models -> Chat models -> google Vertex AI 
	 
	 > Install gcloud  [ Download gcloud https://cloud.google.com/sdk/docs/install  ]
	 
	 commands in gcloud
	 
		> gcloud init
		> gcloud auth application-default  login
		> gcloud config set billing/quota_project springbootproject-439006  
			
			>>>>>>>>>>>>> 
			Credential file saved under [C:\Users\LENOVO\AppData\Roaming\gcloud\application_default_credentials.json
 
 ### Validating
 
 http://localhost:8080?message="Icc champions trophy winners list"

 ![image](https://github.com/user-attachments/assets/1f5e5862-a5c5-4fa0-931d-81048fd663ee)

 
 ### Trouble shoot
 
	https://cloud.google.com/docs/authentication/troubleshoot-adc
