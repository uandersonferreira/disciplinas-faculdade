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
    private String nome;

    //UMA PESSOA PODE TER VÁRIAS VENDAS
    @OneToMany(mappedBy = "id")
    private List<Venda> vendaList;
    //UMA VENDA PERTENCE APENAS A UMA PESSOA


    public Pessoa(Long id, String nome, List<Venda> vendaList) {
        this.id = id;
        this.nome = nome;
        this.vendaList = vendaList;
    }

    public Pessoa() {
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
}//class
