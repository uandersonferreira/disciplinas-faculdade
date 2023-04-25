package br.com.uanderson.aula06jpaheranca.model.repository;

import br.com.uanderson.aula06jpaheranca.model.entity.Cidade;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CidadeRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Cidade cidade){
        entityManager.persist(cidade);
    }

    public Cidade findById(Long id){
        Cidade cidade = entityManager.find(Cidade.class, id);
        return cidade;
    }

    public List<Cidade> listAll(){
        final Query query = entityManager.createQuery("from Cidade");
        return query.getResultList();
    }

    public void removeById(Long id){
        Cidade cidade = entityManager.find(Cidade.class, id);
        entityManager.remove(cidade);
    }

    public void update(Cidade cidade){
        entityManager.merge(cidade);
    }

}//class
