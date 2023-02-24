package com.umasuraj.payroll.api;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author umasuraj
 */
@SpringBootApplication
public class PayrollApiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PayrollApiApplication.class, args);

    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {

    }

}
