package br.com.uanderson.aula06jpaheranca.model.repository;

import br.com.uanderson.aula06jpaheranca.model.entity.PessoaJuridica;
import br.com.uanderson.aula06jpaheranca.model.entity.PessoaJuridica;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PessoaJuridicaRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(PessoaJuridica pessoaJuridica){
        entityManager.persist(pessoaJuridica);
    }

    public PessoaJuridica findById(Long id){
        PessoaJuridica pessoaJuridica = entityManager.find(PessoaJuridica.class, id);
        return pessoaJuridica;
    }

    public List<PessoaJuridica> listAll(){
        final Query query = entityManager.createQuery("from PessoaJuridica");
        return query.getResultList();
    }

    public void removeById(Long id){
        PessoaJuridica pessoaJuridica = entityManager.find(PessoaJuridica.class, id);
        entityManager.remove(pessoaJuridica);
    }

    public void update(PessoaJuridica pessoaJuridica){
        entityManager.merge(pessoaJuridica);
    }

}//class
