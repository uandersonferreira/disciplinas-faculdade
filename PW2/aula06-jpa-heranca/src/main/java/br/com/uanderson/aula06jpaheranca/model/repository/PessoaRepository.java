package br.com.uanderson.aula06jpaheranca.model.repository;

import br.com.uanderson.aula06jpaheranca.model.entity.Pessoa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PessoaRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Pessoa pessoa){
        entityManager.persist(pessoa);
    }

    public Pessoa findById(Long id){
        Pessoa pessoa = entityManager.find(Pessoa.class, id);
        return pessoa;
    }
    public Pessoa findPessoaByEmail(String email){
        Query query = entityManager.createQuery("select p from Pessoa p where p.email = :email");
        query.setParameter("email",email);
        return (Pessoa) query.getSingleResult();
    }

    public List<Pessoa> listAll(){
        final Query query = entityManager.createQuery("from Pessoa");
        return query.getResultList();
    }

    public void removeById(Long id){
        Pessoa pessoa = entityManager.find(Pessoa.class, id);
        entityManager.remove(pessoa);
    }

    public void update(Pessoa pessoa){
        entityManager.merge(pessoa);
    }
    
}//class
