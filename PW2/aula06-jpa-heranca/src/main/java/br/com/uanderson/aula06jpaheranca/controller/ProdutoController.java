package br.com.uanderson.aula06jpaheranca.controller;

import br.com.uanderson.aula06jpaheranca.model.entity.*;
import br.com.uanderson.aula06jpaheranca.model.repository.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

@Controller
@Transactional
@RequestMapping("produtos")
public class ProdutoController {
    private final ProdutoRepository produtoRepository;
    private static final String caminhoImagens = "/static/imagens/";

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping("/list")
    public String listarProdutos(ModelMap modelMap){
        modelMap.addAttribute("produtos", produtoRepository.listAll());
        return "/produto/list";//page html
    }

    @GetMapping("/form")
    public ModelAndView form(Produto produto){
        return new ModelAndView("/produto/form");
    }

    @RequestMapping("/")
    public String home(Produto produto){
        return "home";
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid Produto produto, BindingResult bindingResult, @RequestParam("file") MultipartFile arquivo){
        if (bindingResult.hasErrors()){
            return form(produto);
        }
        produtoRepository.save(produto);

        try{
            if (!arquivo.isEmpty()){
                byte[] bytes = arquivo.getBytes();
                String nomeImagem = produto.getId() + arquivo.getOriginalFilename();

                Path caminho = Paths.get(caminhoImagens + nomeImagem);
                Files.write(caminho, bytes);

                produto.setNomeImagem(nomeImagem);
            }

        }catch (IOException exception){
            exception.printStackTrace();
        }

        return new ModelAndView( "redirect:/produtos/list");
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
    public ModelAndView update(Produto produto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return form(produto);
        }
        produtoRepository.update(produto);
        return new ModelAndView("redirect:/produtos/list");
    }


}//class
