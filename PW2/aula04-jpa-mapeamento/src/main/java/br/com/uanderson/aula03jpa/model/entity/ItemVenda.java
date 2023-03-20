package br.com.uanderson.aula03jpa.model.entity;
import jakarta.persistence.*;


@Entity
public class ItemVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne // Muitos itemVenda podem está associados a um produto
    @JoinColumn(name = "produto_id")
    private Produto produto;
    private int qtd;

    /*
    @ManyToOne -> Vários itemVenda para um produto
     */



    public ItemVenda(Long id, int qtd, Produto produto) {
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

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
}//class
