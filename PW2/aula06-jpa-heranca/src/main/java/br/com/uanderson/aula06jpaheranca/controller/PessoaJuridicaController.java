package br.com.uanderson.aula06jpaheranca.controller;

import br.com.uanderson.aula06jpaheranca.model.entity.PessoaJuridica;
import br.com.uanderson.aula06jpaheranca.model.entity.Role;
import br.com.uanderson.aula06jpaheranca.model.repository.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@Transactional
@RequestMapping("pessoas/juridica")
public class PessoaJuridicaController {
    private final PessoaJuridicaRepository pessoaJuridicaRepository;
    private final PessoaRepository pessoaRepository;
    private final CidadeRepository cidadeRepository;
    private final EnderecoRepository enderecoRepository;
    private final RoleRepository roleRepository;




    @Autowired
    public PessoaJuridicaController(PessoaJuridicaRepository pessoaJuridicaRepository, PessoaRepository pessoaRepository, CidadeRepository cidadeRepository, EnderecoRepository enderecoRepository, RoleRepository roleRepository) {
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
        this.pessoaRepository = pessoaRepository;
        this.cidadeRepository = cidadeRepository;
        this.enderecoRepository = enderecoRepository;
        this.roleRepository = roleRepository;
    }

    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/list")
    public String listarPessoasJuridicas(ModelMap modelMap){
        modelMap.addAttribute("pessoasJuridicas", pessoaJuridicaRepository.listAll());
        return "/pessoa/pessoaJuridicaList";//page html
    }


    @GetMapping("/form")
    public ModelAndView form(PessoaJuridica pessoaJuridica){
        ModelAndView mv = new ModelAndView("/pessoa/formPessoaJuridica");
        mv.addObject("pessoa",pessoaJuridica);//manda o objeto funcionário para a view cadastro.html
        mv.addObject("listaCidades", cidadeRepository.listAll());
        return mv;
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid PessoaJuridica pessoaJuridica, BindingResult bindingResult){
        if (bindingResult.hasErrors()) return form(pessoaJuridica);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        pessoaJuridica.getUsuario().setSenha(bCryptPasswordEncoder.encode(pessoaJuridica.getUsuario().getPassword()));
        pessoaJuridica.getUsuario().setLogin(pessoaJuridica.getEmail());

        // 1- ROLE_USER | 2- ROLE_ADMIN
        ArrayList<Role> roles = new ArrayList<>();
        Role role = roleRepository.findRoleById(1L);
        roles.add(role);
        pessoaJuridica.getUsuario().setRoles(roles);

        enderecoRepository.save(pessoaJuridica.getEndereco());
        pessoaJuridicaRepository.save(pessoaJuridica);
        return new ModelAndView("redirect:/pessoas/juridica/list");
    }


    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        pessoaJuridicaRepository.removeById(id);
        return new ModelAndView("redirect:/pessoas/juridica/list");
    }


    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("pessoaJuridica", pessoaJuridicaRepository.findById(id));
        model.addAttribute("listaCidades", cidadeRepository.listAll());

        return new ModelAndView("/pessoa/formPessoaJuridica", model);//view formPessoaJuridica.html
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid PessoaJuridica pessoaJuridica, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return form(pessoaJuridica);

        pessoaRepository.update(pessoaJuridica);
        return new ModelAndView("redirect:/pessoas/juridica/list");
    }

}//class
