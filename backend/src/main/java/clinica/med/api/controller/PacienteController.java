package clinica.med.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clinica.med.api.domain.paciente.DadosCadastroPaciente;
import clinica.med.api.domain.paciente.Paciente;
import clinica.med.api.repository.PacienteRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/pacientes")
public class PacienteController {

	@Autowired
	private PacienteRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid  DadosCadastroPaciente dados) {
		var paciente = new Paciente(dados);
		repository.save(paciente);
		return ResponseEntity.ok(paciente);
	}
}
