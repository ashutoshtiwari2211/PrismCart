package com.marketplacex.auth;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.TimeZone;

@SpringBootApplication
public class AuthServiceApplication {

    @PostConstruct
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        System.out.println("JVM Default Timezone: " + TimeZone.getDefault().getID());
    }

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }

//    @Bean
//    CommandLineRunner testDatabase(DataSource dataSource) {
//        return args -> {
//            try (Connection conn = dataSource.getConnection()) {
//                System.out.println("✅ DB Connected Successfully: " + conn.getMetaData().getURL());
//            } catch (Exception e) {
//                System.err.println("❌ DB Connection Failed: " + e.getMessage());
//            }
//        };
//    }


}

