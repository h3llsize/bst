package com.bstbackend.bstbackend.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PersistenceProperties {

    @Value("${persistence.path}")
    private String persistencePath;


    @Bean
    private EntityManager getPersistenceProp() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(persistencePath));
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("appName", properties);

        return factory.createEntityManager();
    }

}
