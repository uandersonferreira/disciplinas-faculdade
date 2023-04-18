package br.com.uanderson.aula06jpaheranca.controller;

import br.com.uanderson.aula06jpaheranca.model.entity.*;
import br.com.uanderson.aula06jpaheranca.model.repository.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@Transactional
@RequestMapping("produtos")
@Log4j2
public class ProdutoController {
    private final ProdutoRepository produtoRepository;
    private final PessoaRepository pessoaRepository;
    private final VendaRepository vendaRepository;
    private final ItemVendaRepository itemVendaRepository;
    private List<ItemVenda> itemVendaList = new ArrayList<>();
//    private List<Pessoa> pessoaList = new ArrayList<>();
    private Venda venda = new Venda();

    public ProdutoController(ProdutoRepository produtoRepository, PessoaRepository pessoaRepository, VendaRepository vendaRepository, ItemVendaRepository itemVendaRepository) {
        this.produtoRepository = produtoRepository;
        this.pessoaRepository = pessoaRepository;
        this.vendaRepository = vendaRepository;
        this.itemVendaRepository = itemVendaRepository;
    }


    @GetMapping("/list")
    public String listarProdutos(ModelMap modelMap){
        modelMap.addAttribute("produtos", produtoRepository.listAll());
        return "/produto/list";//page html
    }



    @GetMapping("/form")
    public String form(Produto produto){
        return "/produto/form";
    }

    @RequestMapping("/")
    public String home(Produto produto){
        return "home";
    }

    @PostMapping("/save")
    public ModelAndView save(Produto produto){
        log.info("dentro do save Controller '{}'", produto.getDescricao());
        produtoRepository.save(produto);
        return new ModelAndView("redirect:/produtos/list");
        /*
        Ao realizar o submit do form.html irá se chamado o metodo save por de baixo dos panos
        para pode salvar e quando aciona o /save realiza-se um redirecionamento automático para
        para a lista de produtos através da chamado do method do controller /produtos/list.
         */
    }


    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        produtoRepository.removeById(id);
        return new ModelAndView("redirect:/produtos/list");
    }


    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("produto", produtoRepository.findById(id));
        return new ModelAndView("/produto/form", model);//view form.html
    }

    @PostMapping("/update")
    public ModelAndView update(Produto produto) {
        produtoRepository.update(produto);
        return new ModelAndView("redirect:/produtos/list");
    }


    @GetMapping("/carrinho")
    public ModelAndView chamarCarrinho(){
        ModelAndView modelAndView = new ModelAndView("produto/carrinho");

        venda.setItensList(itemVendaList);//associando a lista de itens a venda

        modelAndView.addObject("venda", venda);
        modelAndView.addObject("itemVendaList", itemVendaList);//pegando a lista de carrinhos e mandando para finalizar.html
        return modelAndView;
    }

    @GetMapping("/finalizar")
    public ModelAndView finalizarCompra(){
        ModelAndView modelAndView = new ModelAndView("produto/finalizar");
        venda.setItensList(itemVendaList);
        List<Pessoa> pessoas = pessoaRepository.listAll();

        modelAndView.addObject("pessoaList", pessoas);
        modelAndView.addObject("venda", venda);
        modelAndView.addObject("itemVendaList", itemVendaList);// para pegar a page finalizar.html
        return modelAndView;
    }

    @PostMapping("/finalizar/confirmar")
    public ModelAndView confirmarCompra(){
        Pessoa pessoa = pessoaRepository.listAll().get(0);//SOMENTE PARA TESTE ESTOU SEMPRE ATRIBUINDO A VENDA A 1° PESSOA DO BANCO QUE É RETORNADA.
        venda.setId(null);
        venda.setLocalDate(LocalDate.now());
        venda.setPessoa(pessoa);
        vendaRepository.save(venda);

        List<ItemVenda> itensList = venda.getItensList();
        for (ItemVenda itemVenda : itensList) {
            itemVendaRepository.save(itemVenda);
        }

        return new ModelAndView("redirect:/vendas/list");
    }


    @GetMapping("/alterarQuantidade/{id}/{acao}")
    public String alterarQuantidade(@PathVariable Long id, @PathVariable Integer acao){
        //SE 1 = ADICIONAR / 0 - DIMINUIR
        for (ItemVenda item : itemVendaList) {
            if (item.getProduto().getId().equals(id)){
                if (acao.equals(1)){
                    item.setQtd(item.getQtd() + 1);
                }else if (acao.equals(0)){
                    item.setQtd(item.getQtd() - 1);
                    if (item.getQtd() < 0){
                        item.setQtd(0);
                    }
                }
                break;
            }
        }//foreach
        return "redirect:/produtos/carrinho";
    }
    @GetMapping("/removerProduto/{id}")
    public String removerProduto(@PathVariable Long id){
        for (ItemVenda item : itemVendaList) {
            if (item.getProduto().getId().equals(id)){
                itemVendaList.remove(item);
                break;
            }
        }//foreach
        return "redirect:/produtos/carrinho";
    }

    @GetMapping("adicionar/produto/{id}")
    public String addCarrinho(@PathVariable Long id){
        Produto produto = produtoRepository.findById(id);
        int controle = 0;
        for (ItemVenda item : itemVendaList) {
            if (item.getProduto().getId().equals(produto.getId())){
                item.setQtd(item.getQtd() + 1);
                controle = 1;
                break;
            }
        }
        if (controle == 0){
            ItemVenda item = new ItemVenda();
            item.setProduto(produto);
            item.setQtd(item.getQtd() + 1);
            itemVendaList.add(item);
        }
        return "redirect:/produtos/carrinho";
    }




}//class
