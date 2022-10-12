package br.com.daysesoares.controller;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import br.com.daysesoares.model.Pessoa;
import br.com.daysesoares.service.PessoaService;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@WebMvcTest
public class PessoaControllerTest {

	@Autowired
	private PessoaController pessoaController;
	
	@MockBean
	private PessoaService pessoaService;
	
	@BeforeEach
	public void setup() {
		RestAssuredMockMvc.standaloneSetup(this.pessoaController);
	}
	
	@Test
	public void deveRetornarSucesso_QuandoPesquisarPessoa() {		
		when(this.pessoaService.findById(2)).thenReturn(new Pessoa(2, "Jonas", 30));
		RestAssuredMockMvc.given()
		.accept(ContentType.JSON)
		.when()
		.get("/pessoas/{id}", 2)
		.then()
		.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarNaoEncontrado_QuandoPesquisarPessoa() {
		when(this.pessoaService.findById(2)).thenReturn(null);
		RestAssuredMockMvc.given()
		.accept(ContentType.JSON)
		.when()
		.get("/pessoas/{id}", 5)
		.then()
		.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void deveRetornarBadRequest_QuandoPesquisarPessoa() {
		
		RestAssuredMockMvc.given()
		.accept(ContentType.JSON)
		.when()
		.get("/pessoas/{id}", -1)
		.then()
		.statusCode(HttpStatus.BAD_REQUEST.value());
		
		verify(this.pessoaService, never()).findById(-1);
	}
	
}
