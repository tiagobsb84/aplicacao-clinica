package clinica.med.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clinica.med.api.domain.medico.DadosCadastroMedico;
import clinica.med.api.domain.medico.Medico;
import clinica.med.api.repository.MedicoRepository;

@RestController
@RequestMapping(path = "/medicos")
public class MedicoController {

	@Autowired
	private MedicoRepository repository;
	
	@PostMapping
	public ResponseEntity cadastro(@RequestBody DadosCadastroMedico dados) {
		var medico = new Medico(dados);
		repository.save(medico);
		return ResponseEntity.ok(medico);
	}
}
