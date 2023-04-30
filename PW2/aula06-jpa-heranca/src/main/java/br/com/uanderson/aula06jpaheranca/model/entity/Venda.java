package br.com.uanderson.aula06jpaheranca.model.entity;

import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


//@Scope("session")
@Entity
@Component
public class Venda implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate localDate;
    private Double totalVenda;
    @OneToMany()//Uma venda para muitos itensVendas. mappedBy = "venda", cascade = CascadeType.PERSIST
    @JoinColumn(name = "id_itemVenda")
    private List<ItemVenda> itensList;

    //UMA PESSOA PODE TER VÁRIAS VENDAS
    //MUITAS VENDAS PERTENCEM A UMA PESSOA
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    public Venda(Long id, LocalDate localDate, Double totalVenda, List<ItemVenda> itensList, Pessoa pessoa) {
        this.id = id;
        this.localDate = localDate;
        this.totalVenda = totalVenda;
        this.itensList = itensList;
        this.pessoa = pessoa;
    }

    public Venda() {
    }

    public Double total(){
        double valor = 0;
        for (ItemVenda itemVenda : itensList) {
            double total = itemVenda.total();
            valor += total;
        }
        return  valor;
        /*
        Pegamos uma lista de itens é a percorremos
        então chamo o metodo total é armazeno o valor numa variavel
        que irá se somada com a variavel de valor que irá atualizar o preço
        a cada interação dependendo da quantidade de itens vendidos no dia
        e no final retornamos a variável valor.
         */
    }

    public Produto getProdutoItemVenda(){
        Produto produto = new Produto();
        for (ItemVenda item : itensList) {
            produto = item.getProduto();
        }
        return produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venda venda = (Venda) o;
        return Objects.equals(id, venda.id) && Objects.equals(localDate, venda.localDate) && Objects.equals(itensList, venda.itensList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, localDate, itensList);
    }


    public Double getTotalVenda() {
        return totalVenda;
    }

    public void setTotalVenda(Double totalVenda) {
        this.totalVenda = totalVenda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public List<ItemVenda> getItensList() {
        return itensList;
    }

    public void setItensList(List<ItemVenda> itensList) {
        this.itensList = itensList;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}//class
