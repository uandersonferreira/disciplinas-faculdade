package br.com.uanderson.aula06jpaheranca.controller;

import br.com.uanderson.aula06jpaheranca.model.entity.ItemVenda;
import br.com.uanderson.aula06jpaheranca.model.entity.Produto;
import br.com.uanderson.aula06jpaheranca.model.entity.Venda;
import br.com.uanderson.aula06jpaheranca.model.repository.*;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@Transactional
@RequestMapping("produtos")
@Log4j2
public class ProdutoController {
    private final ProdutoRepository produtoRepository;
    private final ItemVendaRepository itemVendaRepository;
    private final VendaRepository vendaRepository;
    private  final PessoaFisicaRepository pessoaFisicaRepository;
    private final PessoaJuridicaRepository pessoaJuridicaRepository;
    private final Venda venda = new Venda();

    @Autowired
    public ProdutoController(ProdutoRepository produtoRepository, ItemVendaRepository itemVendaRepository, VendaRepository vendaRepository, PessoaFisicaRepository pessoaFisicaRepository, PessoaJuridicaRepository pessoaJuridicaRepository) {
        this.produtoRepository = produtoRepository;
        this.itemVendaRepository = itemVendaRepository;
        this.vendaRepository = vendaRepository;
        this.pessoaFisicaRepository = pessoaFisicaRepository;
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
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
    public String produtosDisponiveis(ModelMap modelMap){
        modelMap.addAttribute("carrinho", itemVendaRepository.listAll());
        return "/produto/produtos-disponiveis";
    }
    @GetMapping("adicionar/produto/{id}")
    public ModelAndView addCarrinho(@PathVariable Long id, ModelMap modelMap,HttpSession session){
        Produto produto = produtoRepository.findById(id);
        ItemVenda itemVenda = new ItemVenda(1, produto);
        List<ItemVenda> itensCarrinho = new ArrayList<>();
        itensCarrinho.add(itemVenda);
        session.setAttribute("itensCarrinho", itensCarrinho);

//        return new ModelAndView("redirect:produtos/list");
        return new ModelAndView("produto/produtos-disponiveis", modelMap);

    }



}//class
