package clinica.med.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import clinica.med.api.domain.paciente.DadosAtualizarPaciente;
import clinica.med.api.domain.paciente.DadosCadastroPaciente;
import clinica.med.api.domain.paciente.DadosDetalhamentoPaciente;
import clinica.med.api.domain.paciente.DadosListagemPaciente;
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
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder) {
		var paciente = new Paciente(dados);
		repository.save(paciente);
		var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemPaciente>> lista(@PageableDefault(sort = {"nome"}, size = 10) Pageable page) {
		var paciente = repository.findAll(page).map(DadosListagemPaciente::new);
		return ResponseEntity.ok(paciente);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody DadosAtualizarPaciente paciente) {
		var idPaciente = repository.getReferenceById(paciente.id());
		idPaciente.atualizarInformacao(paciente);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping(path = "/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {
		var idPaciente = repository.getReferenceById(id);
		idPaciente.excluir();
		return ResponseEntity.noContent().build();
	}
}
