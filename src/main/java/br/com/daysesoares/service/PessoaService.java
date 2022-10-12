package br.com.daysesoares.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.daysesoares.model.Pessoa;

@Service
public class PessoaService {

	
	
	public List<Pessoa> findAll() {
		List<Pessoa> list = new ArrayList<>();
		Pessoa pessoa = new Pessoa(1, "João", 30);
		list.add(pessoa);
		pessoa = new Pessoa(2, "Maria", 25);
		list.add(pessoa);
		pessoa = new Pessoa(3, "Pedro", 18);
		list.add(pessoa);
		
		return list;
		
	}
	
	public Pessoa findById(Integer id) {
		List<Pessoa> list = new ArrayList<>();
		Pessoa pessoa = new Pessoa(1, "João", 30);
		list.add(pessoa);
		pessoa = new Pessoa(2, "Maria", 25);
		list.add(pessoa);
		pessoa = new Pessoa(3, "Pedro", 18);
		list.add(pessoa);
		pessoa = null;
		for (Pessoa f : list) {
			if(f.getId() == id) {
				pessoa = f;
			}
		}
		
		return pessoa;
		
	}
}
