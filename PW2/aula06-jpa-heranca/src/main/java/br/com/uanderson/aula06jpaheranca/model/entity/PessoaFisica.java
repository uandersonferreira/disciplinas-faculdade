package br.com.uanderson.aula06jpaheranca.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

import java.io.Serializable;
import java.util.List;

@Entity
public class PessoaFisica extends Pessoa implements Serializable {
    private String cpf;

    public PessoaFisica(Long id, String nome, List<Venda> vendaList, String cpf) {
        super(id, nome, vendaList);
        this.cpf = cpf;
    }

    public PessoaFisica() {

    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}//class
