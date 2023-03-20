package br.com.uanderson.aula03jpa.model.repository;

import br.com.uanderson.aula03jpa.model.entity.ItemVenda;
import br.com.uanderson.aula03jpa.model.entity.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemVendaRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<ItemVenda> listAll(){
        final Query query = entityManager.createQuery("from ItemVenda");
        return query.getResultList();
    }


}//class
