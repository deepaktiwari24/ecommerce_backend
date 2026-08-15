package com.deepak.ecommerce_backend;

import com.deepak.ecommerce_backend.entity.User;
import com.deepak.ecommerce_backend.service.Impl.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EcommerceBackendApplication {

	public static void main(String[] args) {
                 SpringApplication.run(EcommerceBackendApplication.class, args);
    }

}
