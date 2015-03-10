package fr.sii.survival;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
	public static final String TOPIC_PREFIX = "/topic/game";
	public static final String CLIENT_SUBSCRIBE_PREFIX = TOPIC_PREFIX;
	public static final String SERVER_PUBLISH_PREFIX = TOPIC_PREFIX;
	public static final String SERVER_MAPPING_PREFIX = "";
	public static final String CLIENT_SEND_PREFIX = TOPIC_PREFIX;
	public static final String ENDPOINT_PREFIX = "/game";

	@Autowired
	ObjectMapper objectMapper;

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker(TOPIC_PREFIX);
		config.setApplicationDestinationPrefixes(CLIENT_SEND_PREFIX);
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint(ENDPOINT_PREFIX).withSockJS()
		 .setClientLibraryUrl("http://10.6.192.65:8000/sockjs.min.js");
	}

	/*
	 * This should be done automatically by Spring Boot but there is a currently
	 * a bug on it: https://github.com/spring-projects/spring-boot/issues/2445
	 */
	@Override
	public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
		DefaultContentTypeResolver resolver = new DefaultContentTypeResolver();
		resolver.setDefaultMimeType(MimeTypeUtils.APPLICATION_JSON);
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setObjectMapper(objectMapper);
		converter.setContentTypeResolver(resolver);
		messageConverters.add(converter);
		return false;
	}

}