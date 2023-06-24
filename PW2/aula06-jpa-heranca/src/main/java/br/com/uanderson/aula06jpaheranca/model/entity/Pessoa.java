package br.com.uanderson.aula06jpaheranca.model.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.context.annotation.Scope;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Scope("session")
public  class Pessoa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "Preencha o campo nome")
    @Size(min = 3, message = "O nome dever conter 3 ou mais caracteres")
    private String nome;
    @NotBlank(message = "Preencha o campo Email")
    @Email(message = "Informe um email válido. ex: seunome@exemplo.com")//Verifica somente o formato, e não se é um e-mail real válido!
    private String email;
    @NotBlank(message = "Preencha o campo Telefone ")
    private String telefone;
    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
    @Null//Pois a pessoa pode não ter nenhuma venda
    private List<Venda> vendaList;

    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    private Endereco endereco;//UMA PESSOA TÊM MAIS DE UM ENDEREÇO  1 - N

    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;

    public Pessoa(String nome, String email, String telefone, List<Venda> vendaList, Endereco endereco, Usuario usuario) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.vendaList = vendaList;
        this.endereco = endereco;
        this.usuario = usuario;
    }

    public Pessoa() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Venda> getVendaList() {
        return vendaList;
    }

    public void setVendaList(List<Venda> vendaList) {
        this.vendaList = vendaList;
    }


    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", vendaList=" + vendaList +
                ", endereco=" + endereco +
                '}';
    }
}//class
