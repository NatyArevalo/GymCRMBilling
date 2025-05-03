package com.gymcrm.trainerbilling.Configurations;


import com.gymcrm.trainerbilling.DTO.TrainerTrainingInformationDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class JmsConfiguration {

    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");

        Map<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put("com.gymcrm.trainerbilling.DTO.TrainingBillingDTO", TrainerTrainingInformationDTO.class);
        converter.setTypeIdMappings(typeIdMappings);

        return converter;
    }
}
