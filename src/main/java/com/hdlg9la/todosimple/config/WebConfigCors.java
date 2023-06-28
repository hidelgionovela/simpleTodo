package com.hdlg9la.todosimple.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


// Ficheiro que permite conexao com o frontEnd
// Para este caso foi liberado qualquer tipo de requisicao

@Configuration
@EnableWebMvc
public class WebConfigCors implements WebMvcConfigurer {
     
         public void addCorsMappings(CorsRegistry registry) {
                  registry.addMapping("/**");
         }
}
