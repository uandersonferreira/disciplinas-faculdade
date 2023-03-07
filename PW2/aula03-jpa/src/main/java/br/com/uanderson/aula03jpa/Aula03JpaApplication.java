package br.com.uanderson.aula03jpa;

import jakarta.persistence.EntityManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Aula03JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(Aula03JpaApplication.class, args);
    }

}
/*
O @PersistenceContext é usado especificamente quando precisamos injetar um EntityManager.
*/