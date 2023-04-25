package br.com.uanderson.aula06jpaheranca.model.entity;
import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;


@Entity
@Scope("session")
public class ItemVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer qtd;
    @ManyToOne // Muitos itemVenda podem está associados a um produto
    private Produto produto;


//    @ManyToOne //-> Vários itemVenda para uma Venda
//    private Venda venda;


    public ItemVenda(Long id, Integer qtd, Produto produto) {
        this.id = id;
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

}//class
