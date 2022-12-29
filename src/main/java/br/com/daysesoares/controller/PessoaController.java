package br.com.daysesoares.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.daysesoares.model.Pessoa;
import br.com.daysesoares.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> listaPessoas(){
		List<Pessoa> list = pessoaService.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> getPessoa(@PathVariable Integer id){
		if(id < 0) {
			return ResponseEntity.badRequest().build();
		}
		Pessoa obj = pessoaService.findById(id);
		if(obj == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(obj);
	}
	
	@GetMapping("/by-name/{nome}")
	public ResponseEntity<Pessoa> getPessoaByName(@PathVariable String nome) throws Exception{
		Pessoa obj = pessoaService.findByName(nome);
		if("vazio".equals(nome)) {
			throw new Exception("Erro forçado!!!");
		}		
		return ResponseEntity.ok(obj);
	}
	
}
