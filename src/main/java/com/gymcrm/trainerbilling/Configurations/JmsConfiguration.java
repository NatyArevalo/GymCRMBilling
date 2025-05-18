package com.gymcrm.trainerbilling.Configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.ConnectionFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.jms.support.destination.DestinationResolver;
import org.springframework.jms.support.destination.DynamicDestinationResolver;

import java.util.Map;


@Configuration
@EnableJms
@ConditionalOnProperty(name = "activemq.enabled", havingValue = "true", matchIfMissing = false)
public class JmsConfiguration {
    @Bean
    public DestinationResolver destinationResolver() {
        return new DynamicDestinationResolver();
    }
    @Bean
    public MessageConverter jacksonJmsMessageConverter(ObjectMapper objectMapper) {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdMappings(Map.of(
                "TrainingBillingDTO", com.gymcrm.trainerbilling.DTO.TrainingBillingDTO.class
        ));
        converter.setTypeIdPropertyName("_typeId");
        converter.setObjectMapper(objectMapper);
        return converter;
    }
    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(
            ConnectionFactory connectionFactory,
            DestinationResolver destinationResolver,
            MessageConverter messageConverter
    ) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setDestinationResolver(destinationResolver);
        factory.setMessageConverter(messageConverter);
        factory.setSessionTransacted(true);
        factory.setConcurrency("3-10");
        return factory;
    }

}
