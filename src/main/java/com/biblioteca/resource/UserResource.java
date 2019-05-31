package com.biblioteca.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.model.User;
import com.biblioteca.repository.UserRepository;

@RestController
@RequestMapping(value = "/login")
@CrossOrigin(origins = "http://localhost:4200")
public class UserResource {

	@Autowired
	private UserRepository repository;
	private User user;

	@PostMapping
	public ResponseEntity<User> login(@Valid @RequestBody User login) {

		user = repository.findByEmail(login.getEmail());

		if (user == null) {
			User usuarioRetorno = User.builder()
					.email(login.getEmail())
					.password(login.getPassword())
					.msgUsuario("Não encontrado usuario para o email informado")
					.build();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(usuarioRetorno);
		}else if(user.getEmail().equals(login.getEmail()) && !user.getPassword().equals(login.getPassword())) {
			User usuarioRetorno = User.builder()
					.email(user.getEmail())
					.password(user.getPassword())
					.msgUsuario("Senha incorreta!")
					.build();
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(usuarioRetorno);
		}
		user.setMsgUsuario("Usuario Logado com Sucesso");
		System.err.println("Usuario retorno : "+ user);
		return ResponseEntity.ok().body(user);
	}

}
