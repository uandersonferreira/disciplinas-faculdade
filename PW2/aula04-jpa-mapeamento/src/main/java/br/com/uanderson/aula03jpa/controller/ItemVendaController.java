package br.com.uanderson.aula03jpa.controller;

import br.com.uanderson.aula03jpa.model.repository.ItemVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("itemvenda")
public class ItemVendaController {

    private  final ItemVendaRepository itemVendaRepository;

    @Autowired
    public ItemVendaController(ItemVendaRepository itemVendaRepository) {
        this.itemVendaRepository = itemVendaRepository;
    }

//    @GetMapping("/list")
//    public String listarVendas(ModelMap modelMap){
//        modelMap.addAttribute("itensVenda", itemVendaRepository.listAll());
//        return "venda/list";//page html
//    }
}//class
