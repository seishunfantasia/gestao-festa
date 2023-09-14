package br.gov.sp.etec.gestaofesta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.gov.sp.etec.gestaofesta.model.Login;
import br.gov.sp.etec.gestaofesta.repository.LoginRepository;

@Controller
public class LoginController {
	
	@Autowired
	LoginRepository repository;
	
	@GetMapping("/")
	public String abrirLogin() {
		return "login";
	}
	
	@GetMapping("login/cadastrar")
	public String loginCadastrar() {
		return "cadastro-login";
	}
	
	@PostMapping("/login/salvar")
	public String salvarCadastro(Login login) {
		repository.save(login);
		return "login";
	}
	
	@PostMapping("/efetuar-login")
	public String abrirLogin(Login login) {
		
		Login loginBanco = repository.findByEmail(login.getEmail());
		
		
		
		if(login.getEmail().equals(loginBanco.getEmail())
				&& login.getSenha().equals(loginBanco.getSenha())) {
			return "redirect:convidado/lista-convidados";
		}else {
			return "login";
		}		
	}
}
