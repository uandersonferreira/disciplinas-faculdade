package br.com.uanderson.aula06jpaheranca.model.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Produto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Informe uma descrição para o produto!")
    private String descricao;
    @DecimalMin(value = "0.01", message = "Valor não permitido. Informe um valor maior que 0" )
    @NotNull(message = "Valor não permitido. Informe um valor maior que 0 para o produto!")
    private Double valor;
    private String nomeImagem;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    @Null//Deixar null, pois o produto pode não ter sido vendido
    private List<ItemVenda> itemVenda;
    /*
    @OneToMany -> Um produto para vários itemVenda
    mappedBy = "produto" -> faz referência ao atributo "produto"
    definido na classe ItemVenda.

     */

    public Produto(Long id, String descricao, @NotNull(message = "Valor não permitido. Informe um valor maior que 0 para o produto!") Double valor, String nomeImagem, List<ItemVenda> itemVenda) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.nomeImagem = nomeImagem;
        this.itemVenda = itemVenda;
    }

    public Produto() {
    }

    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
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
