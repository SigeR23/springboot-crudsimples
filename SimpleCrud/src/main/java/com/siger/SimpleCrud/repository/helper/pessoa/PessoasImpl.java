package com.siger.SimpleCrud.repository.helper.pessoa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.siger.SimpleCrud.modal.Pessoa;
import com.siger.SimpleCrud.repository.filter.PessoaFilter;

public class PessoasImpl implements PessoasQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Pessoa> filtrar(PessoaFilter filter) {
		
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Pessoa.class);
		aplicarFiltro(filter, criteria);
		
		return criteria.list();
	}
	
	private void aplicarFiltro(PessoaFilter filter, Criteria criteria) {
		if (filter != null) {
			if(!StringUtils.isEmpty(filter.getNome()))
				criteria.add(Restrictions.ilike("nome", filter.getNome(), MatchMode.ANYWHERE));
		}
	}
}
