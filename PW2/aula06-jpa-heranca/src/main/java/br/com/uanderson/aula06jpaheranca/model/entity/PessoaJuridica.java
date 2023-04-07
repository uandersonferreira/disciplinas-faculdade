package br.com.uanderson.aula06jpaheranca.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

import java.io.Serializable;
import java.util.List;

@Entity
public class PessoaJuridica extends Pessoa {
    private String razaoSocial;
    private String cnpj;

    public PessoaJuridica(Long id, String email, String telefone, List<Venda> vendaList, String razaoSocial, String cnpj) {
        super(id, email, telefone, vendaList);
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
    }

    public PessoaJuridica() {}

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}//class
