package br.com.uanderson.aula06jpaheranca.controller;

import br.com.uanderson.aula06jpaheranca.model.entity.Estado;
import br.com.uanderson.aula06jpaheranca.model.entity.Pessoa;
import br.com.uanderson.aula06jpaheranca.model.entity.PessoaFisica;
import br.com.uanderson.aula06jpaheranca.model.entity.Role;
import br.com.uanderson.aula06jpaheranca.model.repository.*;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import java.util.List;

@Controller
@Transactional
@RequestMapping("pessoas/fisica")
public class PessoaFisicaController {
    private final PessoaFisicaRepository pessoaFisicaRepository;
    private final PessoaRepository pessoaRepository;
    private final CidadeRepository cidadeRepository;
    private final EstadoRepository estadoRepository;
    private final EnderecoRepository enderecoRepository;
    private final RoleRepository roleRepository;

    public PessoaFisicaController(PessoaFisicaRepository pessoaFisicaRepository, PessoaRepository pessoaRepository, CidadeRepository cidadeRepository, EstadoRepository estadoRepository, EnderecoRepository enderecoRepository, RoleRepository roleRepository) {
        this.pessoaFisicaRepository = pessoaFisicaRepository;
        this.pessoaRepository = pessoaRepository;
        this.cidadeRepository = cidadeRepository;
        this.estadoRepository = estadoRepository;
        this.enderecoRepository = enderecoRepository;
        this.roleRepository = roleRepository;
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
    public ModelAndView save(@Valid PessoaFisica pessoaFisica, BindingResult bindingResult){
        if (bindingResult.hasErrors()) return form(pessoaFisica);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        Estado estado = pessoaFisica.getEndereco().getCidade().getEstado();
        pessoaFisica.getEndereco().getCidade().setEstado(estado);
        pessoaFisica.getUsuario().setSenha(bCryptPasswordEncoder.encode(pessoaFisica.getUsuario().getPassword()));
        pessoaFisica.getUsuario().setLogin(pessoaFisica.getEmail());

        // 1- ROLE_USER | 2- ROLE_ADMIN
        ArrayList<Role> roles = new ArrayList<>();
        Role role = roleRepository.findRoleById(1L);
        roles.add(role);
        pessoaFisica.getUsuario().setRoles(roles);

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
    public ModelAndView update(@Valid PessoaFisica pessoaFisica, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return form(pessoaFisica);

        pessoaRepository.update(pessoaFisica);
        return new ModelAndView("redirect:/pessoas/fisica/list");
    }

}//class
