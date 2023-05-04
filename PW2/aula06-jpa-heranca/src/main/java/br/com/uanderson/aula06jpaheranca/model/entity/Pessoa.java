package br.com.uanderson.aula06jpaheranca.model.entity;

import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Scope("session")
public  class Pessoa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nome;

    private String email;
    private String telefone;
    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
    private List<Venda> vendaList;
    //UMA VENDA PERTENCE APENAS A UMA PESSOA
    @OneToMany(cascade = CascadeType.ALL)
    private List<Endereco> enderecos;//UMA PESSOA TÊM MAIS DE UM ENDEREÇO  1 - N
    /*
     * ADD UM NOVO ENDEREÇO AO CLIENTE PARA ENTREGA
     * -
     * -
     */

    public Pessoa(Long id, String nome, String email, String telefone, List<Venda> vendaList, List<Endereco> enderecos) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.vendaList = vendaList;
        this.enderecos = enderecos;
    }

    public Pessoa() {
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Venda> getVendaList() {
        return vendaList;
    }

    public void setVendaList(List<Venda> vendaList) {
        this.vendaList = vendaList;
    }


    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", vendaList=" + vendaList +
                ", enderecos=" + enderecos +
                '}';
    }
}//class
