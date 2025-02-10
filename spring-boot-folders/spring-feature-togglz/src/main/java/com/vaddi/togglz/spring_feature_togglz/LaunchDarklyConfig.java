package com.vaddi.togglz.spring_feature_togglz;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.launchdarkly.sdk.server.LDClient;
import com.launchdarkly.sdk.server.LDConfig;

@Configuration
  public class LaunchDarklyConfig {
	  private static final String SDK_KEY = "sdk-060f8ef7-e8a2-49ed-aa07-f7b88d72e82b"; // Replace with your actual key

	    @Bean
	    public LDClient ldClient() {
	        return new LDClient(SDK_KEY, new LDConfig.Builder().build());
	    }
  }


