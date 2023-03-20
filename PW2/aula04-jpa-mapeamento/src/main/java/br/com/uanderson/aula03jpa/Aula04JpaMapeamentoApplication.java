package br.com.uanderson.aula03jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Aula04JpaMapeamentoApplication {

    public static void main(String[] args) {
        SpringApplication.run(Aula04JpaMapeamentoApplication.class, args);

    }

}
/*
O @PersistenceContext é usado especificamente quando precisamos injetar um EntityManager.

@mappedBy - Faz referência ao atributo da associação mais fraca,
quando queremos implementar uma associação bidirecional.

PRODUTO x ITEMVENDA

Um produto para vários itemvenda e vários itemVenda para um produto
- ManyToOne

- ITEMVENDA proprietário:
	- Na propriedade da associação anota-se com @ManyToOne
	- É responsável por manter a associação(pois é o lado 'muitos')
	- Declara a coluna de Ligação @JoinColumn

(SE FOR ASSOCIAÇÃO BIDIRECIONAL)
Do  lado oposto(PRODUTO) ao proprietário usa-se:
	- Anotação @OneToOne
    - Com atributo mappedBy fazendo referencia o atributo de referencia
    da class fraca declarada em ItemVenda.


ITEMVENDA  x VENDA (ASSOCIAÇÃO UNI-DIRECIONAL)

Vários ITEMVENDA para uma venda e uma VENDA para muitos itemVenda
- OneToMany

- VENDA:
    - @OneToMany
    - @JoinColumn

*/