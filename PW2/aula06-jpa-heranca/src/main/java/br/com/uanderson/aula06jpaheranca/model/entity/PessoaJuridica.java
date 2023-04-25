package br.com.uanderson.aula06jpaheranca.model.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class PessoaJuridica extends Pessoa {
    private String razaoSocial;
    private String cnpj;

    public PessoaJuridica(Long id, String nome, String email, String telefone, List<Venda> vendaList, String razaoSocial, String cnpj) {
        super(id, nome, email, telefone, vendaList);
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

    @Override
    public String toString() {
        return super.toString()+" PessoaJuridica{" +
                "razaoSocial='" + razaoSocial + '\'' +
                ", cnpj='" + cnpj + '\'' +
                '}';
    }
}//class
