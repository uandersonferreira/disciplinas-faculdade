package br.com.uanderson.aula06jpaheranca.model.repository;

import br.com.uanderson.aula06jpaheranca.model.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
         List<Venda> findVendasByPessoaId(Long idPessoa);

}//class
