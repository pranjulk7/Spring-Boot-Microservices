package com.eazybytes.account;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(info = @Info(title = "Account Service Microservice",
		description = "Handles Accounts CRUD operations",
		version = "v1",
		contact = @Contact (name = "Pranjul",
		email = "pranjulk7@gmail.com",
		url = "kpro.com"),
		license = @License(name = "apache 2.0",
		url = "licesnseurl.com" +
				"")
		),
		externalDocs = @ExternalDocumentation(
				description = "For more details check this url",
				url = "someurl.com"
		)

)
public class AccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}

}
