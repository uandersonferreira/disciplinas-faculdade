package br.com.uanderson.aula06jpaheranca.controller;

import br.com.uanderson.aula06jpaheranca.model.entity.*;
import br.com.uanderson.aula06jpaheranca.model.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Transactional
@RequestMapping("produtos")
public class ProdutoController {
    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping("/list")
    public String listarProdutos(ModelMap modelMap){
        List<Produto> produtos = produtoRepository.listAll();
        if (produtos.isEmpty()){
            modelMap.addAttribute("mensagemErro", "Ops! não há produtos cadastrados no momento!!");
        }
        modelMap.addAttribute("produtos", produtos);
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
        produtoRepository.save(produto);
        return new ModelAndView("redirect:/produtos/list");
        /*
        Ao realizar o submit do formPessoaJuridica.html irá se chamado o metodo save por de baixo dos panos
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
        return new ModelAndView("/produto/form", model);//view formPessoaJuridica.html
    }

    @PostMapping("/update")
    public ModelAndView update(Produto produto) {
        produtoRepository.update(produto);
        return new ModelAndView("redirect:/produtos/list");
    }


}//class
