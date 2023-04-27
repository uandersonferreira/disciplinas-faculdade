package br.com.uanderson.aula06jpaheranca.model.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class PessoaFisica extends Pessoa  {

    private String cpf;

    public PessoaFisica(Long id, String nome, String email, String telefone, List<Venda> vendaList, Endereco endereco, String cpf) {
        super(id, nome, email, telefone, vendaList, endereco);
        this.cpf = cpf;
    }

    public PessoaFisica() {}

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}//class
