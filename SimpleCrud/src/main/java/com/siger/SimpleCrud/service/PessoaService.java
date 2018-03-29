package com.siger.SimpleCrud.service;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siger.SimpleCrud.modal.Pessoa;
import com.siger.SimpleCrud.repository.Pessoas;
import com.siger.SimpleCrud.service.exception.ImpossivelExcluirPessoaExcpetion;

@Service
public class PessoaService {

	@Autowired
	private Pessoas pessoas;
	
	@Transactional
	public void salvar(Pessoa pessoa) {
		pessoas.save(pessoa);
	}
	
	@Transactional
	public void excluir(Pessoa pessoa) {
		try {
			pessoas.delete(pessoa);;
			pessoas.flush();
		
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirPessoaExcpetion("Impossivel Excluir essa pessoa");
		}
	}
	
	public void editar(Long codigo) {
		
	}
}
