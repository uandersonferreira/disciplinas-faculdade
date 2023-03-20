package br.com.uanderson.aula03jpa.model.repository;

import br.com.uanderson.aula03jpa.model.entity.Produto;
import br.com.uanderson.aula03jpa.model.entity.Venda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VendaRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Venda> listAll(){
        final Query query = entityManager.createQuery("from Venda");
        return query.getResultList();
        /*
        method que irá listar todas as vendas cadastradas na base de dados.
         */
    }


}//class
