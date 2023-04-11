package br.com.uanderson.aula06jpaheranca.controller;

import br.com.uanderson.aula06jpaheranca.model.entity.*;
import br.com.uanderson.aula06jpaheranca.model.repository.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final ItemVendaRepository itemVendaRepository;
    private final PessoaFisicaRepository pessoaFisicaRepository;
    private final PessoaJuridicaRepository pessoaJuridicaRepository;
    private List<ItemVenda> itemVendaList = new ArrayList<>();
    private Venda venda = new Venda();
    private List<Pessoa> pessoaList = new ArrayList<>();

    @Autowired
    public ProdutoController(ProdutoRepository produtoRepository, ItemVendaRepository itemVendaRepository, PessoaFisicaRepository pessoaFisicaRepository, PessoaJuridicaRepository pessoaJuridicaRepository) {
        this.produtoRepository = produtoRepository;
        this.itemVendaRepository = itemVendaRepository;
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
    public ModelAndView chamarCarrinho(){
        ModelAndView modelAndView = new ModelAndView("produto/carrinho");
        venda.setItensList(itemVendaList);
        venda.setLocalDate(LocalDate.now());

        modelAndView.addObject("venda", venda);
        modelAndView.addObject("itemVendaList", itemVendaList);
        return modelAndView;
    }

    @GetMapping("/finalizar")
    public ModelAndView finalizarCompra(){
        ModelAndView modelAndView = new ModelAndView("produto/finalizar");
        venda.setItensList(itemVendaList);
        venda.setLocalDate(LocalDate.now());

        List<PessoaFisica> pessoaFisicas = pessoaFisicaRepository.listAll();
        List<PessoaJuridica> pessoaJuridicas = pessoaJuridicaRepository.listAll();

        for (PessoaFisica pf : pessoaFisicas) {
            pessoaList.add(pf);
        }
        for (PessoaJuridica pj : pessoaJuridicas) {
            pessoaList.add(pj);
        }

        modelAndView.addObject("pessoaList",pessoaList);

        modelAndView.addObject("venda", venda);
        modelAndView.addObject("itemVendaList", itemVendaList);
        return modelAndView;
    }

//    @PostMapping("finalizar/confirmar")
//    public ModelAndView confirmarCompra(){
//        ModelAndView modelAndView = new ModelAndView("venda/venda-finalizada");
//        venda.setPessoa();
//        return modelAndView;
//    }


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
