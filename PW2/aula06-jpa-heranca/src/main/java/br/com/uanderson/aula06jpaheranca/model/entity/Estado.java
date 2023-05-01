package br.com.uanderson.aula06jpaheranca.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Estado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String nome;
    private String sigla;
    @OneToMany(mappedBy = "estado")
    private List<Cidade> cidades;

    public Estado(Long id, String nome, String sigla, List<Cidade> cidades) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
        this.cidades = cidades;
    }

    public Estado() {
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}
/*
copy public.estado
from '/tmp/base-de-dados/estados_import.csv'
delimiter ','
csv header;

SELECT * FROM ESTADO;

 */