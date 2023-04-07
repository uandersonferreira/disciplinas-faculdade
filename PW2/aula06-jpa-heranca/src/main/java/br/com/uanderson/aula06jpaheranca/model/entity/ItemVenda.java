package br.com.uanderson.aula06jpaheranca.model.entity;
import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;


@Entity
@Scope("session")
public class ItemVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne // Muitos itemVenda podem está associados a um produto
    @JoinColumn(name = "produto_id")
    private Produto produto;
    private Integer qtd;

    /*
    @ManyToOne -> Vários itemVenda para um produto
     */



    public ItemVenda(Integer qtd, Produto produto) {
        this.qtd = qtd;
        this.produto = produto;
    }

    public ItemVenda() {
    }

    public double total(){
        return produto.getValor() * qtd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }
}//class
