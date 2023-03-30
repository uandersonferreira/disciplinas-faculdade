package br.com.uanderson.aula06jpaheranca.controller;

import br.com.uanderson.aula06jpaheranca.model.repository.PessoaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("pessoa/juridica")
public class PessoaJuridicaController {
    private final PessoaJuridicaRepository pessoaJuridicaRepository;

    @Autowired
    public PessoaJuridicaController(PessoaJuridicaRepository pessoaJuridicaRepository) {
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
    }

    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/list")
    public String listarPessoasFisicas(ModelMap modelMap){
        modelMap.addAttribute("pessoasJuridicas", pessoaJuridicaRepository.listAll());
        return "/pessoa/pessoaJuridicaList";//page html
    }
}
