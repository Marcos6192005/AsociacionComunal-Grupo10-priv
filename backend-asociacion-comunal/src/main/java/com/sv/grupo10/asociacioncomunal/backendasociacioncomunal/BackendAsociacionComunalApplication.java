package com.sv.grupo10.asociacioncomunal.backendasociaoncomunal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class BackendAsociacionComunalApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendAsociacionComunalApplication.class, args);
    }

}
