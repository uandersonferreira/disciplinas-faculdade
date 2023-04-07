package br.com.uanderson.aula06jpaheranca.model.repository;

import br.com.uanderson.aula06jpaheranca.model.entity.ItemVenda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemVendaRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(ItemVenda itemVenda){
        entityManager.persist(itemVenda);
    }
    public List<ItemVenda> listAll(){
        final Query query = entityManager.createQuery("from ItemVenda");
        return query.getResultList();
    }

    public ItemVenda findById(Long id){
        ItemVenda itemVenda = entityManager.find(ItemVenda.class, id);
        return itemVenda;
    }
    

    public void removeById(Long id){
        ItemVenda itemVenda = entityManager.find(ItemVenda.class, id);
        entityManager.remove(itemVenda);
    }

    public void update(ItemVenda itemVenda){
        entityManager.merge(itemVenda);
    }

}//class
