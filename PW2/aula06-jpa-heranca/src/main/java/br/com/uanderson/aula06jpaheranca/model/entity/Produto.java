package br.com.uanderson.aula06jpaheranca.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Produto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private Double valor;

    @OneToMany(mappedBy = "produto")
    private List<ItemVenda> itemVenda;
    /*
    @OneToMany -> Um produto para vários itemVenda
    mappedBy = "produto" -> faz referência ao atributo "produto"
    definido na classe ItemVenda.

     */

    public Produto(Long id, String descricao, Double valor, List<ItemVenda> itemVenda) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.itemVenda = itemVenda;
    }

    public Produto() {
    }

    public List<ItemVenda> getItemVenda() {
        return itemVenda;
    }

    public void setItemVenda(List<ItemVenda> itemVenda) {
        this.itemVenda = itemVenda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}//class
