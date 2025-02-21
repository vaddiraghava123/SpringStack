package com.vaddi.elasticsearch.spring_elastic_search;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;

@Configuration
@ConfigurationProperties
public class ElasticSearchConfig {
	
	@Value("${spring.elasticsearch.username}")
	private String username;
	
	@Value("${spring.elasticsearch.password}")
	private String password;
	
	@Bean
	public RestClient restClient() {
		
		RestClientBuilder builder = RestClient.builder(new org.apache.http.HttpHost("localhost", 9200, "http"))
                .setHttpClientConfigCallback(httpClientBuilder -> {
                    BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                    credentialsProvider.setCredentials(AuthScope.ANY,
                            new UsernamePasswordCredentials(username, password));

                    return httpClientBuilder
                            .setDefaultCredentialsProvider(credentialsProvider)
                            .setDefaultIOReactorConfig(IOReactorConfig.custom()
                                    .setIoThreadCount(2)
                                    .build());
                });
        return builder.build();
	}

    @Bean
    public ElasticsearchClient elasticsearchClient() {
        return new ElasticsearchClient(new RestClientTransport(restClient(), new JacksonJsonpMapper()));
    }
    
}
