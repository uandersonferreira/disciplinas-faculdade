package br.com.uanderson.aula06jpaheranca.controller;

import br.com.uanderson.aula06jpaheranca.model.entity.*;
import br.com.uanderson.aula06jpaheranca.model.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//@Scope("request")
@Controller
@Transactional
@RequestMapping("vendas")
public class VendaController {
   private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;
    private final PessoaRepository pessoaRepository;
    private final ItemVendaRepository itemVendaRepository;
    private  final UsuarioRepository usuarioRepository;

    private List<ItemVenda> itemVendaList = new ArrayList<>();
    private Venda venda;

    public VendaController(VendaRepository vendaRepository, ProdutoRepository produtoRepository, PessoaRepository pessoaRepository, ItemVendaRepository itemVendaRepository, UsuarioRepository usuarioRepository, Venda venda) {
        this.vendaRepository = vendaRepository;
       this.produtoRepository = produtoRepository;
       this.pessoaRepository = pessoaRepository;
       this.itemVendaRepository = itemVendaRepository;
       this.usuarioRepository = usuarioRepository;
       this.venda = venda;

   }

    @GetMapping("/list")
    public String listarVendas(ModelMap modelMap){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailLoginUsuario = authentication.getName();
        Pessoa pessoa = pessoaRepository.findPessoaByEmail(emailLoginUsuario);

        modelMap.addAttribute("listVendas", vendaRepository.findVendasByPessoaId(pessoa.getId()));
        return "venda/list";//page html
    }

    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/carrinho")
    public ModelAndView chamarCarrinho(HttpSession session){
        ModelAndView modelAndView = new ModelAndView("produto/carrinho");
        venda.setItensList(itemVendaList);//associando a lista de itens a venda
        session.setAttribute("venda", venda);

        return modelAndView;
    }

//    private void buscarUsuarioLogado(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (!(authentication instanceof AnonymousAuthenticationToken)){
//            String nameAuthentication = authentication.getName();
//            Usuario usuario = usuarioRepository.findUsuarioByLogin(nameAuthentication);
//
//        }
//    }//method


    @GetMapping("/finalizar")
    public ModelAndView finalizarCompra(){
        ModelAndView modelAndView = new ModelAndView("produto/finalizar");
        venda.setItensList(itemVendaList);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailLoginUsuario = authentication.getName();
        Pessoa pessoa = pessoaRepository.findPessoaByEmail(emailLoginUsuario);

        modelAndView.addObject("pessoaLogada", pessoa);
        modelAndView.addObject("venda", venda);
        return modelAndView;
    }

    @PostMapping("/finalizar/confirmar")
    public ModelAndView confirmarCompra(Long pessoa){
        Pessoa pessoaEncontrada = pessoaRepository.findById(pessoa);
        venda.setId(null);
        venda.setLocalDate(LocalDate.now());
        venda.setPessoa(pessoaEncontrada);
        venda.setTotalVenda(venda.total());
        vendaRepository.save(venda);

        List<ItemVenda> itensList = venda.getItensList();
        for (ItemVenda itemVenda : itensList) {
            itemVenda.setVenda(venda);
            itemVendaRepository.save(itemVenda);
        }

        itemVendaList = new ArrayList<>();//PARA LIMPAR O CARRINHO APÓS REALZIAR A VENDA

        return new ModelAndView("redirect:/vendas/list");
    }


    @GetMapping("/alterarQuantidade/{id}/{acao}")
    public String alterarQuantidade(@PathVariable Long id, @PathVariable Integer acao){
        //SE 1 = ADICIONAR / 0 - DIMINUIR
        for (ItemVenda item : itemVendaList) {
            if (item.getProduto().getId().equals(id)){
                if (acao.equals(1)){
                    item.setQtd(item.getQtd() + 1);
                }else if (acao.equals(0)){
                    item.setQtd(item.getQtd() - 1);
                    if (item.getQtd() < 0){
                        item.setQtd(0);
                    }
                }
                break;
            }
        }//foreach
        return "redirect:/vendas/carrinho";
    }
    @GetMapping("/removerProduto/{id}")
    public String removerProduto(@PathVariable Long id){
        for (ItemVenda item : itemVendaList) {
            if (item.getProduto().getId().equals(id)){
                itemVendaList.remove(item);
                break;
            }
        }//foreach
        return "redirect:/vendas/carrinho";
    }

    @GetMapping("adicionar/produto/{id}")
    public String addCarrinho(@PathVariable Long id){
        Produto produto = produtoRepository.findById(id);
        int controle = 0;
        if (itemVendaList != null){
            for (ItemVenda item : itemVendaList) {
                if (item.getProduto().getId().equals(produto.getId())){
                    item.setQtd(item.getQtd() + 1);
                    controle = 1;
                    break;
                }
            }//for
        }//if

        if (controle == 0){
            ItemVenda item = new ItemVenda();
            item.setId(null);
            item.setProduto(produto);
            item.setQtd(item.getQtd() + 1);//0 +1
            System.out.println("ITEM: "+item.getProduto().getDescricao());
            itemVendaList.add(item);//está sobreescrevendo os itens adciconandos no carrinho
            System.out.println("TAMANHO: "+itemVendaList.size());
        }
        return "redirect:/vendas/carrinho";
    }



}//class
