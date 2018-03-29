package com.siger.SimpleCrud.repository.helper.pessoa;

import java.util.List;

import com.siger.SimpleCrud.modal.Pessoa;
import com.siger.SimpleCrud.repository.filter.PessoaFilter;

public interface PessoasQueries {

	public List<Pessoa> filtrar(PessoaFilter filter);
}
