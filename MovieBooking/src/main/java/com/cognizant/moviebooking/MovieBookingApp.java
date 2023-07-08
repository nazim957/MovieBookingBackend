package com.cognizant.moviebooking;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class MovieBookingApp {

//	@Bean
//	public FilterRegistrationBean jwtFilter()
//	{
//		FilterRegistrationBean fb = new FilterRegistrationBean();
//
//		fb.setFilter(new JWTFilter());
//
//		fb.addUrlPatterns("/api/v1/*");
//		return fb;
//	}



	@Configuration

	public class OpenApiConfig {

		@Bean
		public OpenAPI customConfig() {
			final String securitySchemeName = "bearerAuth";

			return new OpenAPI()
					.addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
					.components(new Components().addSecuritySchemes(securitySchemeName,
							new SecurityScheme()
									.name(securitySchemeName)
									.type(SecurityScheme.Type.HTTP)
									.scheme("bearer ")
									.bearerFormat("JWT")));
		}
	}



	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder){
		return builder.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(MovieBookingApp.class, args);
	}

}
