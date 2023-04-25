package br.com.uanderson.aula06jpaheranca.model.repository;

import br.com.uanderson.aula06jpaheranca.model.entity.Estado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EstadoRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Estado estado){
        entityManager.persist(estado);
    }

    public Estado findById(Long id){
        Estado estado = entityManager.find(Estado.class, id);
        return estado;
    }

    public List<Estado> listAll(){
        final Query query = entityManager.createQuery("from Estado");
        return query.getResultList();
    }

    public void removeById(Long id){
        Estado estado = entityManager.find(Estado.class, id);
        entityManager.remove(estado);
    }

    public void update(Estado estado){
        entityManager.merge(estado);
    }

}//class
