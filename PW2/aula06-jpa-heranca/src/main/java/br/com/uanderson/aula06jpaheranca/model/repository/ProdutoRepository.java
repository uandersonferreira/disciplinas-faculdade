package br.com.uanderson.aula06jpaheranca.model.repository;

import br.com.uanderson.aula06jpaheranca.model.entity.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Log4j2
public class ProdutoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Produto produto){
        log.info("dentro save do ProdutoRepository");
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
