package com.vaddi.togglz.spring_feature_togglz;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LaunchDarklyController {
	
	private final LaunchDarklyService featureToggleService;
	
	private final String FEATURE_FLAG_KEY = "spring-feature";

    public LaunchDarklyController(LaunchDarklyService featureToggleService) {
        this.featureToggleService = featureToggleService;
    }

    @GetMapping("/feature-check")
    public String checkFeature(@RequestParam String userId) {
        boolean isEnabled = featureToggleService.isFeatureEnabled(FEATURE_FLAG_KEY, userId);
        return isEnabled ? "Feature is enabled!" : "Feature is disabled!";
    }

}
