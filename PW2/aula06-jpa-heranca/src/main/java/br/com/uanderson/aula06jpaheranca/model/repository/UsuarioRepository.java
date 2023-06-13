package br.com.uanderson.aula06jpaheranca.model.repository;

import br.com.uanderson.aula06jpaheranca.model.entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    /*
      CLASSE RESPONSÁVEL POR BUSCAR O USUÁRIO NA BASE DE DADOS,
      DURANTE O PROCESSO DE AUTENTICAÇÃO DO SPRING.

      - Troquei para JpaRepository, pois o método find() do EntityManager
      aceita somente uma instância da class + a primaryKey da classe passada
      e como o id é Long, mas nós precisamos encontrar o usuário pelo login
      ficava dando erro de types distintos.
     */

    public Usuario findUsuarioByLogin(String login);

}//class
