package com.example.danielmeuprojeto.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    //ModelMapper, os campos que são iguais são retornados
    public ModelMapper mapper(){
        return new ModelMapper();
    }
}
