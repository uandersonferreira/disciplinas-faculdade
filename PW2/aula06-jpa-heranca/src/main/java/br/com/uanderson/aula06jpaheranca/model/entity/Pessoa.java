package br.com.uanderson.aula06jpaheranca.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String email;
    private String telefone;

    //UMA PESSOA PODE TER VÁRIAS VENDAS
    @OneToMany(mappedBy = "id")
    private List<Venda> vendaList;
    //UMA VENDA PERTENCE APENAS A UMA PESSOA


    public Pessoa(Long id, String email, String telefone, List<Venda> vendaList) {
        this.id = id;
        this.email = email;
        this.telefone = telefone;
        this.vendaList = vendaList;
    }

    public Pessoa() {
    }

    public String dados(Pessoa pessoa){
        if (pessoa instanceof PessoaFisica){
            return ((PessoaFisica) pessoa).getNome();
        } else if (pessoa instanceof PessoaJuridica) {
            return ((PessoaJuridica) pessoa).getRazaoSocial();
        }
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}//class
