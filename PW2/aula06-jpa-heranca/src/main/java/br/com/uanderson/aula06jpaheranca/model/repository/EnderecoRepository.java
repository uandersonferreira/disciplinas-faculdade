package br.com.uanderson.aula06jpaheranca.model.repository;

import br.com.uanderson.aula06jpaheranca.model.entity.Endereco;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EnderecoRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Endereco endereco){
        entityManager.persist(endereco);
    }

    public Endereco findById(Long id){
        Endereco endereco = entityManager.find(Endereco.class, id);
        return endereco;
    }

    public List<Endereco> listAll(){
        final Query query = entityManager.createQuery("from Endereco");
        return query.getResultList();
    }

    public void removeById(Long id){
        Endereco endereco = entityManager.find(Endereco.class, id);
        entityManager.remove(endereco);
    }

    public void update(Endereco endereco){
        entityManager.merge(endereco);
    }

}//class
