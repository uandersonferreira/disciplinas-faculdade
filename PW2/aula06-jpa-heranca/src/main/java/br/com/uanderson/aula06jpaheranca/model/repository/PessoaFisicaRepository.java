package br.com.uanderson.aula06jpaheranca.model.repository;

import br.com.uanderson.aula06jpaheranca.model.entity.PessoaFisica;
import br.com.uanderson.aula06jpaheranca.model.entity.PessoaFisica;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Log4j2
public class PessoaFisicaRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(PessoaFisica pessoaFisica){
        entityManager.persist(pessoaFisica);
    }

    public PessoaFisica findById(Long id){
        PessoaFisica pessoaFisica = entityManager.find(PessoaFisica.class, id);
        return pessoaFisica;
    }

    public List<PessoaFisica> listAll(){
        final Query query = entityManager.createQuery("from PessoaFisica");
        return query.getResultList();
    }

    public void removeById(Long id){
        PessoaFisica pessoaFisica = entityManager.find(PessoaFisica.class, id);
        entityManager.remove(pessoaFisica);
    }

    public void update(PessoaFisica pessoaFisica){
        entityManager.merge(pessoaFisica);
    }
    
}//class
