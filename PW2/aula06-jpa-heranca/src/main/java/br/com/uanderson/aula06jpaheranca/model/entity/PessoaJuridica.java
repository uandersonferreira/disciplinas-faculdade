package br.com.uanderson.aula06jpaheranca.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

import java.io.Serializable;
import java.util.List;

@Entity
public class PessoaJuridica extends Pessoa implements Serializable {
    private String cnpj;

    public PessoaJuridica(Long id, String nome, List<Venda> vendaList, String cnpj) {
        super(id, nome, vendaList);
        this.cnpj = cnpj;
    }

    public PessoaJuridica() {}



    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
