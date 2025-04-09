package com.myapp.camel.config;


import org.apache.camel.CamelContext;
import org.apache.camel.dsl.xml.io.XmlRoutesBuilderLoader;
import org.apache.camel.impl.engine.DefaultResourceLoader;
import org.apache.camel.spi.Resource;
import org.apache.camel.spi.RoutesBuilderLoader;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

//@Configuration
public class XmlRoutesConfig {
	
	@Bean
	
     RoutesBuilderLoader xmlRoutesBuilderLoader() {
        return new XmlRoutesBuilderLoader();
    }
	
	@Bean
	@Order(1)
     ApplicationRunner loadXmlRoutes(CamelContext camelContext, RoutesBuilderLoader loader) {
        return args -> {
            var resourceLoader = new DefaultResourceLoader();
            resourceLoader.setCamelContext(camelContext);
            Resource resource = resourceLoader.resolveResource("classpath:xmlRoutesDsl.xml");

            camelContext.addRoutes(loader.loadRoutesBuilder(resource));
        };
	
//	@Bean
//     RoutesBuilder loadXmlDsl(CamelContext camelContext, RoutesBuilderLoader loader) throws Exception {
//        // Create a ResourceLoader that is aware of the CamelContext
//        ResourceLoader resourceLoader = new DefaultResourceLoader();
//        resourceLoader.setCamelContext(camelContext);
//
//        // Load the resource
//        Resource resource = resourceLoader.resolveResource("classpath:camel-context.xml");
//        
//        
//        return loader.loadRoutesBuilder(resource);
//    }

//    @Bean
//     RoutesBuilder xmlRoute(RoutesBuilderLoader loader) throws Exception {
//    	ResourceLoader resourceLoader = new DefaultResourceLoader();
//        Resource resource = resourceLoader.resolveResource("classpath:camel-context.xml");
//        return loader.loadRoutesBuilder(resource);
//    }
}
}
