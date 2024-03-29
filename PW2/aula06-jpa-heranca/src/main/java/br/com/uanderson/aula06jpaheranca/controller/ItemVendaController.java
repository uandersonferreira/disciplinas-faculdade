package br.com.uanderson.aula06jpaheranca.controller;

import br.com.uanderson.aula06jpaheranca.model.entity.ItemVenda;
import br.com.uanderson.aula06jpaheranca.model.entity.Pessoa;
import br.com.uanderson.aula06jpaheranca.model.repository.ItemVendaRepository;
import br.com.uanderson.aula06jpaheranca.model.repository.PessoaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("itemvenda")
@Transactional
public class ItemVendaController {
    private  final ItemVendaRepository itemVendaRepository;
    private final PessoaRepository pessoaRepository;



    public ItemVendaController(ItemVendaRepository itemVendaRepository, PessoaRepository pessoaRepository) {
        this.itemVendaRepository = itemVendaRepository;
        this.pessoaRepository = pessoaRepository;
    }

    @RequestMapping("/")
    public String home(ItemVenda itemVenda){
        return "home";
    }

    @GetMapping("/list")
    public String listarItensVendas(ModelMap modelMap){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailLoginUsuario = authentication.getName();
        Pessoa pessoa = pessoaRepository.findPessoaByEmail(emailLoginUsuario);

        modelMap.addAttribute("itensVenda", itemVendaRepository.findItensByPessoaId(pessoa.getId()));
        return "itensVenda/list";//page html
    }

    
    @GetMapping("/form")
    public String form(ItemVenda itemVenda){
        return "itensVenda/form";
    }
    

    @GetMapping("/save")
    public ModelAndView save(ItemVenda itemVenda){
        itemVendaRepository.save(itemVenda);
        return new ModelAndView("redirect:/itemvenda/list");
    }


    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        itemVendaRepository.removeById(id);
        return new ModelAndView("redirect:/itemvenda/list");
    }


    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("itensVenda", itemVendaRepository.findById(id));
        return new ModelAndView("itensVenda/form", model);//view formPessoaJuridica.html
    }

    @PostMapping("/update")
    public ModelAndView update(ItemVenda itemVenda) {
        itemVendaRepository.update(itemVenda);
        return new ModelAndView("redirect:itemvenda/list");
    }



}//class
