package Kruchkov.Task4.Config;

import Kruchkov.Task4.*;
import Kruchkov.Task4.Checkers.AppTypeChecker;
import Kruchkov.Task4.Checkers.DateChecker;
import Kruchkov.Task4.Checkers.FioChecker;
import Kruchkov.Task4.FileReader;


import Kruchkov.Task4.Models.Login;
import Kruchkov.Task4.Models.User;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties

public class Config {

    @Bean
    public    BeanPostProcessorStart  postprocessor() {
        return new BeanPostProcessorStart();
    }


    @Bean
    public ArrayList<FileReader> fileReaderList() {
        return new ArrayList<>();
    }

    @Bean   (initMethod = "init")
    public FileReader fileReader() {
        return new FileReader();
    }

    @Bean @Order(1)
    public DateChecker dateChecker() {
        return new DateChecker();
    }

    @Bean @Order(2)
    public AppTypeChecker appTypeChecker() {
        return new AppTypeChecker();
    }

    @Bean  @Order(3)
    public  FioChecker fioChecker() {
        return new FioChecker();
    }

    @Bean
    public DataProcess dataProcess(ArrayList<FileReader> fileReaderList) {
        return new DataProcess(fileReaderList);
    }

    @Bean
    public User user() {
         return new User();
    }


    @Bean
    public Login login() {
        return new Login();
    }

    @Bean
    public DbInsert dbInsert() {
        return new DbInsert();
    }

}
