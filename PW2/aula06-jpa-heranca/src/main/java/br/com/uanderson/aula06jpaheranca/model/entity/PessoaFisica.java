package br.com.uanderson.aula06jpaheranca.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class PessoaFisica extends Pessoa  {
    private String nome;
    private String cpf;

    public PessoaFisica(Long id, String email, String telefone, List<Venda> vendaList, String nome, String cpf) {
        super(id, email, telefone, vendaList);
        this.nome = nome;
        this.cpf = cpf;
    }

    public PessoaFisica() {

    }

    public void dados(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}//class
