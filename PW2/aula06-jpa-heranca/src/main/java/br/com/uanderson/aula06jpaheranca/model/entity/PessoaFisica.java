package br.com.uanderson.aula06jpaheranca.model.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class PessoaFisica extends Pessoa  {

    private String cpf;

    public PessoaFisica(Long id, String email, String telefone, List<Venda> vendaList, String cpf) {
        super(id, email, telefone, vendaList);
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
