package br.com.uanderson.aula06jpaheranca.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConfiguracaoSpringMvc implements WebMvcConfigurer {
    /**
     * Com a chamada a registry.addViewController(), estamos registrando um controller
     * definido pelo próprio Spring, para atender a requisições direcionadas à URL / e /home. E com a chamada
     * a setViewName(), sempre que a aplicação receber uma requisição para um desses endereços, a view home,
     * criada na última aula, será exibida.
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/home").setViewName("home");
    }
}//class
