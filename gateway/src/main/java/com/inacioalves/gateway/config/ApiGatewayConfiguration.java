package com.inacioalves.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
	
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/get")
						.filters(f -> f
								.addRequestHeader("MyHeader", "MyURI")
								.addRequestParameter("Param", "MyValue"))
						.uri("http://httpbin.org:80"))
				
				.route(p -> p.path("/crud/produto/**")
						.uri("lb://crud"))
				
				.route(p -> p.path("/pagamento/venda/**")
						.uri("lb://pagamento"))
				
				.route(p -> p.path("/auth/login/**")//URL
						.uri("lb://auth"))//name application.properties
				.route(p -> p.path("/auth/login/testeSecurity/**")//URL
						.uri("lb://auth"))//name application.properties
				
				.build();
		
	}
}