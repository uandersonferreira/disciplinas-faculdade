package br.com.uanderson.aula06jpaheranca.controller;

import br.com.uanderson.aula06jpaheranca.model.repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Transactional
@RequestMapping("pessoa/fisica")
public class PessoaFisicaController {
    private final PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    public PessoaFisicaController(PessoaFisicaRepository pessoaFisicaRepository) {
        this.pessoaFisicaRepository = pessoaFisicaRepository;
    }

    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/list")
    public String listarPessoasFisicas(ModelMap modelMap){
        modelMap.addAttribute("pessoasFisicas", pessoaFisicaRepository.listAll());
        return "/pessoa/pessoaFisicaList";//page html
    }
}//class
