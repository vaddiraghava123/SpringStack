package com.vaddi.togglz.spring_feature_togglz;

import org.springframework.stereotype.Service;

import com.launchdarkly.sdk.LDContext;
import com.launchdarkly.sdk.server.LDClient;

@Service
public class LaunchDarklyService {
	 private final LDClient ldClient;

	    public LaunchDarklyService(LDClient ldClient) {
	        this.ldClient = ldClient;
	    }

	    public boolean isFeatureEnabled(String featureFlag, String userKey) {
	        final LDContext context = LDContext.builder("example-user-key")
	                .name("Sandy")
	                .build();
	        return ldClient.boolVariation(featureFlag, context, false);
	    }
}
