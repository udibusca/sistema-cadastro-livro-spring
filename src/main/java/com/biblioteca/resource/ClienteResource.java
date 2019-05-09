package com.biblioteca.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.biblioteca.model.Cliente;
import com.biblioteca.repository.ClienteRepository;

@RestController
@RequestMapping(value = "/clientes")
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteResource {

	@Autowired
	private ClienteRepository service;

	@GetMapping
	public ResponseEntity<List<Cliente>> findAll() {
		List<Cliente> alunos = service.findAll();
		return ResponseEntity.ok().body(alunos);
	}

	@PostMapping
	public ResponseEntity<?> salvar(@Valid @RequestBody Cliente cliente) {
		service.save(cliente);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cliente.getCodigo()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping
	public ResponseEntity<?> atualizar(@Valid @RequestBody Cliente cliente) {
		service.save(cliente);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "{codigo}")
	public ResponseEntity<?> excluir(@PathVariable Integer codigo) {
		try {
			service.deleteById(codigo);
			return ResponseEntity.ok(codigo);
		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(value = "{codigo}")
	public ResponseEntity<Optional<Cliente>> findById(@PathVariable Integer codigo) {
		Optional<Cliente> clientes = service.findById(codigo);
		return ResponseEntity.ok().body(clientes);
	}

}