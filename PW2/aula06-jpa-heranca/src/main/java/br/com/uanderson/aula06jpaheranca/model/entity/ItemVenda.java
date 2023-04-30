package br.com.uanderson.aula06jpaheranca.model.entity;
import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Entity
@Component
//@Scope("session")
public class ItemVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer qtd;
    @ManyToOne // Muitos itemVenda podem está associados a um produto
    private Produto produto;

    @ManyToOne //-> Vários itemVenda para uma Venda
    private Venda venda;


    public ItemVenda(Long id, Integer qtd, Produto produto, Venda venda) {
        this.id = id;
        this.qtd = qtd;
        this.produto = produto;
        this.venda = venda;
    }

    public ItemVenda() {
    }

    public double total(){
        return produto.getValor() * qtd;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQtd() {
        if(qtd == null){
            qtd = 0;
        }
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "ItemVenda{" +
                "id=" + id +
                ", qtd=" + qtd +
                ", produto=" + produto +
                '}';
    }
}//class
