package com.projetoibm.config;

import com.projetoibm.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class LocalConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instancia() throws ParseException {
        this.dbService.instanciaBancoDeDados();
        return true;
    }
}
