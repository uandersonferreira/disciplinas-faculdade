package br.com.uanderson.aula06jpaheranca.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;
import java.util.Objects;

@Entity
public class PessoaFisica extends Pessoa  {

    @NotBlank(message = "Preencha o campo CPF")
//    @CPF(message = "Informe um CPF válido. ex: 000.000.000-00") Optei por utilizar uma mascara do Jquery
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
