package br.com.uanderson.aula06jpaheranca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Aula06JpaHerancaApplication {

    public static void main(String[] args) {
        SpringApplication.run(Aula06JpaHerancaApplication.class, args);
    }

}
/*
 * ADD UM NOVO ENDEREÇO AO CLIENTE PARA ENTREGA
 * - UMA PESSOA TÊM MAIS DE UM ENDEREÇO  1 - N
 * - UM ENDEREÇO PARA MUITAS PESSOAS 1 - N
 */