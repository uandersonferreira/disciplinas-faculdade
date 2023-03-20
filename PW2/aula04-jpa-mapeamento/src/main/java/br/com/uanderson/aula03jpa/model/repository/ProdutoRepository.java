package br.com.uanderson.aula03jpa.model.repository;

import br.com.uanderson.aula03jpa.model.entity.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProdutoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Produto produto){
        entityManager.persist(produto);
    }

    public Produto findById(Long id){
        Produto produto = entityManager.find(Produto.class, id);
        return produto;
    }

    public List<Produto> listAll(){
        final Query query = entityManager.createQuery("from Produto");
        return query.getResultList();
    }

    public void removeById(Long id){
        Produto produto = entityManager.find(Produto.class, id);
        entityManager.remove(produto);
    }

    public void update(Produto produto){
        entityManager.merge(produto);
    }





}//class
