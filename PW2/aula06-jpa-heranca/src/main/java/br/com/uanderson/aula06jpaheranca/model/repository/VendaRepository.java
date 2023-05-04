package br.com.uanderson.aula06jpaheranca.model.repository;

import br.com.uanderson.aula06jpaheranca.model.entity.Venda;
import br.com.uanderson.aula06jpaheranca.model.entity.Venda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class VendaRepository {
    @PersistenceContext
    private EntityManager entityManager;


    public void save(Venda venda){
        entityManager.persist(venda);
    }
    public List<Venda> listAll(){
        final Query query = entityManager.createQuery("from Venda");
        return query.getResultList();
        /*
        method que irá listar todas as vendas cadastradas na base de dados.
         */
    }

    public Venda findById(Long id){
        Venda venda = entityManager.find(Venda.class, id);
        return venda;
    }

    public void removeById(Long id){
        Venda venda = entityManager.find(Venda.class, id);
        entityManager.remove(venda);
    }

    public void update(Venda venda){
        entityManager.merge(venda);
    }




}//class
