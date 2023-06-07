package br.com.uanderson.aula06jpaheranca.model.repository;

import br.com.uanderson.aula06jpaheranca.model.entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository {
    /*
      CLASSE RESPONSÁVEL POR BUSCAR O USUÁRIO NA BASE DE DADOS,
      DURANTE O PROCESSO DE AUTENTICAÇÃO DO SPRING.
     */
    @PersistenceContext
    private EntityManager entityManager;

    public Usuario findUsuarioByLogin(String login){
        return entityManager.find(Usuario.class, login);
    }

}//class
