package br.com.uanderson.aula06jpaheranca.controller;

import br.com.uanderson.aula06jpaheranca.model.entity.Venda;
import br.com.uanderson.aula06jpaheranca.model.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("vendas")
public class VendaController {
   private final VendaRepository vendaRepository;


   @Autowired
    public VendaController(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    @GetMapping("/list")
    public String listarVendas(ModelMap modelMap){
        modelMap.addAttribute("listVendas", vendaRepository.listAll());
        return "venda/list";//page html
    }

    @RequestMapping("/")
    public String home(){
        return "home";
    }


}//class
