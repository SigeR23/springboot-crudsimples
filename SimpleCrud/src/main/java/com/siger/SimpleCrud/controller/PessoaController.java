package com.siger.SimpleCrud.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.siger.SimpleCrud.modal.Pessoa;
import com.siger.SimpleCrud.modal.TipoPessoa;
import com.siger.SimpleCrud.repository.Pessoas;
import com.siger.SimpleCrud.repository.filter.PessoaFilter;
import com.siger.SimpleCrud.service.PessoaService;
import com.siger.SimpleCrud.service.exception.ImpossivelExcluirPessoaExcpetion;

@Controller
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	private Pessoas pessoas;
	
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping("/nova")
	public ModelAndView carregar(Pessoa pessoa) {
		ModelAndView mv = new ModelAndView("pessoa/CadastrarPessoa");
		mv.addObject("tiposPessoa", TipoPessoa.values());
		return mv;
	}
	
	@PostMapping(value = {"/nova", "{\\d+}"})
	public ModelAndView salvar(@Valid Pessoa pessoa, BindingResult result, Model model, RedirectAttributes attributes, HttpServletRequest httpServletRequest) {
		if(result.hasErrors()) {
			model.addAttribute(pessoa);
			return carregar(pessoa);
		}
		
		
		pessoaService.salvar(pessoa);
		
		attributes.addFlashAttribute("mensagem", (httpServletRequest.getRequestURI().contains("/nova") ? "Pessoa salva com sucesso" : "Alteração salva com sucesso"));
		
		return new ModelAndView("redirect:/pessoas/nova");
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Pessoa pessoa) {
		try {
			pessoaService.excluir(pessoa);
		} catch(ImpossivelExcluirPessoaExcpetion e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{codigo}")
	public @ResponseBody ModelAndView editar(@PathVariable("codigo") Pessoa pessoa) {
		ModelAndView mv = carregar(pessoa);
		mv.addObject(pessoa);
		return mv;
	}
	
	@GetMapping("/pesquisar")
	public ModelAndView pesquisar(PessoaFilter filter, BindingResult result) {
		ModelAndView mv = new ModelAndView("/pessoa/PesquisarPessoa");
		mv.addObject("tipoPessoas", TipoPessoa.values());
		mv.addObject("pessoas", pessoas.filtrar(filter));
		return mv;
	}
	

} 
