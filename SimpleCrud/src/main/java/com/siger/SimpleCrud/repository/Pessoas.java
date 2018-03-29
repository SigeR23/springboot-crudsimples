package com.siger.SimpleCrud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siger.SimpleCrud.modal.Pessoa;
import com.siger.SimpleCrud.repository.helper.pessoa.PessoasQueries;

@Repository
public interface Pessoas extends JpaRepository<Pessoa, Long>, PessoasQueries {

	public Optional<Pessoa> findByNome(String nome);
	
	public Optional<Pessoa> findByCpfOuCnpj(String cpfOuCnpj);
}
