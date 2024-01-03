package clinica.med.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clinica.med.api.domain.medico.DadosCadastroMedico;
import clinica.med.api.domain.medico.Medico;
import clinica.med.api.repository.MedicoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/medicos")
public class MedicoController {

	@Autowired
	private MedicoRepository repository;
	
	@PostMapping
	public ResponseEntity cadastro(@RequestBody @Valid DadosCadastroMedico dados) {
		var medico = new Medico(dados);
		repository.save(medico);
		return ResponseEntity.ok(medico);
	}
	
	@GetMapping
	public ResponseEntity<List<Medico>> lista() {
		var lista = repository.findAll();
		return ResponseEntity.ok(lista);
	}
}
