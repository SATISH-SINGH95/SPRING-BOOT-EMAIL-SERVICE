package com.email.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setSkipNullEnabled(true);

        return modelMapper;
    }

    @Bean
    ObjectMapper objectMapper() {
        // Create ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        // Register JavaTimeModule
        JavaTimeModule javaTimeModule = new JavaTimeModule();

        // Add custom serializers/deserializers for LocalDate and LocalDateTime
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(timeFormatter));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(timeFormatter));
        // Register JavaTimeModule with ObjectMapper
        objectMapper.registerModule(javaTimeModule);

        return objectMapper;
    }
}
