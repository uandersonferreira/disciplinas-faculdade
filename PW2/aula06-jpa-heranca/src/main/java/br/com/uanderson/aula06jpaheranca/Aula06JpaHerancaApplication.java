package br.com.uanderson.aula06jpaheranca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Aula06JpaHerancaApplication {

    public static void main(String[] args) {
        SpringApplication.run(Aula06JpaHerancaApplication.class, args);
    }

}
/*
 * Criar view para cadastro e consulta de produtos; OK

 * Criar view que lista os produtos e permita adicionar um produto ao carrinho;

 * Crie uma view CARRINHO (DE COMPRAS) que possibilita visualizar os itens que
 * foram inseridos, remover caso desejar e finalizar a venda. Ao concluir a venda,
 *  apresentar a lista de vendas ao usuário.

 * Criar também uma view para listar todas as Vendas com opção para visualizar dados
 *  de uma Venda em particular;
 */