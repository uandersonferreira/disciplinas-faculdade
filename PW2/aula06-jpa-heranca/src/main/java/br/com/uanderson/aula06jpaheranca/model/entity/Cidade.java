package br.com.uanderson.aula06jpaheranca.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Cidade implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String nome;
    @OneToOne
    private Estado estado;

    public Cidade(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Cidade() {
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


    @Override
    public String toString() {
        return "Cidade{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}

/*
copy public.cidade
from '/tmp/base-de-dados/municipios.csv'
delimiter ','
csv header;

SELECT * FROM CIDADE;
 */

