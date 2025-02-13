/*
 * Copyright 2024-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.ai.autoconfigure.stabilityai;

import org.springframework.ai.autoconfigure.NativeHints;
import org.springframework.ai.stabilityai.StabilityAiImageClient;
import org.springframework.ai.stabilityai.api.StabilityAiApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportRuntimeHints;

/**
 * @author Mark Pollack
 * @since 0.8.0
 */
@AutoConfiguration
@ConditionalOnClass(StabilityAiApi.class)
@EnableConfigurationProperties({ StabilityAiProperties.class })
@ImportRuntimeHints(NativeHints.class)
public class StabilityAiImageAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public StabilityAiApi stabilityAiApi(StabilityAiProperties stabilityAiProperties) {
		return new StabilityAiApi(stabilityAiProperties.getApiKey(), stabilityAiProperties.getBaseUrl(),
				stabilityAiProperties.getOptions().getModel());
	}

	@Bean
	@ConditionalOnMissingBean
	public StabilityAiImageClient stabilityAiImageClient(StabilityAiApi stabilityAiApi,
			StabilityAiProperties stabilityAiProperties) {
		return new StabilityAiImageClient(stabilityAiApi, stabilityAiProperties.getOptions());
	}

}
