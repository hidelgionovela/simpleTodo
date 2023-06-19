package com.hdlg9la.todosimple.doc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Documentation")
                        .version("1.0.0")
                        .description("API for performing various operations.")
                        .contact(new Contact()
                                .name("Hidelgio Novela")
                                .email("hidelgionovela@example.com")
                                .url("https://hidelgio9la.com")));
        // .license(new License()
        // .name("Apache 2.0")
        // .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }

}
