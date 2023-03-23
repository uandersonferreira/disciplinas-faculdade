package br.com.uanderson.aula03jpa.controller;

import br.com.uanderson.aula03jpa.model.entity.ItemVenda;
import br.com.uanderson.aula03jpa.model.repository.ItemVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("itemvenda")
public class ItemVendaController {

    private  final ItemVendaRepository itemVendaRepository;

    @Autowired
    public ItemVendaController(ItemVendaRepository itemVendaRepository) {
        this.itemVendaRepository = itemVendaRepository;
    }

    @RequestMapping("/")
    public String home(ItemVenda itemVenda){
        return "home";
    }

    @GetMapping("/list")
    public String listarItensVendas(ModelMap modelMap){
        modelMap.addAttribute("itensVenda", itemVendaRepository.listAll());
        return "itensVenda/list";//page html
    }

    
    @GetMapping("/form")
    public String form(ItemVenda itemVenda){
        return "/itemVenda/form";
    }
    

    @PostMapping("/save")
    public ModelAndView save(ItemVenda itemVenda){
        itemVendaRepository.save(itemVenda);
        return new ModelAndView("redirect:/itemvenda/list");
        /*
        Ao realizar o submit do form.html irá se chamado o metodo save por de baixo dos panos
        para pode salvar e quando aciona o /save realiza-se um redirecionamento automático para
        para a lista de itemvenda através da chamado do method do controller /itemvenda/list.
         */
    }


    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        itemVendaRepository.removeById(id);
        return new ModelAndView("redirect:/itemvenda/list");
    }


    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("itensVenda", itemVendaRepository.findById(id));
        return new ModelAndView("/itemVenda/form", model);//view form.html
    }

    @PostMapping("/update")
    public ModelAndView update(ItemVenda itemVenda) {
        itemVendaRepository.update(itemVenda);
        return new ModelAndView("redirect:/itemvenda/list");
    }


}//class
