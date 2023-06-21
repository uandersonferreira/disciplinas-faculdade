package br.com.uanderson.aula06jpaheranca.controller;

import br.com.uanderson.aula06jpaheranca.model.entity.*;
import br.com.uanderson.aula06jpaheranca.model.repository.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Transactional
@RequestMapping("pessoas")
@Log4j2
public class PessoaController {

    private final PessoaRepository pessoaRepository;
    private final PessoaFisicaRepository pessoaFisicaRepository;
    private final PessoaJuridicaRepository pessoaJuridicaRepository;
    private final CidadeRepository cidadeRepository;
    private final EnderecoRepository enderecoRepository;


    @Autowired
    public PessoaController(PessoaRepository pessoaRepository, PessoaFisicaRepository pessoaFisicaRepository, PessoaJuridicaRepository pessoaJuridicaRepository, CidadeRepository cidadeRepository, EnderecoRepository enderecoRepository) {
        this.pessoaRepository = pessoaRepository;
        this.pessoaFisicaRepository = pessoaFisicaRepository;
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
        this.cidadeRepository = cidadeRepository;
        this.enderecoRepository = enderecoRepository;
    }


    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/list")
    public String listarPessoasFisicas(ModelMap modelMap){
        List<Pessoa> pessoas = pessoaRepository.listAll();
        modelMap.addAttribute("pessoas", pessoas );
        return "pessoa/listAllpessoa";//page html
    }

    @GetMapping("/form")
    public ModelAndView cadastrar(Pessoa pessoa){
        ModelAndView mv = new ModelAndView("formPessoaJuridica");
        mv.addObject("pessoa",pessoa);//manda o objeto funcionário para a view cadastro.html
        mv.addObject("listaCidades", cidadeRepository.listAll());
        return mv;
    }//cadastrar

//
//    @PostMapping("/save")
//    public ModelAndView salvar(Pessoa pessoa, String cpf, String cnpj, String razaoSocial, String tipoPessoa, BindingResult bindingResult){
//        if (bindingResult.hasErrors()){
//            return cadastrar(pessoa);//Retorna a pessoa para a page de cadrastro, preservando os dados do objeto/Funcionario
//            //que tentou realizar a ação.
//        }
//        System.out.println(pessoa);
//        System.out.println(pessoa.getEndereco());
//        System.out.printf("CPF: %s | CNPJ: %s | RAZÃO SOCIAL: %s",cpf ,cnpj , razaoSocial);
//
//        //TIPO PESSOA 1-FISICA | 2-JURIDICA
//        if (tipoPessoa.equals("1")){
//            PessoaFisica pessoaFisica = new PessoaFisica();
//            pessoaFisica.setId(pessoa.getId());
//            pessoaFisica.setNome(pessoa.getNome());
//            pessoaFisica.setEmail(pessoa.getEmail());
//            pessoaFisica.setTelefone(pessoa.getTelefone());
//            pessoaFisica.setEndereco(pessoa.getEndereco());
//            pessoaFisica.setCpf(cpf);
//
//            enderecoRepository.save(pessoa.getEndereco());
//            pessoaFisicaRepository.save(pessoaFisica);
//
//        } else if (tipoPessoa.equals("2")) {
//            PessoaJuridica pessoaJuridica = new PessoaJuridica();
//            pessoaJuridica.setId(pessoa.getId());
//            pessoaJuridica.setNome(pessoa.getNome());
//            pessoaJuridica.setEmail(pessoa.getEmail());
//            pessoaJuridica.setTelefone(pessoa.getTelefone());
//            pessoaJuridica.setEndereco(pessoa.getEndereco());
//            pessoaJuridica.setRazaoSocial(razaoSocial);
//            pessoaJuridica.setCnpj(cnpj);
//
//            enderecoRepository.save(pessoa.getEndereco());
//            pessoaJuridicaRepository.save(pessoaJuridica);
//        }
//        return cadastrar(new Pessoa());//É retornar a page de cadastro com um novo funcionário vazio;
//    }//salvar
//
//    @GetMapping("/remove/{id}")
//    public ModelAndView remove(@PathVariable("id") Long id){
//        pessoaRepository.removeById(id);
//        return new ModelAndView("redirect:/pessoas/list");
//    }
//
//
//    @GetMapping("/edit/{id}")
//    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
//        Pessoa pessoa = pessoaRepository.findById(id);
//        System.out.println(pessoa);
//        System.out.println(pessoa.getEndereco());
//
//        model.addAttribute("pessoa", pessoaRepository.findById(id));
//        return new ModelAndView("formPessoaJuridica", model);//view formPessoaJuridica.html
//    }
//
//    @PostMapping("/update")
//    public ModelAndView update(Pessoa pessoa) {
//        pessoaRepository.update(pessoa);
//        enderecoRepository.update(pessoa.getEndereco());
//        return new ModelAndView("redirect:/pessoas/list");
//    }
//


}//class
