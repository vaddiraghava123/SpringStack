🚀 OpenAPI to ReadMe.io Integration Guide

This guide explains how to convert an OpenAPI specification into ReadMe.io documentation, expose it through Spring Boot, and make it publicly accessible using Ngrok.

📌 Prerequisites
1️⃣ ReadMe.io Account

Login to ReadMe Dashboard
🔗 https://dash.readme.com/login

You will need:

Project

API Key

2️⃣ Install Ngrok

Install from Microsoft Store or download from:
🔗 https://ngrok.com

Login and configure:

ngrok config add-authtoken <NGROK_AUTH_TOKEN>

3️⃣ Install Required Tools
npm install -g ngrok
npm install -g rdme@10
npm install -g jq


Install ReadMe OpenAPI plugin:

rdme plugins:install openapi

4️⃣ Install OpenAPI Generator
npm install -g @openapitools/openapi-generator-cli

📦 Spring Boot Setup

Your Spring Boot REST project must include:

✅ Global CORS Configuration

✅ Security Configuration

✅ OpenAPI Specification (YAML)

✅ Generated REST Controllers from OpenAPI

✅ Swagger UI enabled

Generate Spring Boot REST Stubs from YAML
openapi-generator-cli generate \
 -i customer-api.yaml \
 -g spring \
 -o customer-api-service

Run Spring Boot Application
mvn spring-boot:run


Open Swagger UI:

http://localhost:8080/swagger-ui/index.html

✅ Validation
mvn clean generate-sources verify

📤 Upload OpenAPI Spec to ReadMe

Upload your OpenAPI YAML file:

rdme openapi upload src/main/resources/customer-api.yaml \
 --key <README_API_KEY> \
 --slug customer-api


When prompted:

This will overwrite the existing API definition... Are you sure? → yes


On success:

🚀 Your API definition was successfully updated in ReadMe!


💡 Change the --slug value (e.g., customer-api-v2) to upload as a new API.

🌍 Expose Spring Boot Using Ngrok
ngrok http 8080 --host-header=localhost


You will get a public URL like:

https://6682-2409-40f0-4b-9d3c-557a-2209-8a1e-cbcc.ngrok-free.app

⚠️ Mandatory Ngrok Header for Browser Issues

Add this to your OpenAPI YAML:

headers:
  ngrok-skip-browser-warning:
    schema:
      type: string
    example: "true"


And in all API calls:

ngrok-skip-browser-warning: true

📡 Sample API Call
curl --request GET \
 --url https://6682-2409-40f0-4b-9d3c-557a-2209-8a1e-cbcc.ngrok-free.app/api/v1/customers/254 \
 --header "User-Agent: ReadMe-API-Explorer" \
 --header "accept: application/json" \
 --header "ngrok-skip-browser-warning: true"


 ### Validation Screenshots
<img width="1288" height="406" alt="image" src="https://github.com/user-attachments/assets/d516be2b-caec-4632-bdb6-30b6f831ba19" />

<img width="972" height="91" alt="image" src="https://github.com/user-attachments/assets/217e23d7-3c6e-4676-bb63-6ba3b87e085e" />

<img width="263" height="21" alt="image" src="https://github.com/user-attachments/assets/0456da8f-fa99-4ed0-9bda-ba2566501355" />

<img width="980" height="234" alt="image" src="https://github.com/user-attachments/assets/acbccc54-b621-4475-b86f-b2c2bd82e082" />

<img width="1571" height="425" alt="image" src="https://github.com/user-attachments/assets/d156c5fd-a084-4271-8f54-a6e150a61655" />

<img width="1564" height="779" alt="image" src="https://github.com/user-attachments/assets/8ae58a5e-a704-4bc6-94f4-89bf5cb341f2" />

<img width="1557" height="685" alt="image" src="https://github.com/user-attachments/assets/3f542810-d5f5-4a6b-96bb-f9b53285beaf" />
