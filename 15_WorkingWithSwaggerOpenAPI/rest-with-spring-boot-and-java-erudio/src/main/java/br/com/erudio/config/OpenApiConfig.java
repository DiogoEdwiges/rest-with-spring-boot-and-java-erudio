package br.com.erudio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
	
	// BEAN: objeto que é instanciado, montado e gerenciado pelo
	// Spring IOC Container (busca as informações em XML, annotations ou código Java
	// sobre como beans devem ser montados e como se relacionam entre si {injeção de dependência}).
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("RESTful API with Java 18 and Spring Boot")
						.version("v1")
						.description("Some description about the API")
						.termsOfService("https://pub.erudio.com.br/meus-cursos")
						.license(new License()
								.name("Apache 2.0")
								.url("https://pub.erudio.com.br/meus-cursos")));
	}
}
