package br.com.uanderson.aula06jpaheranca.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.List;

@Entity
public class PessoaJuridica extends Pessoa {
    @NotBlank(message = "Preencha o campo Razão Social")
    private String razaoSocial;
    @NotBlank(message = "Preencha o campo CNPJ")
//    @CNPJ(message = "Informe um CNPJ válido. ex: 00.000.000/0000-00")
    private String cnpj;

    public PessoaJuridica(String nome, String email, String telefone, List<Venda> vendaList, Endereco endereco, Usuario usuario, String razaoSocial, String cnpj) {
        super(nome, email, telefone, vendaList, endereco, usuario);
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
