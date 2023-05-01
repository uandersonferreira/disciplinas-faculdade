package br.com.uanderson.aula06jpaheranca.controller;

import br.com.uanderson.aula06jpaheranca.model.entity.Estado;
import br.com.uanderson.aula06jpaheranca.model.entity.PessoaFisica;
import br.com.uanderson.aula06jpaheranca.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Transactional
@RequestMapping("pessoas/fisica")
public class PessoaFisicaController {
    private final PessoaFisicaRepository pessoaFisicaRepository;
    private final PessoaRepository pessoaRepository;
    private final CidadeRepository cidadeRepository;
    private final EstadoRepository estadoRepository;
    private final EnderecoRepository enderecoRepository;



    @Autowired
    public PessoaFisicaController(PessoaFisicaRepository pessoaFisicaRepository, PessoaRepository pessoaRepository, CidadeRepository cidadeRepository, EstadoRepository estadoRepository, EnderecoRepository enderecoRepository) {
        this.pessoaFisicaRepository = pessoaFisicaRepository;
        this.pessoaRepository = pessoaRepository;
        this.cidadeRepository = cidadeRepository;
        this.estadoRepository = estadoRepository;
        this.enderecoRepository = enderecoRepository;
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


    @GetMapping("/form")
    public ModelAndView form(PessoaFisica pessoaFisica){
        ModelAndView mv = new ModelAndView("/pessoa/formPessoaFisica");
        mv.addObject("pessoa",pessoaFisica);//manda o objeto funcionário para a view cadastro.html
        mv.addObject("listaCidades", cidadeRepository.listAll());
        mv.addObject("listaEstados", estadoRepository.listAll());
        return mv;
    }

    @PostMapping("/save")
    public ModelAndView save(PessoaFisica pessoaFisica){
        Estado estado = pessoaFisica.getEndereco().getCidade().getEstado();
        pessoaFisica.getEndereco().getCidade().setEstado(estado);
        enderecoRepository.save(pessoaFisica.getEndereco());
        pessoaFisicaRepository.save(pessoaFisica);
        return new ModelAndView("redirect:/pessoas/fisica/list");
    }


    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        pessoaFisicaRepository.removeById(id);
        return new ModelAndView("redirect:/pessoas/fisica/list");
    }


    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("pessoaFisica", pessoaFisicaRepository.findById(id));
        model.addAttribute("listaCidades", cidadeRepository.listAll());

        return new ModelAndView("/pessoa/formPessoaFisica", model);//view formPessoaJuridica.html
    }

    @PostMapping("/update")
    public ModelAndView update(PessoaFisica pessoaFisica) {
        pessoaRepository.update(pessoaFisica);
        return new ModelAndView("redirect:/pessoas/fisica/list");
    }

}//class
